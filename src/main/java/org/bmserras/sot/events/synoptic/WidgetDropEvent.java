package org.bmserras.sot.events.synoptic;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import org.bmserras.sot.data.domain.Widget;

import java.util.Optional;

public class WidgetDropEvent extends ComponentEvent<Component> {

    private final Optional<Widget> widget;
    private final double x;
    private final double y;

    public WidgetDropEvent(Component source, Optional<Widget> widget, double x, double y) {
        super(source, false);
        this.widget = widget;
        this.x  = x;
        this.y  = y;
    }

    public Optional<Widget> getWidget() {
        return widget;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
