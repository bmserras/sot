package org.bmserras.sot.events.widget.reader;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import org.bmserras.sot.data.domain.readers.ValueReaderType;

import java.util.Optional;

public class ReaderTypeEvent extends ComponentEvent<Component> {

    private final Optional<ValueReaderType> type;

    public ReaderTypeEvent(Component source, Optional<ValueReaderType> type) {
        super(source, false);
        this.type = type;
    }

    public Optional<ValueReaderType> getType() {
        return type;
    }

}
