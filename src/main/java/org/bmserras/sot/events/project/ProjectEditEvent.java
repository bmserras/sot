package org.bmserras.sot.events.project;

import com.vaadin.flow.component.Component;
import org.bmserras.sot.data.domain.Project;

import java.util.Optional;

public class ProjectEditEvent extends ProjectEvent {

    public ProjectEditEvent(Component source, Optional<Project> project) {
        super(source, project);
    }
}
