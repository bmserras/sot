package org.bmserras.sot.events.widget.reader;

import com.vaadin.flow.component.Component;
import org.bmserras.sot.data.domain.readers.Reader;

import java.util.Optional;

public class ReaderDeleteEvent extends ReaderEvent {

    public ReaderDeleteEvent(Component source, Optional<Reader> reader) {
        super(source, reader);
    }
}
