package org.bmserras.sot.events.synoptic;

import com.vaadin.flow.component.Component;

import java.util.Optional;

public class SynopticCloseEvent extends SynopticEvent {

    public SynopticCloseEvent(Component source) {
        super(source, Optional.empty());
    }
}
