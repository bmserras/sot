package org.bmserras.sot.events.synoptic;

import com.vaadin.flow.component.Component;
import org.bmserras.sot.data.entity.synoptic.Synoptic;

import java.util.Optional;

public class SynopticMapEvent extends SynopticEvent {

    public SynopticMapEvent(Component source, Optional<Synoptic> synoptic) {
        super(source, synoptic);
    }
}
