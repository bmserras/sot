package org.bmserras.sot.views.widget.readers.solidgauge;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import org.bmserras.sot.data.domain.readers.SolidGauge;

public class SolidGaugeLayout extends HorizontalLayout {

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

}
