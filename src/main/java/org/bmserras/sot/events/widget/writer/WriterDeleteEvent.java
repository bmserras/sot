package org.bmserras.sot.events.widget.writer;

import com.vaadin.flow.component.Component;
import org.bmserras.sot.data.domain.writers.Writer;
import org.bmserras.sot.data.domain.writers.Writer;

import java.util.Optional;

public class WriterDeleteEvent extends WriterEvent {

    public WriterDeleteEvent(Component source, Optional<Writer> writer) {
        super(source, writer);
    }
}
