package org.bmserras.sot.views.project;

import com.vaadin.flow.component.Component;
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
import org.vaadin.lineawesome.LineAwesomeIcon;

import java.util.List;

@PageTitle("Projects")
@Route(value = "projects", layout = AppLayoutNavbar.class)
@RouteAlias(value = "", layout = AppLayoutNavbar.class)
@PermitAll
public class ProjectsView extends VerticalLayout {

    private ProjectService service;

    private final NewCard newProject;

    private final int NUMBER_OF_CARDS_PER_ROW = 6;

    private final Button changeView;
    private final Button info;

    private final VerticalLayout content;

    private boolean initInCardView = true;

    public ProjectsView(ProjectService service) {
        this.service = service;
        setSizeFull();

        newProject = new NewCard(LineAwesomeIcon.FOLDER_PLUS_SOLID.create(), "New Project");
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

        changeView = new Button(getIcon(initInCardView));
        info = new Button(LineAwesomeIcon.INFO_CIRCLE_SOLID.create());

        content = new VerticalLayout();
        content.setSizeFull();

        changeView.addClickListener(click -> {
            initInCardView = !initInCardView;
            changeView.setIcon(getIcon(initInCardView));

            updateContent();
        });

        add(new HorizontalLayout(new H2("My Projects"), changeView, info), content);

        updateContent();
    }

    private void updateContent() {
        content.removeAll();
        if (initInCardView) {
            updateCards();
        } else {
            updateList();
        }
    }

    private void updateList() {
        content.add(new ProjectList(service));
    }

    private Component getIcon(boolean isCardView) {
        return isCardView ? LineAwesomeIcon.LIST_SOLID.create() : LineAwesomeIcon.TH_SOLID.create();
    }

    private void updateCards() {
        List<Project> allProjects = service.findAll("");

        int lines = ((allProjects.size()) / NUMBER_OF_CARDS_PER_ROW) + 1;
        for (int line = 0; line < lines; line++) {

            HorizontalLayout horizontalLayout = new HorizontalLayout();
            horizontalLayout.setWidthFull();
            horizontalLayout.setHeight("40%");
            content.add(horizontalLayout);

            if (line == 0) {
                horizontalLayout.add(newProject);
            }

            int row = line == 0 ? 1 : 0;
            int index = (line * NUMBER_OF_CARDS_PER_ROW) + row - 1;
            while (row < NUMBER_OF_CARDS_PER_ROW && index < allProjects.size()) {

                Project project = allProjects.get(index);
                ExistingCard existingCard = new ExistingCard(LineAwesomeIcon.FOLDER_OPEN.create(), project.getName());
                existingCard.addMainButtonClickListener(mainButtonClick ->
                        mainButtonClick.getSource().getUI().ifPresent(ui -> ui.navigate("project/" + project.getIdentifier())));
                existingCard.addOpenButtonClickListener(openButtonClick -> openButtonClick.getSource().getUI().ifPresent(ui -> ui.navigate("project/" + project.getIdentifier())));
                existingCard.addDeleteButtonClickListener(deleteButtonClick -> {
                    service.delete(project);
                    horizontalLayout.remove(existingCard);
                });
                horizontalLayout.add(existingCard);

                ++row;
                ++index;
            }

        }

    }
}
