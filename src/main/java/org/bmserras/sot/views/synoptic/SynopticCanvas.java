package org.bmserras.sot.views.synoptic;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import org.bmserras.sot.components.WidgetComponent;
import org.bmserras.sot.views.layout.AbsoluteLayout;

public class SynopticCanvas extends AbsoluteLayout {

    public SynopticCanvas() {

        UI.getCurrent().getPage().executeJs("let l=window.Vaadin.Flow.dndConnector.__ondropListener; " +
                "window.Vaadin.Flow.dndConnector.__ondropListener=function(e){" +
                "window.dragDropX=e.clientX;" +
                "window.dragDropY=e.clientY;" +
                "l(e); " +
                "}");


    }

    public void add(Component widget, int position) {
        switch (position) {
            case 1 -> add(widget, 0, 0);
            case 2 -> add(widget, 300, 0);
            case 3 -> add(widget, 600, 0);
            case 4 -> add(widget, 900, 0);
            case 5 -> add(widget, 0, 300);
            case 6 -> add(widget, 300, 300);
            case 7 -> add(widget, 600, 300);
            case 8 -> add(widget, 900, 300);
            default -> throw new IllegalStateException("Unexpected value: " + position);
        }
    }
}
