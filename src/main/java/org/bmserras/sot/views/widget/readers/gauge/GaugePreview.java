package org.bmserras.sot.views.widget.readers.gauge;

import com.vaadin.flow.component.html.Div;
import org.bmserras.sot.data.domain.readers.Gauge;

public class GaugePreview extends Div {

    private final GaugeComponent gaugeComponent;

    public GaugePreview(Gauge gauge) {
        this.gaugeComponent = new GaugeComponent(gauge);
        add(gaugeComponent);
    }

    public void setName(String name) {
        gaugeComponent.setName(name);
    }

    public void setMin(int min) {
        gaugeComponent.setMin(min);
    }

    public void setMax(int max) {
        gaugeComponent.setMax(max);
    }
}
