package org.bmserras.sot.views.project;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import org.bmserras.sot.data.domain.Project;
import org.bmserras.sot.data.domain.User;
import org.bmserras.sot.data.service.ProjectService;
import org.bmserras.sot.data.service.SynopticService;
import org.bmserras.sot.data.service.UserService;
import org.bmserras.sot.security.AuthenticatedUser;
import org.bmserras.sot.views.layout.AppLayoutNavbar;
import org.bmserras.sot.views.synoptic.card.SynopticCards;
import org.vaadin.lineawesome.LineAwesomeIcon;

import java.util.Optional;

@PageTitle("Project")
@Route(value = "project", layout = AppLayoutNavbar.class)
@PermitAll
public class ProjectView extends VerticalLayout implements HasUrlParameter<String> {

    private ProjectService projectService;
    private SynopticService synopticService;

    private Button back;

    private TextField name;

    private SynopticCards synopticCards;

    public ProjectView(ProjectService projectService, SynopticService synopticService) {
        this.projectService = projectService;
        this.synopticService = synopticService;

        back = new Button(LineAwesomeIcon.ARROW_LEFT_SOLID.create(), click ->
                click.getSource().getUI().ifPresent(ui -> ui.navigate(ProjectsView.class))
        );

        name = new TextField();

        HorizontalLayout horizontalLayout = new HorizontalLayout(back, name);
        horizontalLayout.setAlignItems(Alignment.CENTER);

        synopticCards = new SynopticCards(projectService, synopticService);

        add(horizontalLayout, new H2("Synoptics"), synopticCards, new H2("Widgets"));
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, String parameter) {
        projectService.findById(parameter).ifPresent(project -> {
            name.setValue(project.getName());
            synopticCards.init(projectService, project);
        });

        name.addValueChangeListener(valueChangeEvent -> {
            //Optional<ProjectDB> project = service.findByName(valueChangeEvent.getOldValue());
            Optional<Project> project = projectService.findById(parameter);
            project.ifPresent(p -> {
                p.setName(valueChangeEvent.getValue());
                projectService.save(project.get());
            });
        });


    }
}
