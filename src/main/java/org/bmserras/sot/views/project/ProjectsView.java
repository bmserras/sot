package org.bmserras.sot.views.project;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import org.bmserras.sot.data.domain.Project;
import org.bmserras.sot.data.domain.User;
import org.bmserras.sot.data.service.ProjectService;
import org.bmserras.sot.data.service.UserService;
import org.bmserras.sot.security.AuthenticatedUser;
import org.bmserras.sot.views.layout.AppLayoutNavbar;

import java.util.List;
import java.util.Optional;

@PageTitle("Projects")
@Route(value = "projects-test", layout = AppLayoutNavbar.class)
@PermitAll
public class ProjectsView extends VerticalLayout {

    private AuthenticatedUser authenticatedUser;

    private UserService userService;
    private ProjectService projectService;

    private ProjectLayout layout;

    public ProjectsView(AuthenticatedUser authenticatedUser, UserService userService, ProjectService projectService) {
        this.authenticatedUser = authenticatedUser;
        this.userService = userService;
        this.projectService = projectService;
        setSizeFull();

        Optional<User> userOp = authenticatedUser.get();
        if (userOp.isEmpty()) return;
        User user = userOp.get();
        List<Project> projects = user.getProjects();

        layout = new ProjectLayout();
        layout.setItems(projects);

        layout.addOpenListener(click -> {
            Optional<Project> project = click.getProject();
            project.ifPresent(p -> UI.getCurrent().navigate("project-test/" + p.getId()));
        });
        layout.addSaveListener(click -> {
            Optional<Project> project = click.getProject();
            project.ifPresent(p -> {
                if (p.getName().equals("")) p.setName("Blank Project");
                if (!user.getProjects().contains(p)) {
                    user.addProjects(p);
                    userService.save(user);
                }
                else projectService.save(p);
                layout.setItems(user.getProjects());
            });
        });
        layout.addDeleteListener(click -> {
            Optional<Project> project = click.getProject();
            project.ifPresent(p -> {
                user.removeProject(p);
                userService.save(user);
                projectService.delete(p);
                layout.setItems(user.getProjects());
            });
        });

        add(layout);
    }
}
