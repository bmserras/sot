package org.bmserras.sot.events.widget;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import org.bmserras.sot.data.domain.Widget;

import java.util.Optional;

public class WidgetEvent extends ComponentEvent<Component> {

    private final Optional<Widget> widget;

    public WidgetEvent(Component source, Optional<Widget> widget) {
        super(source, false);
        this.widget = widget;
    }

    public Optional<Widget> getWidget() {
        return widget;
    }

}
