package org.bmserras.sot.events.widget.reader;

import com.vaadin.flow.component.Component;
import org.bmserras.sot.data.domain.readers.ValueReader;

import java.util.Optional;

public class ReaderDeleteEvent extends ReaderEvent {

    public ReaderDeleteEvent(Component source, Optional<ValueReader> reader) {
        super(source, reader);
    }
}
