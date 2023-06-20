package org.bmserras.sot.views.synoptic;

import com.vaadin.flow.component.Component;
import org.bmserras.sot.views.AbsoluteLayout;

public class SynopticCanvas extends AbsoluteLayout {

    public SynopticCanvas() {

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
