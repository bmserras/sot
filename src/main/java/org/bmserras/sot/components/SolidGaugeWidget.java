package org.bmserras.sot.components;

import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.*;
import com.vaadin.flow.component.html.Div;

public class SolidGaugeWidget extends Div {

    private final Chart solidGauge = new Chart(ChartType.SOLIDGAUGE);

    DataSeries series = new DataSeries("Speed");
    DataSeriesItem item = new DataSeriesItem();

    public SolidGaugeWidget(String title, int initialValue) {

        Configuration configuration = solidGauge.getConfiguration();

        Pane pane = configuration.getPane();
        pane.setCenter("50%", "50%");
        pane.setStartAngle(-90);
        pane.setEndAngle(90);

        Background paneBackground = new Background();
        paneBackground.setInnerRadius("60%");
        paneBackground.setOuterRadius("100%");
        paneBackground.setShape(BackgroundShape.ARC);
        pane.setBackground(paneBackground);

        YAxis yAxis = configuration.getyAxis();
        yAxis.setTickAmount(2);
        yAxis.setTitle(title);
        yAxis.setMinorTickInterval("null");
        yAxis.getTitle().setY(-50);
        yAxis.getLabels().setY(16);
        yAxis.setMin(0);
        yAxis.setMax(200);

        item.setY(initialValue);
        DataLabels dataLabelsSeries = new DataLabels();

        item.setDataLabels(dataLabelsSeries);

        series.add(item);
        configuration.addSeries(series);

        add(solidGauge);
    }

    public void setValue(int newValue) {
        item.setY(newValue);
        series.update(item);
    }
}
