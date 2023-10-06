package org.bmserras.sot.events.widget.solidgauge;

import com.vaadin.flow.component.Component;
import org.bmserras.sot.data.domain.readers.SolidGauge;

import java.util.Optional;

public class SolidGaugeMinEvent extends SolidGaugeEvent {

    public SolidGaugeMinEvent(Component source, Optional<SolidGauge> solidGauge) {
        super(source, solidGauge);
    }
}