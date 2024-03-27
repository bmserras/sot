package org.bmserras.sot.expoutlines;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.bmserras.sot.expcharts.MyGauge;

public class Space extends VerticalLayout {

    public Space() {

        addClassName("space");
        setPadding(false);
        //setWidth("200px");
        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);

        add(new MyGauge());

    }
}
