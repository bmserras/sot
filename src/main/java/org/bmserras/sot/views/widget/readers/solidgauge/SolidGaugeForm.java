package org.bmserras.sot.views.widget.readers.solidgauge;

import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import org.bmserras.sot.data.domain.readers.SolidGauge;
import org.bmserras.sot.events.widget.solidgauge.SolidGaugeColorEvent;
import org.bmserras.sot.events.widget.solidgauge.SolidGaugeMaxEvent;
import org.bmserras.sot.events.widget.solidgauge.SolidGaugeMinEvent;
import org.bmserras.sot.events.widget.solidgauge.SolidGaugeNameEvent;

import java.util.Optional;

public class SolidGaugeForm extends VerticalLayout {

    private final NumberField identifier = new NumberField("Identifier");
    private TextField name = new TextField("Name");
    private IntegerField min = new IntegerField("Minimum value");
    private IntegerField max = new IntegerField("Maximum value");
    private TextField color = new TextField("Color");

    private Binder<SolidGauge> binder = new Binder<>(SolidGauge.class);

    public SolidGaugeForm() {
        this.setPadding(false);
        this.setSpacing(false);

        binder.bind(identifier, solidGauge -> (double) solidGauge.getId(), null);
        binder.bind(name, SolidGauge::getName, SolidGauge::setName);
        binder.bind(min, SolidGauge::getMin, SolidGauge::setMin);
        binder.bind(max, SolidGauge::getMax, SolidGauge::setMax);
        binder.bind(color, SolidGauge::getColor, SolidGauge::setColor);

        name.addValueChangeListener(event -> {
            fireEvent(new SolidGaugeNameEvent(this, Optional.of(binder.getBean())));
        });

        min.addValueChangeListener(event -> {
            fireEvent(new SolidGaugeMinEvent(this, Optional.of(binder.getBean())));
        });

        max.addValueChangeListener(event -> {
            fireEvent(new SolidGaugeMaxEvent(this, Optional.of(binder.getBean())));
        });

        color.addValueChangeListener(event -> {
            fireEvent(new SolidGaugeColorEvent(this, Optional.of(binder.getBean())));
        });

        add(identifier, name, min, max, color);
    }

    public void setSolidGauge(SolidGauge solidGauge) {
        binder.setBean(solidGauge);
    }

    public SolidGauge getSolidGauge() {
        return binder.getBean();
    }

    public void addSolidGaugeNameListener(ComponentEventListener<SolidGaugeNameEvent> listener) {
        addListener(SolidGaugeNameEvent.class, listener);
    }

    public void addSolidGaugeMinListener(ComponentEventListener<SolidGaugeMinEvent> listener) {
        addListener(SolidGaugeMinEvent.class, listener);
    }

    public void addSolidGaugeMaxListener(ComponentEventListener<SolidGaugeMaxEvent> listener) {
        addListener(SolidGaugeMaxEvent.class, listener);
    }

    public void addSolidGaugeColorListener(ComponentEventListener<SolidGaugeColorEvent> listener) {
        addListener(SolidGaugeColorEvent.class, listener);
    }
}
