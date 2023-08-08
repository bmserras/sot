package org.bmserras.sot.events.synoptic;

import com.vaadin.flow.component.Component;

import java.util.Optional;

public class CloseEvent extends SynopticEvent {

    public CloseEvent(Component source) {
        super(source, Optional.empty());
    }
}
