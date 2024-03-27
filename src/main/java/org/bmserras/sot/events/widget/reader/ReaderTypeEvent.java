package org.bmserras.sot.events.widget.reader;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import org.bmserras.sot.data.domain.readers.ReaderType;

import java.util.Optional;

public class ReaderTypeEvent extends ComponentEvent<Component> {

    private final Optional<ReaderType> type;

    public ReaderTypeEvent(Component source, Optional<ReaderType> type) {
        super(source, false);
        this.type = type;
    }

    public Optional<ReaderType> getType() {
        return type;
    }

}
