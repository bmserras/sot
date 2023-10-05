package org.bmserras.sot.events.widget;

import com.vaadin.flow.component.Component;
import org.bmserras.sot.data.domain.ValueReader;

import java.util.Optional;

public class ReaderNameEvent extends ReaderEvent {

    public ReaderNameEvent(Component source, Optional<ValueReader> reader) {
        super(source, reader);
    }
}
