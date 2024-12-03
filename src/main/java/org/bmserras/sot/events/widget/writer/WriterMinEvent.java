package org.bmserras.sot.events.widget.writer;

import com.vaadin.flow.component.Component;
import org.bmserras.sot.data.domain.writers.Writer;
import org.bmserras.sot.data.domain.writers.Writer;

import java.util.Optional;

public class WriterMinEvent extends WriterEvent {

    public WriterMinEvent(Component source, Optional<Writer> writer) {
        super(source, writer);
    }
}
