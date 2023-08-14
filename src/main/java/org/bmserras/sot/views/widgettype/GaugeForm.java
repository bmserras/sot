package org.bmserras.sot.views.widgettype;

import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import org.bmserras.sot.data.db.widgettype.Gauge;

public class GaugeForm extends FormLayout {

    private final TextField name = new TextField("Name");
    private final NumberField minValue = new NumberField("Minimum value");
    private final NumberField maxValue = new NumberField("Maximum value");
    private final TextField color = new TextField("Color");
    private final Select<String> type = new Select<>();

    private final Binder<Gauge> binder = new Binder<>(Gauge.class);

    public GaugeForm() {

        type.setLabel("Type");
        type.setItems("T1", "T2", "T3");

        binder.bindInstanceFields(this);

        add(name, minValue, maxValue, color, type);
    }

    public Gauge getGauge() {
        return binder.getBean();
    }

    public void setGauge(Gauge gauge) {
        binder.setBean(gauge);
    }
}
