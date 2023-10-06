package org.bmserras.sot.events.widget.gauge;

import com.vaadin.flow.component.Component;
import org.bmserras.sot.data.domain.readers.Gauge;

import java.util.Optional;

public class GaugeMinEvent extends GaugeEvent {

    public GaugeMinEvent(Component source, Optional<Gauge> gauge) {
        super(source, gauge);
    }
}
