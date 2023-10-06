package org.bmserras.sot.events.widget.reader;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import org.bmserras.sot.data.domain.readers.ValueReader;

import java.util.Optional;

public class ReaderEvent extends ComponentEvent<Component> {

    private final Optional<ValueReader> reader;

    public ReaderEvent(Component source, Optional<ValueReader> reader) {
        super(source, false);
        this.reader = reader;
    }

    public Optional<ValueReader> getReader() {
        return reader;
    }

}