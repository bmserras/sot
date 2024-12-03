package org.bmserras.sot.events.widget.writer;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import org.bmserras.sot.data.domain.writers.Writer;
import org.bmserras.sot.data.domain.writers.Writer;

import java.util.Optional;

public class WriterEvent extends ComponentEvent<Component> {

    private final Optional<Writer> writer;

    public WriterEvent(Component source, Optional<Writer> writer) {
        super(source, false);
        this.writer = writer;
    }

    public Optional<Writer> getWriter() {
        return writer;
    }

}
