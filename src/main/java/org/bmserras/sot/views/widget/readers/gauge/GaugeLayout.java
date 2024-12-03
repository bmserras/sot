package org.bmserras.sot.views.widget.readers.gauge;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.bmserras.sot.data.domain.readers.Reader;
import org.bmserras.sot.views.widget.readers.ReaderLayout;

public class GaugeLayout extends ReaderLayout {

    GaugeChart gaugeChart = new GaugeChart();

    public GaugeLayout() {
        setSizeFull();

        GaugeContainer gaugeContainer = new GaugeContainer(gaugeChart);
        GaugeForm gaugeForm = new GaugeForm(gaugeChart);

        Div div = new Div(gaugeContainer);
        setAlignSelf(Alignment.START, div);
        expand();
        add(new Div(gaugeForm), new VerticalLayout(new H4("Preview"), div));
    }

    @Override
    public Reader getReader() {
        return gaugeChart.getGaugeData();
    }
}
