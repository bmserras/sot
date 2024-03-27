package org.bmserras.sot.events.widgetinstance;

import com.vaadin.flow.component.Component;
import org.bmserras.sot.events.project.ProjectEvent;

import java.util.Optional;

public class WidgetInstanceCloseEvent extends WidgetInstanceEvent {

    public WidgetInstanceCloseEvent(Component source) {
        super(source, Optional.empty());
    }
}
