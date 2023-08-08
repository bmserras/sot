package org.bmserras.sot.events.project;

import com.vaadin.flow.component.Component;
import org.bmserras.sot.events.project.ProjectEvent;

import java.util.Optional;

public class ProjectCloseEvent extends ProjectEvent {

    public ProjectCloseEvent(Component source) {
        super(source, Optional.empty());
    }
}
