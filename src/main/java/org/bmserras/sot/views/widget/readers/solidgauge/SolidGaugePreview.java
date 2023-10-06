package org.bmserras.sot.views.widget.readers.solidgauge;

import com.vaadin.flow.component.html.Div;
import org.bmserras.sot.data.domain.readers.Gauge;
import org.bmserras.sot.data.domain.readers.SolidGauge;
import org.bmserras.sot.views.widget.component.GaugeComponent;
import org.bmserras.sot.views.widget.component.SolidGaugeComponent;

public class SolidGaugePreview extends Div {

    private final SolidGaugeComponent solidGaugeComponent;

    public SolidGaugePreview(SolidGauge solidGauge) {
        this.solidGaugeComponent = new SolidGaugeComponent(solidGauge);
        add(solidGaugeComponent);
    }

    public void setName(String name) {
        solidGaugeComponent.setName(name);
    }

    public void setMin(int min) {
        solidGaugeComponent.setMin(min);
    }

    public void setMax(int max) {
        solidGaugeComponent.setMax(max);
    }

    public void setColor(String color) {
        solidGaugeComponent.setColor(color);
    }
}
