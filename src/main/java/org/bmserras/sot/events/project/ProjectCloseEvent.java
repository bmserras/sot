package org.bmserras.sot.events.project;

import com.vaadin.flow.component.Component;

import java.util.Optional;

public class ProjectCloseEvent extends ProjectEvent {

    public ProjectCloseEvent(Component source) {
        super(source, Optional.empty());
    }
}
