package org.bmserras.sot.views.synoptic.card;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import org.bmserras.sot.data.domain.Project;
import org.bmserras.sot.data.domain.Synoptic;
import org.bmserras.sot.data.service.ProjectService;
import org.bmserras.sot.data.service.SynopticService;
import org.bmserras.sot.views.card.ExistingCard;
import org.bmserras.sot.views.card.NewCard;
import org.vaadin.lineawesome.LineAwesomeIcon;

import java.util.Date;
import java.util.List;

public class SynopticCards extends VerticalLayout {

    private ProjectService projectService;
    private SynopticService synopticService;

    private HorizontalLayout horizontalLayout;
    private NewCard newSynoptic;

    public SynopticCards(ProjectService projectService, SynopticService synopticService) {
        this.projectService = projectService;
        this.synopticService = synopticService;
        setSizeFull();

    }

    private void updateList(Project project) {
        List<Synoptic> synoptics = project.getSynoptics();

        for (Synoptic synoptic : synoptics) {
            ExistingCard existingCard = new ExistingCard(LineAwesomeIcon.CHART_PIE_SOLID.create(), synoptic.getName(),
                    "Open synoptic");
            existingCard.addMainButtonClickListener(mainButtonClick ->
                    mainButtonClick.getSource().getUI().ifPresent(ui -> ui.navigate("synoptic/" + synoptic.getId())));
            existingCard.addOpenButtonClickListener(openButtonClick -> openButtonClick.getSource().getUI().ifPresent(ui -> ui.navigate("synoptic/" + synoptic.getId())));
            existingCard.addDeleteButtonClickListener(deleteButtonClick -> {

                project.removeSynoptic(synoptic);
                projectService.save(project);
                synopticService.delete(synoptic);
                horizontalLayout.remove(existingCard);
            });
            horizontalLayout.add(existingCard);
        }
    }

    public void init(ProjectService projectService, Project project) {
        newSynoptic = new NewCard(LineAwesomeIcon.PLUS_CIRCLE_SOLID.create(), "New Synoptic", "Create new synoptic");
        newSynoptic.addMainButtonClickListener(mainButtonClick -> {
            Dialog dialog = new Dialog("Create new synoptic");

            TextField name = new TextField("Synoptic name", "Blank synoptic");
            Button create = new Button("Create", createButtonClick -> {
                Synoptic synoptic = new Synoptic(new Date().getTime(), name.getValue().equals("") ? "Blank synoptic" :
                        name.getValue());

                project.addSynoptic(synoptic);
                projectService.save(project);

                dialog.close();
                mainButtonClick.getSource().getUI().ifPresent(ui -> ui.navigate("synoptic/" + synoptic.getId()));
            });

            dialog.add(name, create);
            dialog.open();
        });

        horizontalLayout = new HorizontalLayout(newSynoptic);
        horizontalLayout.setWidthFull();
        horizontalLayout.setHeight("40%");

        add(horizontalLayout);

        updateList(project);
    }
}