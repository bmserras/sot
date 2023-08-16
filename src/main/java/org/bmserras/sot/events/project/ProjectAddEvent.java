package org.bmserras.sot.events.project;

import com.vaadin.flow.component.Component;
import org.bmserras.sot.data.domain.Project;

import java.util.Optional;

public class ProjectAddEvent extends ProjectEvent {

    public ProjectAddEvent(Component source) {
        super(source, Optional.empty());
    }
}
