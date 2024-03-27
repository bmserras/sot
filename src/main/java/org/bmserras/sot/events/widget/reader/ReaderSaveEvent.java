package org.bmserras.sot.events.widget.reader;

import com.vaadin.flow.component.Component;
import org.bmserras.sot.data.domain.readers.Reader;

import java.util.Optional;

public class ReaderSaveEvent extends ReaderEvent {

    public ReaderSaveEvent(Component source, Optional<Reader> reader) {
        super(source, reader);
    }
}
