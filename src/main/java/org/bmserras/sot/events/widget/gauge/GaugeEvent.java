package org.bmserras.sot.events.widget.gauge;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import org.bmserras.sot.data.domain.readers.Gauge;

import java.util.Optional;

public class GaugeEvent extends ComponentEvent<Component> {

    private final Optional<Gauge> gauge;

    public GaugeEvent(Component source, Optional<Gauge> gauge) {
        super(source, false);
        this.gauge = gauge;
    }

    public Optional<Gauge> getGauge() {
        return gauge;
    }

}
