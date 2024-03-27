package org.bmserras.sot.events.widgetinstance;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import org.bmserras.sot.data.domain.WidgetInstance;

import java.util.Optional;

public class WidgetInstanceEvent extends ComponentEvent<Component> {

    private final Optional<WidgetInstance> widgetInstance;

    public WidgetInstanceEvent(Component source, Optional<WidgetInstance> widgetInstance) {
        super(source, false);
        this.widgetInstance = widgetInstance;
    }

    public Optional<WidgetInstance> getWidgetInstance() {
        return widgetInstance;
    }

}
