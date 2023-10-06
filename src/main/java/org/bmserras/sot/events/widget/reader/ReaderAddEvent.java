package org.bmserras.sot.events.widget.reader;

import com.vaadin.flow.component.Component;

import java.util.Optional;

public class ReaderAddEvent extends ReaderEvent {

    public ReaderAddEvent(Component source) {
        super(source, Optional.empty());
    }
}
