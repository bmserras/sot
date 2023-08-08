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
import org.bmserras.sot.data.entity.project.Project;
import org.bmserras.sot.data.service.ProjectService;
import org.bmserras.sot.views.card.CardsLayout;
import org.bmserras.sot.views.layout.AppLayoutNavbar;
import org.vaadin.lineawesome.LineAwesomeIcon;

@PageTitle("Projects")
@Route(value = "projects", layout = AppLayoutNavbar.class)
@RouteAlias(value = "", layout = AppLayoutNavbar.class)
@PermitAll
public class ProjectsView extends VerticalLayout {

    private ProjectService service;

    private final Button changeView;
    private final Button info;

    private final VerticalLayout content;

    private CardsLayout<Project> cardsLayout;
    private ProjectList projectList;

    private boolean initInCardView = true;

    public ProjectsView(ProjectService service) {
        this.service = service;
        setSizeFull();

        cardsLayout = new CardsLayout<>(service);

        cardsLayout.setNewCardIcon(LineAwesomeIcon.FOLDER_PLUS_SOLID);
        cardsLayout.setNewCardText("New Project");
        cardsLayout.setNewCardTooltipText("Create new project");

        cardsLayout.setExistingCardIcon(LineAwesomeIcon.FOLDER_OPEN);
        cardsLayout.setExistingCardTooltipText("Open project");

        cardsLayout.init();

        projectList = new ProjectList(service);

        cardsLayout.addMainButtonClickListener(mainButtonClick -> {
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

        content = new VerticalLayout(cardsLayout, projectList);
        content.setSizeFull();

        changeView.addClickListener(click -> {
            initInCardView = !initInCardView;
            changeView.setIcon(getIcon(initInCardView));

            updateContent();
        });

        updateContent();

        add(new HorizontalLayout(new H2("My Projects"), changeView, info), content);
    }

    private void updateContent() {
        cardsLayout.setVisible(initInCardView);
        projectList.setVisible(!initInCardView);
    }

    private Component getIcon(boolean isCardView) {
        return isCardView ? LineAwesomeIcon.LIST_SOLID.create() : LineAwesomeIcon.TH_SOLID.create();
    }
}
