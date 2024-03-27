package org.bmserras.sot.events.widgetinstance;

import com.vaadin.flow.component.Component;
import org.bmserras.sot.data.domain.WidgetInstance;

import java.util.Optional;

public class WidgetInstanceDeleteEvent extends WidgetInstanceEvent {

    public WidgetInstanceDeleteEvent(Component source, Optional<WidgetInstance> widgetInstance) {
        super(source, widgetInstance);
    }
}
