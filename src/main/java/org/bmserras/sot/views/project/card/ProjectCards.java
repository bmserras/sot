package org.bmserras.sot.views.project.card;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import org.bmserras.sot.data.domain.Project;
import org.bmserras.sot.data.domain.User;
import org.bmserras.sot.data.service.ProjectService;
import org.bmserras.sot.data.service.UserService;
import org.bmserras.sot.views.card.ExistingCard;
import org.bmserras.sot.views.card.NewCard;
import org.vaadin.lineawesome.LineAwesomeIcon;

import java.util.Date;
import java.util.List;

public class ProjectCards extends VerticalLayout {

    private UserService userService;
    private ProjectService projectService;
    private User user;

    private final HorizontalLayout horizontalLayout;
    private final NewCard newProject;

    public ProjectCards(UserService userService, ProjectService projectService, User user) {
        this.userService = userService;
        this.projectService = projectService;
        this.user = user;
        setSizeFull();

        newProject = new NewCard(LineAwesomeIcon.FOLDER_PLUS_SOLID.create(), "New Project", "Create new project");
        newProject.addMainButtonClickListener(mainButtonClick -> {
            Dialog dialog = new Dialog("Create new project");

            TextField name = new TextField("Project name", "Blank project");
            Button create = new Button("Create", createButtonClick -> {
                //ProjectDB projectDB = new ProjectDB(name.getValue().equals("") ? "Blank project" : name.getValue());
                //service.save(projectDB);
                Project p = new Project(new Date().getTime(), name.getValue().equals("") ? "Blank project" : name.getValue());
                user.addProject(p);
                userService.save(user);
                //projectService.save(p);
                dialog.close();
                mainButtonClick.getSource().getUI().ifPresent(ui -> ui.navigate("project/" + p.getId()));
            });

            dialog.add(name, create);
            dialog.open();
        });

        horizontalLayout = new HorizontalLayout(newProject);
        horizontalLayout.setWidthFull();
        horizontalLayout.setHeight("40%");

        add(horizontalLayout);

        updateList();
    }

    private void updateList() {
        List<Project> projects = user.getProjects();

        for (Project project : projects) {
            ExistingCard existingCard = new ExistingCard(LineAwesomeIcon.FOLDER_OPEN.create(), project.getName(),
                    "Open project");
            existingCard.addMainButtonClickListener(mainButtonClick ->
                    mainButtonClick.getSource().getUI().ifPresent(ui -> ui.navigate("project/" + project.getId())));
            existingCard.addOpenButtonClickListener(openButtonClick -> openButtonClick.getSource().getUI().ifPresent(ui -> ui.navigate("project/" + project.getId())));
            existingCard.addDeleteButtonClickListener(deleteButtonClick -> {

                user.removeProject(project);
                userService.save(user);
                projectService.delete(project);
                horizontalLayout.remove(existingCard);
            });
            horizontalLayout.add(existingCard);
        }
    }
}