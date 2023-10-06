package org.bmserras.sot.views.widget.readers.gauge;

import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import org.bmserras.sot.data.domain.readers.Gauge;
import org.bmserras.sot.events.widget.ReaderMaxEvent;
import org.bmserras.sot.events.widget.ReaderMinEvent;
import org.bmserras.sot.events.widget.ReaderNameEvent;
import org.bmserras.sot.events.widget.gauge.GaugeMaxEvent;
import org.bmserras.sot.events.widget.gauge.GaugeMinEvent;
import org.bmserras.sot.events.widget.gauge.GaugeNameEvent;

import java.util.Optional;

public class GaugeForm extends VerticalLayout {

    private final NumberField identifier = new NumberField("Identifier");
    private TextField name = new TextField("Name");
    private IntegerField min = new IntegerField("Minimum value");
    private IntegerField max = new IntegerField("Maximum value");

    private Binder<Gauge> binder = new Binder<>(Gauge.class);

    public GaugeForm() {
        this.setPadding(false);
        this.setSpacing(false);

        binder.bind(identifier, gauge -> (double) gauge.getId(), null);
        binder.bind(name, Gauge::getName, Gauge::setName);
        binder.bind(min, Gauge::getMin, Gauge::setMin);
        binder.bind(max, Gauge::getMax, Gauge::setMax);

        name.addValueChangeListener(event -> {
            fireEvent(new GaugeNameEvent(this, Optional.of(binder.getBean())));
        });

        min.addValueChangeListener(event -> {
            fireEvent(new GaugeMinEvent(this, Optional.of(binder.getBean())));
        });

        max.addValueChangeListener(event -> {
            fireEvent(new GaugeMaxEvent(this, Optional.of(binder.getBean())));
        });

        add(identifier, name, min, max);
    }

    public void setGauge(Gauge gauge) {
        binder.setBean(gauge);
    }

    public void addGaugeNameListener(ComponentEventListener<GaugeNameEvent> listener) {
        addListener(GaugeNameEvent.class, listener);
    }

    public void addGaugeMinListener(ComponentEventListener<GaugeMinEvent> listener) {
        addListener(GaugeMinEvent.class, listener);
    }

    public void addGaugeMaxListener(ComponentEventListener<GaugeMaxEvent> listener) {
        addListener(GaugeMaxEvent.class, listener);
    }
}
