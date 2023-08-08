package org.bmserras.sot.events.widget;

import com.vaadin.flow.component.Component;
import org.bmserras.sot.data.entity.widget.Widget;

import java.util.Optional;

public class RemoveWidgetEvent extends WidgetEvent {

    public RemoveWidgetEvent(Component source, Optional<Widget> widget) {
        super(source, widget);
    }
}
