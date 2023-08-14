package org.bmserras.sot.events.project;

import com.vaadin.flow.component.Component;
import org.bmserras.sot.data.db.project.ProjectDB;
import org.bmserras.sot.data.domain.Project;

import java.util.Optional;

public class ProjectRemoveEvent extends ProjectEvent {

    public ProjectRemoveEvent(Component source, Optional<Project> project) {
        super(source, project);
    }
}
