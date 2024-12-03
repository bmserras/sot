package org.bmserras.sot.events.widget.writer;

import com.vaadin.flow.component.Component;

import java.util.Optional;

public class WriterCloseEvent extends WriterEvent {

    public WriterCloseEvent(Component source) {
        super(source, Optional.empty());
    }
}
