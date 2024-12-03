package org.bmserras.sot.events.widget.solidgauge;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import org.bmserras.sot.data.domain.readers.SolidGaugeData;

import java.util.Optional;

public class SolidGaugeEvent extends ComponentEvent<Component> {

    private final Optional<SolidGaugeData> solidGauge;

    public SolidGaugeEvent(Component source, Optional<SolidGaugeData> solidGauge) {
        super(source, false);
        this.solidGauge = solidGauge;
    }

    public Optional<SolidGaugeData> getSolidGauge() {
        return solidGauge;
    }

}
