package org.bmserras.sot.expoutlines;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class Grid extends VerticalLayout {

    public Grid(int rows, int ...cols) {

        setSizeFull();

        for (int i = 0; i < rows; i++) {
            HorizontalLayout horizontalLayout = new HorizontalLayout();
            horizontalLayout.setSizeFull();
            add(horizontalLayout);
            for (int j = 0; j < cols[i]; j++) {
                Space space = new Space();
                horizontalLayout.add(space);
            }
        }

    }
}
