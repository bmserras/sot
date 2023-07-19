package org.bmserras.sot.events;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import org.bmserras.sot.data.entity.synoptic.Synoptic;

import java.util.Optional;

public class SynopticEvent extends ComponentEvent<Component> {

    private final Optional<Synoptic> synoptic;

    public SynopticEvent(Component source, Optional<Synoptic> synoptic) {
        super(source, false);
        this.synoptic = synoptic;
    }

    public Optional<Synoptic> getSynoptic() {
        return synoptic;
    }

}
