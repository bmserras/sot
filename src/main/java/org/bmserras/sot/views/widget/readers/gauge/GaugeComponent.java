package org.bmserras.sot.views.widget.readers.gauge;

import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.*;
import com.vaadin.flow.component.html.Div;
import org.bmserras.sot.data.domain.readers.Gauge;

public class GaugeComponent extends Div {

    private final Chart gaugeChart = new Chart(ChartType.GAUGE);
    private final ListSeries series = new ListSeries();

    private final Gauge gauge;

    public GaugeComponent(Gauge gauge) {

        this.gauge = gauge;

        Configuration configuration = gaugeChart.getConfiguration();
        configuration.setTitle(gauge.getName());
        configuration.getChart().setWidth(300);
        configuration.getChart().setHeight(String.valueOf(300));

        Pane pane = configuration.getPane();
        pane.setStartAngle(-150);
        pane.setEndAngle(150);

        YAxis yAxis = new YAxis();
        yAxis.setTitle("km/h");
        yAxis.setMin(gauge.getMin());
        yAxis.setMax(gauge.getMax());
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
        bands[0].setFrom(gauge.getMin());
        bands[0].setTo(gauge.getMax());
        bands[0].setClassName("band-0");
        yAxis.setPlotBands(bands);

        configuration.addyAxis(yAxis);

        series.setName(gauge.getName());
        series.setData(getRandomNumber(gauge.getMin(), gauge.getMax()));

        PlotOptionsGauge plotOptionsGauge = new PlotOptionsGauge();
        SeriesTooltip tooltip = new SeriesTooltip();
        tooltip.setValueSuffix(" km/h");
        plotOptionsGauge.setTooltip(tooltip);
        series.setPlotOptions(plotOptionsGauge);

        configuration.addSeries(series);

        add(gaugeChart);
    }

    private void refreshValue() {
        series.updatePoint(0, getRandomNumber(gaugeChart.getConfiguration().getyAxis().getMin().intValue(),
                gaugeChart.getConfiguration().getyAxis().getMax().intValue()));
    }

    public void setName(String name) {
        gaugeChart.getConfiguration().setTitle(name);
        gaugeChart.drawChart();
    }

    public void setMin(int min) {
        gaugeChart.getConfiguration().getyAxis().setMin(min);
        refreshValue();
        gaugeChart.drawChart();
    }

    public void setMax(int max) {
        gaugeChart.getConfiguration().getyAxis().setMax(max);
        refreshValue();
        gaugeChart.drawChart();
    }

    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

}
