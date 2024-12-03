package org.bmserras.sot.views.widget.readers.gauge;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class GaugeContainer extends VerticalLayout {

    public GaugeContainer(GaugeChart gaugeChart) {
        addClassName("button2-container");
        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);
        add(gaugeChart);
        gaugeChart.addClassName("in-gauge-container");
    }
}
