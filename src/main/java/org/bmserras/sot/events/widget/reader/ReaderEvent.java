package org.bmserras.sot.events.widget.reader;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import org.bmserras.sot.data.domain.readers.Reader;

import java.util.Optional;

public class ReaderEvent extends ComponentEvent<Component> {

    private final Optional<Reader> reader;

    public ReaderEvent(Component source, Optional<Reader> reader) {
        super(source, false);
        this.reader = reader;
    }

    public Optional<Reader> getReader() {
        return reader;
    }

}
