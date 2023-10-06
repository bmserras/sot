package org.bmserras.sot.events.widget;

import com.vaadin.flow.component.Component;
import org.bmserras.sot.events.project.ProjectEvent;

import java.util.Optional;

public class WidgetAddEvent extends WidgetEvent {

    public WidgetAddEvent(Component source) {
        super(source, Optional.empty());
    }
}
