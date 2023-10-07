package org.bmserras.sot.views.project;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
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
import org.bmserras.sot.data.domain.Widget;
import org.bmserras.sot.data.service.ProjectService;
import org.bmserras.sot.data.service.SynopticService;
import org.bmserras.sot.views.layout.AppLayoutNavbar;
import org.bmserras.sot.views.synoptic.SynopticLayout;
import org.bmserras.sot.views.widget.WidgetLayout;
import org.vaadin.lineawesome.LineAwesomeIcon;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@PageTitle("Project")
@Route(value = "project", layout = AppLayoutNavbar.class)
@PermitAll
public class ProjectView extends VerticalLayout implements HasUrlParameter<String> {

    private Button back;
    private TextField name;

    private ProjectService projectService;
    private SynopticService synopticService;

    private SynopticLayout synopticLayout;
    private WidgetLayout widgetLayout;

    private Project project;

    public ProjectView(ProjectService projectService, SynopticService synopticService) {
        this.projectService = projectService;
        this.synopticService = synopticService;
        setSizeFull();
        setSpacing(false);

        back = new Button(LineAwesomeIcon.ARROW_LEFT_SOLID.create(), click ->
                click.getSource().getUI().ifPresent(ui -> ui.navigate(ProjectsView.class))
        );
        name = new TextField();

        HorizontalLayout horizontalLayout = new HorizontalLayout(back, new Span("Project"), name);
        horizontalLayout.setAlignItems(Alignment.CENTER);

        synopticLayout = new SynopticLayout();

        synopticLayout.addOpenListener(click -> {
            Optional<Synoptic> synoptic = click.getSynoptic();
            synoptic.ifPresent(p -> UI.getCurrent().navigate("synoptic/" + p.getId()));
        });
        synopticLayout.addSaveListener(click -> {
            Optional<Synoptic> synoptic = click.getSynoptic();
            synoptic.ifPresent(s -> {
                if (!project.getSynoptics().contains(s)) {
                    project.addSynoptics(s);
                    projectService.save(project);
                }
                else synopticService.save(s);
                synopticLayout.setItems(project.getSynoptics());
            });
        });
        synopticLayout.addDeleteListener(click -> {
            Optional<Synoptic> synoptic = click.getSynoptic();
            synoptic.ifPresent(s -> {
                project.removeSynoptic(s);
                projectService.save(project);
                synopticService.delete(s);
                synopticLayout.setItems(project.getSynoptics());
            });
        });

        widgetLayout = new WidgetLayout("Widgets", 100);
        List<Widget> widgets = new ArrayList<>();
        widgetLayout.setItems(widgets);

        widgetLayout.addOpenListener(click -> {

        });
        widgetLayout.addSaveListener(click -> {
            click.getWidget().ifPresent(widget -> {
                widgets.add(widget);
                widgetLayout.setItems(widgets);
            });
        });
        widgetLayout.addDeleteListener(click -> {
            click.getWidget().ifPresent(widget -> {
                widgets.add(widget);
                widgetLayout.setItems(widgets);
            });
        });

        add(horizontalLayout, synopticLayout, widgetLayout);
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, String parameter) {
        projectService.findById(parameter).ifPresent(project -> {
            name.setValue(project.getName());
            synopticLayout.setItems(project.getSynoptics());
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
