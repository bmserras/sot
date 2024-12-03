package org.bmserras.sot.views.widget.readers.solidgauge;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class SolidGaugeContainer extends VerticalLayout {

    public SolidGaugeContainer(SolidGaugeChart solidGaugeChart) {
        addClassName("button2-container");
        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);
        add(solidGaugeChart);
        solidGaugeChart.addClassName("in-gauge-container");
    }
}
