package org.bmserras.sot.events.widget.writer;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import org.bmserras.sot.data.domain.writers.WriterType;
import org.bmserras.sot.data.domain.writers.WriterType;

import java.util.Optional;

public class WriterTypeEvent extends ComponentEvent<Component> {

    private final Optional<WriterType> type;

    public WriterTypeEvent(Component source, Optional<WriterType> type) {
        super(source, false);
        this.type = type;
    }

    public Optional<WriterType> getType() {
        return type;
    }

}
