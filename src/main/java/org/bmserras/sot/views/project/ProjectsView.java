package org.bmserras.sot.views.project;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import jakarta.annotation.security.PermitAll;
import org.bmserras.sot.components.card.ExistingCard;
import org.bmserras.sot.components.card.NewCard;
import org.bmserras.sot.data.entity.project.Project;
import org.bmserras.sot.data.service.ProjectService;
import org.bmserras.sot.views.layout.AppLayoutNavbar;
import org.bmserras.sot.views.layout.MainLayout;
import org.vaadin.lineawesome.LineAwesomeIcon;

import java.util.List;

@PageTitle("Projects")
@Route(value = "projects", layout = AppLayoutNavbar.class)
@RouteAlias(value = "", layout = MainLayout.class)
@PermitAll
public class ProjectsView extends VerticalLayout {

    private ProjectService service;

    private final HorizontalLayout horizontalLayout;
    private final NewCard newProject;

    public ProjectsView(ProjectService service) {
        this.service = service;
        setSizeFull();

        newProject = new NewCard(LineAwesomeIcon.FOLDER_PLUS_SOLID.create(), "New Project");
        newProject.addMainButtonClickListener(mainButtonClick -> {
            Dialog dialog = new Dialog("Create new project");

            TextField name = new TextField("Project name", "Blank project");
            Button create = new Button("Create", createButtonClick -> {
                Project project = new Project(name.getValue().equals("") ? "Blank project" : name.getValue());
                service.saveProject(project);
                dialog.close();
                mainButtonClick.getSource().getUI().ifPresent(ui -> ui.navigate("project/" + project.getIdentifier()));
            });

            dialog.add(name, create);
            dialog.open();
        });

        horizontalLayout = new HorizontalLayout(newProject);
        horizontalLayout.setWidthFull();
        horizontalLayout.setHeight("40%");

        add(new H2("My Projects"), horizontalLayout);

        updateList();
    }

    private void updateList() {
        List<Project> allProjects = service.findAllProjects("");

        /*int lines = ((allProjects.size()) / 5) + 1;
        int c = 0;
        for (int i = 0; i < lines; i++) {
            HorizontalLayout horizontalLayout = new HorizontalLayout();
            horizontalLayout.setWidthFull();
            horizontalLayout.setHeight("40%");
            add(horizontalLayout);
            if (i == 0) {
                horizontalLayout.add(newProject);
            }
            ++c;
            for (int j = c*(i+1); j < 5 && j < allProjects.size(); j++) {
                horizontalLayout.add(new ExistingCard(LineAwesomeIcon.FOLDER_OPEN.create(), allProjects.get(j).getName()));
            }
        }*/

        for (Project project : allProjects) {
            ExistingCard existingCard = new ExistingCard(LineAwesomeIcon.FOLDER_OPEN.create(), project.getName());
            existingCard.addMainButtonClickListener(mainButtonClick ->
                    mainButtonClick.getSource().getUI().ifPresent(ui -> ui.navigate("project/" + project.getIdentifier())));
            existingCard.addOpenButtonClickListener(openButtonClick -> openButtonClick.getSource().getUI().ifPresent(ui -> ui.navigate("project/" + project.getIdentifier())));
            existingCard.addDeleteButtonClickListener(deleteButtonClick -> {
                service.deleteProject(project);
                horizontalLayout.remove(existingCard);
            });
            horizontalLayout.add(existingCard);
        }
    }
}
