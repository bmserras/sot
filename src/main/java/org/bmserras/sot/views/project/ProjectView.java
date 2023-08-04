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
import org.bmserras.sot.data.entity.project.Project;
import org.bmserras.sot.data.service.ProjectService;
import org.bmserras.sot.views.layout.AppLayoutNavbar;
import org.vaadin.lineawesome.LineAwesomeIcon;

@PageTitle("Project")
@Route(value = "project", layout = AppLayoutNavbar.class)
@PermitAll
public class ProjectView extends VerticalLayout implements HasUrlParameter<String> {

    private ProjectService service;

    private Button back;

    private TextField name;

    public ProjectView(ProjectService service) {
        this.service = service;

        back = new Button(LineAwesomeIcon.ARROW_LEFT_SOLID.create(), click ->
                click.getSource().getUI().ifPresent(ui -> ui.navigate(ProjectsView.class))
        );

        name = new TextField();

        HorizontalLayout horizontalLayout = new HorizontalLayout(back, name);
        horizontalLayout.setAlignItems(Alignment.CENTER);

        add(horizontalLayout, new H2("Synoptics"), new H2("Widgets"));
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, String parameter) {
        service.findProjectById(parameter).ifPresent(project -> name.setValue(project.getName()));

        name.addValueChangeListener(valueChangeEvent -> {
            Project project = service.findProjectByName(valueChangeEvent.getOldValue());
            project.setName(valueChangeEvent.getValue());
            service.saveProject(project);
        });
    }
}
