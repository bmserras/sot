package org.bmserras.sot.events.synoptic;

import com.vaadin.flow.component.Component;
import org.bmserras.sot.data.domain.Synoptic;

import java.util.Optional;

public class SynopticAddEvent extends SynopticEvent {

    public SynopticAddEvent(Component source) {
        super(source, Optional.empty());
    }
}
