package org.bmserras.sot.events;

import com.vaadin.flow.component.Component;
import org.bmserras.sot.data.entity.synoptic.Synoptic;

import java.util.Optional;

public class MapEvent extends SynopticEvent {

    public MapEvent(Component source, Optional<Synoptic> synoptic) {
        super(source, synoptic);
    }
}
