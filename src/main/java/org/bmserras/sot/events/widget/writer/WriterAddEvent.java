package org.bmserras.sot.events.widget.writer;

import com.vaadin.flow.component.Component;

import java.util.Optional;

public class WriterAddEvent extends WriterEvent {

    public WriterAddEvent(Component source) {
        super(source, Optional.empty());
    }
}
