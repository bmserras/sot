package org.bmserras.sot.events;

import com.vaadin.flow.component.Component;
import org.bmserras.sot.data.entity.synoptic.Synoptic;

import java.util.Optional;

public class RemoveEvent extends SynopticEvent {

    public RemoveEvent(Component source, Optional<Synoptic> synoptic) {
        super(source, synoptic);
    }
}
