package org.bmserras.sot.events.synoptic;

import com.vaadin.flow.component.Component;
import org.bmserras.sot.data.db.synoptic.Synoptic;

import java.util.Optional;

public class SynopticRemoveEvent extends SynopticEvent {

    public SynopticRemoveEvent(Component source, Optional<Synoptic> synoptic) {
        super(source, synoptic);
    }
}
