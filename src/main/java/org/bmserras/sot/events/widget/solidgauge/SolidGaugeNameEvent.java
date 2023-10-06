package org.bmserras.sot.events.widget.solidgauge;

import com.vaadin.flow.component.Component;
import org.bmserras.sot.data.domain.readers.SolidGauge;

import java.util.Optional;

public class SolidGaugeNameEvent extends SolidGaugeEvent {

    public SolidGaugeNameEvent(Component source, Optional<SolidGauge> solidGauge) {
        super(source, solidGauge);
    }
}
