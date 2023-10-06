package org.bmserras.sot.events.widget.gauge;

import com.vaadin.flow.component.Component;
import org.bmserras.sot.data.domain.readers.Gauge;
import org.bmserras.sot.data.domain.readers.ValueReader;
import org.bmserras.sot.events.widget.ReaderEvent;

import java.util.Optional;

public class GaugeNameEvent extends GaugeEvent {

    public GaugeNameEvent(Component source, Optional<Gauge> gauge) {
        super(source, gauge);
    }
}
