package org.bmserras.sot.events.project;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import org.bmserras.sot.data.db.project.Project;

import java.util.Optional;

public class ProjectEvent extends ComponentEvent<Component> {

    private final Optional<Project> project;

    public ProjectEvent(Component source, Optional<Project> project) {
        super(source, false);
        this.project = project;
    }

    public Optional<Project> getProject() {
        return project;
    }

}
