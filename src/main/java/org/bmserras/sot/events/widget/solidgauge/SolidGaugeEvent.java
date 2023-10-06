package org.bmserras.sot.events.widget.solidgauge;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import org.bmserras.sot.data.domain.readers.SolidGauge;

import java.util.Optional;

public class SolidGaugeEvent extends ComponentEvent<Component> {

    private final Optional<SolidGauge> solidGauge;

    public SolidGaugeEvent(Component source, Optional<SolidGauge> solidGauge) {
        super(source, false);
        this.solidGauge = solidGauge;
    }

    public Optional<SolidGauge> getSolidGauge() {
        return solidGauge;
    }

}
