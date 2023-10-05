package org.bmserras.sot.views.widget;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.*;
import com.vaadin.flow.component.html.Div;
import org.bmserras.sot.views.widget.IReader;

public class GaugeWidget extends Div implements IReader {

    private final Chart gauge = new Chart(ChartType.GAUGE);

    ListSeries series = new ListSeries();

    public GaugeWidget(String title, int initialValue, int minValue, int maxValue, int width, int height) {
        //this.setWidth("350px");
        //this.setHeight("100px");

        Configuration configuration = gauge.getConfiguration();
        configuration.setTitle(title);
        configuration.getChart().setWidth(width);
        configuration.getChart().setHeight(String.valueOf(height));

        Pane pane = configuration.getPane();
        pane.setStartAngle(-150);
        pane.setEndAngle(150);

        YAxis yAxis = new YAxis();
        yAxis.setTitle("km/h");
        yAxis.setMin(minValue);
        yAxis.setMax(maxValue);
        yAxis.setTickLength(10);
        yAxis.setTickPixelInterval(30);
        yAxis.setTickPosition(TickPosition.INSIDE);
        yAxis.setMinorTickLength(10);
        yAxis.setMinorTickInterval("auto");
        yAxis.setMinorTickPosition(TickPosition.INSIDE);

        Labels labels = new Labels();
        labels.setStep(2);
        labels.setRotation("auto");
        yAxis.setLabels(labels);

        PlotBand[] bands = new PlotBand[1];
        bands[0] = new PlotBand();
        bands[0].setFrom(minValue);
        bands[0].setTo(maxValue);
        bands[0].setClassName("band-0");
        yAxis.setPlotBands(bands);

        configuration.addyAxis(yAxis);

        series.setName(title);
        series.setData(initialValue);

        PlotOptionsGauge plotOptionsGauge = new PlotOptionsGauge();
        SeriesTooltip tooltip = new SeriesTooltip();
        tooltip.setValueSuffix(" km/h");
        plotOptionsGauge.setTooltip(tooltip);
        series.setPlotOptions(plotOptionsGauge);

        configuration.addSeries(series);

        add(gauge);
    }

    public void setValue(int newValue) {
        series.updatePoint(0, newValue);
    }

    @Override
    public Component getComponent() {
        return this;
    }

    public String getName() {
        return gauge.getConfiguration().getTitle().getText();
    }

    public void setName(String title) {
        gauge.getConfiguration().setTitle(title);
        gauge.drawChart();
    }

    public int getMin() {
        return gauge.getConfiguration().getyAxis().getMin().intValue();
    }

    public void setMin(int min) {
        gauge.getConfiguration().getyAxis().setMin(min);
        gauge.drawChart();
    }

    public int getMax() {
        return gauge.getConfiguration().getyAxis().getMax().intValue();
    }

    public void setMax(int max) {
        gauge.getConfiguration().getyAxis().setMax(max);
        gauge.drawChart();
    }

}
