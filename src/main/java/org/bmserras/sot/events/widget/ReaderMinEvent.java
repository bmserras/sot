package org.bmserras.sot.events.widget;

import com.vaadin.flow.component.Component;
import org.bmserras.sot.data.domain.Project;
import org.bmserras.sot.data.domain.ValueReader;
import org.bmserras.sot.events.project.ProjectEvent;

import java.util.Optional;

public class ReaderMinEvent extends ReaderEvent {

    public ReaderMinEvent(Component source, Optional<ValueReader> reader) {
        super(source, reader);
    }
}
