package org.bmserras.sot.events.widget;

import com.vaadin.flow.component.Component;
import org.bmserras.sot.events.project.ProjectEvent;

import java.util.Optional;

public class WidgetCloseEvent extends WidgetEvent {

    public WidgetCloseEvent(Component source) {
        super(source, Optional.empty());
    }
}
