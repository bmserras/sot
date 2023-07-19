package org.bmserras.sot.events;

import com.vaadin.flow.component.Component;
import org.bmserras.sot.data.entity.synoptic.Synoptic;

import java.util.Optional;

public class CloseEvent extends SynopticEvent {

    public CloseEvent(Component source) {
        super(source, Optional.empty());
    }
}
