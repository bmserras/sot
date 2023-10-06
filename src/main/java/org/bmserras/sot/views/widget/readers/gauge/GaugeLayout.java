package org.bmserras.sot.views.widget.readers.gauge;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import org.bmserras.sot.data.domain.readers.Gauge;
import org.bmserras.sot.data.domain.readers.ValueReader;
import org.bmserras.sot.views.widget.readers.ValueReaderLayout;

public class GaugeLayout extends ValueReaderLayout {

    private GaugeForm gaugeForm = new GaugeForm();
    private GaugePreview gaugePreview;

    public GaugeLayout() {
        Gauge gauge = new Gauge();
        gaugeForm.setGauge(gauge);

        gaugePreview = new GaugePreview(gauge);

        gaugeForm.addGaugeNameListener(event -> {
            event.getGauge().ifPresent(g -> gaugePreview.setName(g.getName()));
        });
        gaugeForm.addGaugeMinListener(event -> {
            event.getGauge().ifPresent(g -> gaugePreview.setMin(g.getMin()));
        });
        gaugeForm.addGaugeMaxListener(event -> {
            event.getGauge().ifPresent(g -> gaugePreview.setMax(g.getMax()));
        });

        add(gaugeForm, gaugePreview);
    }

    @Override
    public ValueReader getValueReader() {
        return gaugeForm.getGauge();
    }
}
