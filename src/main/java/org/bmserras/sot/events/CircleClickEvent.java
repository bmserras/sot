package org.bmserras.sot.events;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.DomEvent;
import org.bmserras.sot.components.CabinWidget;

@DomEvent("circle-click")
public class CircleClickEvent extends ComponentEvent<CabinWidget> {

    public CircleClickEvent(CabinWidget source, boolean fromClient) {
        super(source, fromClient);
    }
}
