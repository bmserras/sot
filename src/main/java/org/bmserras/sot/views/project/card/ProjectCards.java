package org.bmserras.sot.views.project.card;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import org.bmserras.sot.data.entity.project.Project;
import org.bmserras.sot.data.service.ProjectService;
import org.bmserras.sot.views.card.ExistingCard;
import org.bmserras.sot.views.card.NewCard;
import org.vaadin.lineawesome.LineAwesomeIcon;

import java.util.List;

public class ProjectCards extends VerticalLayout {

    private ProjectService service;

    private final HorizontalLayout horizontalLayout;
    private final NewCard newProject;

    public ProjectCards(ProjectService service) {
        this.service = service;
        setSizeFull();

        newProject = new NewCard(LineAwesomeIcon.FOLDER_PLUS_SOLID.create(), "New Project", "Create new project");
        newProject.addMainButtonClickListener(mainButtonClick -> {
            Dialog dialog = new Dialog("Create new project");

            TextField name = new TextField("Project name", "Blank project");
            Button create = new Button("Create", createButtonClick -> {
                Project project = new Project(name.getValue().equals("") ? "Blank project" : name.getValue());
                service.save(project);
                dialog.close();
                mainButtonClick.getSource().getUI().ifPresent(ui -> ui.navigate("project/" + project.getIdentifier()));
            });

            dialog.add(name, create);
            dialog.open();
        });

        horizontalLayout = new HorizontalLayout(newProject);
        horizontalLayout.setWidthFull();
        horizontalLayout.setHeight("40%");

        add(new H2("Projects"), horizontalLayout);

        updateList();
    }

    private void updateList() {
        List<Project> allProjects = service.findAll("");
        
        for (Project project : allProjects) {
            ExistingCard existingCard = new ExistingCard(LineAwesomeIcon.FOLDER_OPEN.create(), project.getName(),
                    "Open project");
            existingCard.addMainButtonClickListener(mainButtonClick ->
                    mainButtonClick.getSource().getUI().ifPresent(ui -> ui.navigate("project/" + project.getIdentifier())));
            existingCard.addOpenButtonClickListener(openButtonClick -> openButtonClick.getSource().getUI().ifPresent(ui -> ui.navigate("project/" + project.getIdentifier())));
            existingCard.addDeleteButtonClickListener(deleteButtonClick -> {
                service.delete(project);
                horizontalLayout.remove(existingCard);
            });
            horizontalLayout.add(existingCard);
        }
    }
}