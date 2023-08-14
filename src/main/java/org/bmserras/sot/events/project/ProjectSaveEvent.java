package org.bmserras.sot.events.project;

import com.vaadin.flow.component.Component;
import org.bmserras.sot.data.db.project.Project;

import java.util.Optional;

public class ProjectSaveEvent extends ProjectEvent {

    public ProjectSaveEvent(Component source, Optional<Project> project) {
        super(source, project);
    }
}
