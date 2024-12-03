package org.bmserras.sot.views.widget.readers.solidgauge;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.bmserras.sot.data.domain.readers.Reader;
import org.bmserras.sot.views.widget.readers.ReaderLayout;

public class SolidGaugeLayout extends ReaderLayout {

    SolidGaugeChart solidGaugeChart = new SolidGaugeChart();

    public SolidGaugeLayout() {
        setSizeFull();

        SolidGaugeContainer solidGaugeContainer = new SolidGaugeContainer(solidGaugeChart);
        SolidGaugeForm solidGaugeForm = new SolidGaugeForm(solidGaugeChart);

        Div div = new Div(solidGaugeContainer);
        setAlignSelf(Alignment.START, div);
        expand();
        add(new Div(solidGaugeForm), new VerticalLayout(new H4("Preview"), div));
    }

    @Override
    public Reader getReader() {
        return solidGaugeChart.getSolidGaugeData();
    }
}
