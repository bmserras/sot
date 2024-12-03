package org.bmserras.sot.views.project;

import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.bmserras.sot.data.domain.Project;
import org.bmserras.sot.events.project.ProjectDeleteEvent;
import org.bmserras.sot.events.project.ProjectOpenEvent;
import org.bmserras.sot.events.project.ProjectSaveEvent;
import org.bmserras.sot.views.components.Toolbar;
import org.bmserras.sot.views.project.card.ProjectCardLayout;

import java.util.List;

public class ProjectLayout extends VerticalLayout {

    private final HorizontalLayout header;
    private final H2 title;
    private final Toolbar toolbar;

    private final ProjectCardLayout cardLayout;

    public ProjectLayout() {
        setSizeFull();
        setMargin(false);

        title = new H2("My Projects");
        toolbar = new Toolbar();
        header = new HorizontalLayout(title, toolbar);
        header.setWidthFull();
        header.setJustifyContentMode(JustifyContentMode.CENTER);
        header.setAlignItems(Alignment.BASELINE);

        cardLayout = new ProjectCardLayout();

        add(header, cardLayout);
    }

    public void setItems(List<Project> projects) {
        cardLayout.setItems(projects);
    }

    public void addOpenListener(ComponentEventListener<ProjectOpenEvent> listener) {
        cardLayout.addOpenListener(listener);
    }

    public void addSaveListener(ComponentEventListener<ProjectSaveEvent> listener) {
        cardLayout.addSaveListener(listener);
    }

    public void addDeleteListener(ComponentEventListener<ProjectDeleteEvent> listener) {
        cardLayout.addDeleteListener(listener);
    }
}
