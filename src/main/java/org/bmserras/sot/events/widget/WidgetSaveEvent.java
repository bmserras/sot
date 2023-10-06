package org.bmserras.sot.events.widget;

import com.vaadin.flow.component.Component;
import org.bmserras.sot.data.domain.Widget;

import java.util.Optional;

public class WidgetSaveEvent extends WidgetEvent {

    public WidgetSaveEvent(Component source, Optional<Widget> widget) {
        super(source, widget);
    }
}
