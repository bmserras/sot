package org.bmserras.sot.test.events;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.DomEvent;
import org.bmserras.sot.test.cabinwidget.CabinWidget;

@DomEvent("rect-click")
public class RectClickEvent extends ComponentEvent<CabinWidget> {

    public RectClickEvent(CabinWidget source, boolean fromClient) {
        super(source, fromClient);
    }
}
