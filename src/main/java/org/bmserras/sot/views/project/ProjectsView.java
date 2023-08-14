package org.bmserras.sot.views.project;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import jakarta.annotation.security.PermitAll;
import org.bmserras.sot.data.domain.User;
import org.bmserras.sot.data.service.ProjectService;
import org.bmserras.sot.data.service.UserService;
import org.bmserras.sot.security.AuthenticatedUser;
import org.bmserras.sot.views.layout.AppLayoutNavbar;
import org.bmserras.sot.views.project.card.ProjectCards;
import org.bmserras.sot.views.project.list.ProjectList;
import org.vaadin.lineawesome.LineAwesomeIcon;

@PageTitle("Projects")
@Route(value = "projects", layout = AppLayoutNavbar.class)
@RouteAlias(value = "", layout = AppLayoutNavbar.class)
@PermitAll
public class ProjectsView extends VerticalLayout {

    private AuthenticatedUser authenticatedUser;

    private UserService userService;
    private ProjectService projectService;

    private final Button changeView;
    private final Button info;

    private final VerticalLayout content;

    private ProjectCards projectCards;
    private ProjectList projectList;

    private boolean initInCardView = true;

    public ProjectsView(AuthenticatedUser authenticatedUser, UserService userService, ProjectService projectService) {
        this.authenticatedUser = authenticatedUser;
        this.userService = userService;
        this.projectService = projectService;
        setSizeFull();

        User user = authenticatedUser.get().get();

        projectCards = new ProjectCards(userService, projectService, user);
        projectList = new ProjectList(projectService);

        changeView = new Button(getIcon(initInCardView));
        info = new Button(LineAwesomeIcon.INFO_CIRCLE_SOLID.create());

        content = new VerticalLayout(projectCards, projectList);
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
        projectCards.setVisible(initInCardView);
        projectList.setVisible(!initInCardView);
    }

    private Component getIcon(boolean isCardView) {
        return isCardView ? LineAwesomeIcon.LIST_SOLID.create() : LineAwesomeIcon.TH_SOLID.create();
    }
}
