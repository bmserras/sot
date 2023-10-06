package org.bmserras.sot.events.widget.reader;

import com.vaadin.flow.component.Component;

import java.util.Optional;

public class ReaderCloseEvent extends ReaderEvent {

    public ReaderCloseEvent(Component source) {
        super(source, Optional.empty());
    }
}
