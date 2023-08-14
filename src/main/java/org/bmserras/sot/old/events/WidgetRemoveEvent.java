package org.bmserras.sot.old.events;

import com.vaadin.flow.component.Component;
import org.bmserras.sot.old.data.Widget;

import java.util.Optional;

public class WidgetRemoveEvent extends WidgetEvent {

    public WidgetRemoveEvent(Component source, Optional<Widget> widget) {
        super(source, widget);
    }
}
