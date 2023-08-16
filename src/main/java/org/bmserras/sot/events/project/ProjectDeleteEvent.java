package org.bmserras.sot.events.project;

import com.vaadin.flow.component.Component;
import org.bmserras.sot.data.domain.Project;

import java.util.Optional;

public class ProjectDeleteEvent extends ProjectEvent {

    public ProjectDeleteEvent(Component source, Optional<Project> project) {
        super(source, project);
    }
}
