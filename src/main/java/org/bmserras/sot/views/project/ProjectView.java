package org.bmserras.sot.views.project;

import com.vaadin.flow.component.UI;
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
import org.bmserras.sot.data.domain.Synoptic;
import org.bmserras.sot.data.service.ProjectService;
import org.bmserras.sot.data.service.SynopticService;
import org.bmserras.sot.views.layout.AppLayoutNavbar;
import org.bmserras.sot.views.synoptic.SynopticLayout;
import org.vaadin.lineawesome.LineAwesomeIcon;

import java.util.Optional;

@PageTitle("Project")
@Route(value = "project", layout = AppLayoutNavbar.class)
@PermitAll
public class ProjectView extends VerticalLayout implements HasUrlParameter<String> {

    private Button back;
    private TextField name;

    private ProjectService projectService;
    private SynopticService synopticService;

    private SynopticLayout layout;

    private Project project;

    public ProjectView(ProjectService projectService, SynopticService synopticService) {
        this.projectService = projectService;
        this.synopticService = synopticService;
        setSizeFull();

        back = new Button(LineAwesomeIcon.ARROW_LEFT_SOLID.create(), click ->
                click.getSource().getUI().ifPresent(ui -> ui.navigate(ProjectsView.class))
        );
        name = new TextField();

        HorizontalLayout horizontalLayout = new HorizontalLayout(back, name);
        horizontalLayout.setAlignItems(Alignment.CENTER);

        layout = new SynopticLayout();

        layout.addOpenListener(click -> {
            Optional<Synoptic> synoptic = click.getSynoptic();
            synoptic.ifPresent(p -> UI.getCurrent().navigate("synoptic/" + p.getId()));
        });
        layout.addSaveListener(click -> {
            Optional<Synoptic> synoptic = click.getSynoptic();
            synoptic.ifPresent(s -> {
                if (!project.getSynoptics().contains(s)) {
                    project.addSynoptics(s);
                    projectService.save(project);
                }
                else synopticService.save(s);
                layout.setItems(project.getSynoptics());
            });
        });
        layout.addDeleteListener(click -> {
            Optional<Synoptic> synoptic = click.getSynoptic();
            synoptic.ifPresent(s -> {
                project.removeSynoptic(s);
                projectService.save(project);
                synopticService.delete(s);
                layout.setItems(project.getSynoptics());
            });
        });

        add(horizontalLayout, layout, new H2("Widgets"));
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, String parameter) {
        projectService.findById(parameter).ifPresent(project -> {
            name.setValue(project.getName());
            layout.setItems(project.getSynoptics());
            this.project = project;
        });

        name.addValueChangeListener(valueChangeEvent -> {
            Optional<Project> project = projectService.findById(parameter);
            project.ifPresent(p -> {
                p.setName(valueChangeEvent.getValue());
                projectService.save(project.get());
            });
        });


    }
}
