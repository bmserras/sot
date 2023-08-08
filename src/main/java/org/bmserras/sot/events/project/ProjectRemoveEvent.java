package org.bmserras.sot.events.project;

import com.vaadin.flow.component.Component;
import org.bmserras.sot.data.entity.project.Project;
import org.bmserras.sot.events.project.ProjectEvent;

import java.util.Optional;

public class ProjectRemoveEvent extends ProjectEvent {

    public ProjectRemoveEvent(Component source, Optional<Project> project) {
        super(source, project);
    }
}
