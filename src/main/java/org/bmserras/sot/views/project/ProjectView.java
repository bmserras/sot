package org.bmserras.sot.views.project;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import jakarta.annotation.security.PermitAll;
import org.bmserras.sot.data.domain.Project;
import org.bmserras.sot.data.domain.Synoptic;
import org.bmserras.sot.data.domain.Widget;
import org.bmserras.sot.data.service.ProjectService;
import org.bmserras.sot.data.service.SynopticService;
import org.bmserras.sot.data.service.WidgetService;
import org.bmserras.sot.views.layout.AppLayoutNavbar;
import org.bmserras.sot.views.synoptic.SynopticLayout;
import org.bmserras.sot.views.widget.WidgetLayout;
import org.vaadin.lineawesome.LineAwesomeIcon;
import org.vaadin.olli.FileDownloadWrapper;

import java.io.ByteArrayInputStream;
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
    private WidgetService widgetService;

    private SynopticLayout synopticLayout;
    private WidgetLayout widgetLayout;

    private Project project;

    public ProjectView(ProjectService projectService, SynopticService synopticService, WidgetService widgetService) {
        this.projectService = projectService;
        this.synopticService = synopticService;
        this.widgetService = widgetService;
        setSizeFull();
        setPadding(false);
        setSpacing(false);

        back = new Button(LineAwesomeIcon.ARROW_LEFT_SOLID.create(), click ->
                click.getSource().getUI().ifPresent(ui -> ui.navigate(ProjectsView.class))
        );
        name = new TextField();

        HorizontalLayout horizontalLayout = new HorizontalLayout(back, new Span("Project"), name);
        horizontalLayout.setAlignItems(Alignment.CENTER);
        horizontalLayout.setMargin(true);

        synopticLayout = new SynopticLayout();
        synopticLayout.getStyle().set("border-right", "3px solid grey");

        synopticLayout.addOpenListener(click -> {
            Optional<Synoptic> synoptic = click.getSynoptic();
            synoptic.ifPresent(p -> UI.getCurrent().navigate("synoptic/" + p.getId()));
            //synoptic.ifPresent(p -> UI.getCurrent().navigate("ops/synoptic/" + p.getId()));
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
        synopticLayout.addExportListener(click -> {
            Optional<Synoptic> synoptic = click.getSynoptic();
            synoptic.ifPresent(s -> {
                Button button = new Button();
                button.addClassName("invisible-button");
                FileDownloadWrapper buttonWrapper = new FileDownloadWrapper(
                        new StreamResource(s.getName() + ".json", () -> new ByteArrayInputStream(s.getName().getBytes())));
                buttonWrapper.wrapComponent(button);
                add(buttonWrapper);
                button.clickInClient();
            });
        });

        widgetLayout = new WidgetLayout("Widgets", widgetService);
        List<Widget> widgets = new ArrayList<>();
        widgetLayout.setItems(widgets);

        widgetLayout.addOpenListener(click -> {

        });
        widgetLayout.addSaveListener(click -> {
            click.getWidget().ifPresent(widget -> {
                widgets.add(widget);
                widgetLayout.setItems(widgets);
                if (widget.getName().equals("")) widget.setName("Blank Widget");
                if (!project.getWidgets().contains(widget)) {
                    project.addWidget(widget);
                    projectService.save(project);
                }
                else widgetService.save(widget);
                widgetLayout.setItems(project.getWidgets());
            });
        });
        widgetLayout.addDeleteListener(click -> {
            click.getWidget().ifPresent(widget -> {
                widgets.add(widget);
                widgetLayout.setItems(widgets);
            });
        });

        HorizontalLayout panels = new HorizontalLayout(synopticLayout, widgetLayout);
        panels.setSizeFull();
        add(horizontalLayout, panels);
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, String parameter) {
        projectService.findById(parameter).ifPresent(project -> {
            name.setValue(project.getName());
            synopticLayout.setItems(project.getSynoptics());
            widgetLayout.setItems(project.getWidgets());
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
