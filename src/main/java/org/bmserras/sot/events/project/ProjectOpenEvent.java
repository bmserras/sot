package org.bmserras.sot.events.project;

import com.vaadin.flow.component.Component;
import org.bmserras.sot.data.db.project.ProjectDB;
import org.bmserras.sot.data.domain.Project;

import java.util.Optional;

public class ProjectOpenEvent extends ProjectEvent {

    public ProjectOpenEvent(Component source, Optional<Project> project) {
        super(source, project);
    }
}
