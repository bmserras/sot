package org.bmserras.sot.views.widget.readers.solidgauge;

import org.bmserras.sot.data.domain.readers.SolidGauge;
import org.bmserras.sot.data.domain.readers.ValueReader;
import org.bmserras.sot.views.widget.readers.ValueReaderLayout;

public class SolidGaugeLayout extends ValueReaderLayout {

    private SolidGaugeForm solidGaugeForm = new SolidGaugeForm();
    private SolidGaugePreview solidGaugePreview;

    public SolidGaugeLayout() {

        SolidGauge solidGauge = new SolidGauge();
        solidGaugeForm.setSolidGauge(solidGauge);

        solidGaugePreview = new SolidGaugePreview(solidGauge);

        solidGaugeForm.addSolidGaugeNameListener(event -> {
            event.getSolidGauge().ifPresent(sg -> solidGaugePreview.setName(sg.getName()));
        });
        solidGaugeForm.addSolidGaugeMinListener(event -> {
            event.getSolidGauge().ifPresent(sg -> solidGaugePreview.setMin(sg.getMin()));
        });
        solidGaugeForm.addSolidGaugeMaxListener(event -> {
            event.getSolidGauge().ifPresent(sg -> solidGaugePreview.setMax(sg.getMax()));
        });
        solidGaugeForm.addSolidGaugeColorListener(event -> {
            event.getSolidGauge().ifPresent(sg -> solidGaugePreview.setColor(sg.getColor()));
        });

        add(solidGaugeForm, solidGaugePreview);
    }

    @Override
    public ValueReader getValueReader() {
        return solidGaugeForm.getSolidGauge();
    }
}
