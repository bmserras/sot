package org.bmserras.sot.views.synoptic.card;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import org.bmserras.sot.data.entity.synoptic.Synoptic;
import org.bmserras.sot.data.service.SynopticService;
import org.bmserras.sot.views.card.ExistingCard;
import org.bmserras.sot.views.card.NewCard;
import org.vaadin.lineawesome.LineAwesomeIcon;

import java.util.List;

public class SynopticCards extends VerticalLayout {

    private SynopticService service;

    private final HorizontalLayout horizontalLayout;
    private final NewCard newSynoptic;

    public SynopticCards(SynopticService service) {
        this.service = service;
        setSizeFull();

        newSynoptic = new NewCard(LineAwesomeIcon.FOLDER_PLUS_SOLID.create(), "New Synoptic", "Create new synoptic");
        newSynoptic.addMainButtonClickListener(mainButtonClick -> {
            Dialog dialog = new Dialog("Create new synoptic");

            TextField name = new TextField("Synoptic name", "Blank synoptic");
            Button create = new Button("Create", createButtonClick -> {
                Synoptic synoptic = new Synoptic(name.getValue().equals("") ? "Blank synoptic" : name.getValue());
                service.save(synoptic);
                dialog.close();
                mainButtonClick.getSource().getUI().ifPresent(ui -> ui.navigate("synoptic/" + synoptic.getIdentifier()));
            });

            dialog.add(name, create);
            dialog.open();
        });

        horizontalLayout = new HorizontalLayout(newSynoptic);
        horizontalLayout.setWidthFull();
        horizontalLayout.setHeight("40%");

        add(new H2("Synoptics"), horizontalLayout);

        updateList();
    }

    private void updateList() {
        List<Synoptic> allSynoptics = service.findAll("");

        for (Synoptic synoptic : allSynoptics) {
            ExistingCard existingCard = new ExistingCard(LineAwesomeIcon.FOLDER_OPEN.create(), synoptic.getName(),
                    "Open synoptic");
            existingCard.addMainButtonClickListener(mainButtonClick ->
                    mainButtonClick.getSource().getUI().ifPresent(ui -> ui.navigate("synoptic/" + synoptic.getIdentifier())));
            existingCard.addOpenButtonClickListener(openButtonClick -> openButtonClick.getSource().getUI().ifPresent(ui -> ui.navigate("synoptic/" + synoptic.getIdentifier())));
            existingCard.addDeleteButtonClickListener(deleteButtonClick -> {
                service.delete(synoptic);
                horizontalLayout.remove(existingCard);
            });
            horizontalLayout.add(existingCard);
        }
    }
}