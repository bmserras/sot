package org.bmserras.sot.events.synoptic;

import com.vaadin.flow.component.Component;
import org.bmserras.sot.data.domain.Synoptic;

import java.util.Optional;

public class SynopticExportEvent extends SynopticEvent {

    public SynopticExportEvent(Component source, Optional<Synoptic> synoptic) {
        super(source, synoptic);
    }
}
