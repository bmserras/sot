package org.bmserras.sot.events.widget.solidgauge;

import com.vaadin.flow.component.Component;
import org.bmserras.sot.data.domain.readers.SolidGaugeData;

import java.util.Optional;

public class SolidGaugeColorEvent extends SolidGaugeEvent {

    public SolidGaugeColorEvent(Component source, Optional<SolidGaugeData> solidGauge) {
        super(source, solidGauge);
    }
}
