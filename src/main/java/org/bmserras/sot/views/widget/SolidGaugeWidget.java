package org.bmserras.sot.views.widget;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.*;
import com.vaadin.flow.component.html.Div;
import org.bmserras.sot.views.widget.IReader;

public class SolidGaugeWidget extends Div implements IReader {

    private final Chart solidGauge = new Chart(ChartType.SOLIDGAUGE);

    DataSeries series = new DataSeries("Speed");
    DataSeriesItem item = new DataSeriesItem();

    public SolidGaugeWidget(String title, int initialValue, int minValue, int maxValue, int width, int height) {
        this.setWidth("350px");
        this.setHeight("100px");

        Configuration configuration = solidGauge.getConfiguration();
        configuration.getChart().setWidth(width);
        configuration.getChart().setHeight(String.valueOf(height));

        Pane pane = configuration.getPane();
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
        yAxis.getTitle().setY(50);
        yAxis.getLabels().setY(16);
        yAxis.setMin(minValue);
        yAxis.setMax(maxValue);

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

    @Override
    public Component getComponent() {
        return this;
    }

    public String getName() {
        return solidGauge.getConfiguration().getTitle().getText();
    }

    public void setName(String title) {
        solidGauge.getConfiguration().setTitle(title);
        solidGauge.drawChart();
    }

    public int getMin() {
        return solidGauge.getConfiguration().getyAxis().getMin().intValue();
    }

    public void setMin(int min) {
        solidGauge.getConfiguration().getyAxis().setMin(min);
        solidGauge.drawChart();
    }

    public int getMax() {
        return solidGauge.getConfiguration().getyAxis().getMax().intValue();
    }

    public void setMax(int max) {
        solidGauge.getConfiguration().getyAxis().setMax(max);
        solidGauge.drawChart();
    }
}
