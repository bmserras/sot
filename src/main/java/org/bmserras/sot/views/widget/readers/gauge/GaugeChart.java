package org.bmserras.sot.views.widget.readers.gauge;

import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.*;
import com.vaadin.flow.component.charts.model.style.SolidColor;
import com.vaadin.flow.component.html.Div;
import org.bmserras.sot.data.domain.readers.GaugeData;

public class GaugeChart extends Div {

    private final Chart chart = new Chart(ChartType.GAUGE);
    private final ListSeries series = new ListSeries();

    // default props:
    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;

    private static final String NAME = "Default gauge";

    private static final String UNIT = "unit";
    private static final int UNIT_MIN = 0;
    private static final int UNIT_MAX = 100;

    private static final int TICK_LENGTH = 10;
    private static final TickPosition TICK_POSITION = TickPosition.OUTSIDE;
    private static final String TICK_COLOR = "black";

    private static final int MINOR_TICK_LENGTH = 10;
    private static final TickPosition MINOR_TICK_POSITION = TickPosition.INSIDE;

    private static final int BORDER_WIDTH_PIXELS = 5;
    private static final String BORDER_STYLE = "solid";

    private final GaugeData gaugeData;
    public GaugeChart() {
        this(new GaugeData(
                NAME,
                UNIT,
                UNIT_MIN,
                UNIT_MAX,
                WIDTH,
                HEIGHT,
                TICK_LENGTH,
                false,
                TICK_COLOR,
                MINOR_TICK_LENGTH,
                true,
                BORDER_WIDTH_PIXELS,
                BORDER_STYLE
        ));
    }

    public GaugeChart(GaugeData gaugeData) {
        this.gaugeData = gaugeData;
        Configuration configuration = configureGauge(gaugeData.getName(), gaugeData.getWidth(), gaugeData.getHeight());
        configuration.addyAxis(configureYAxis(
                gaugeData.getUnit(),
                gaugeData.getUnitMin(),
                gaugeData.getUnitMax(),
                gaugeData.getTickLength(),
                gaugeData.isTickPosition() ? TickPosition.INSIDE : TickPosition.OUTSIDE,
                gaugeData.getTickColor(),
                gaugeData.getMinorTickLength(),
                gaugeData.isMinorTickPosition() ? TickPosition.INSIDE : TickPosition.OUTSIDE));
        configuration.addSeries(configureSeries());

        getStyle().set("border-color", "#33ccff");

        setBorderStyle(gaugeData.getBorderStyle());
        setBorderWidth(gaugeData.getBorderWidth());

        add(chart);
    }

    private void setBorderStyle(String style) {
        getStyle().set("border-style", style);
        gaugeData.setBorderStyle(style);
    }

    private void setBorderWidth(String width) {
        getStyle().set("border-width", width + "px");
        gaugeData.setBorderWidthPixels(Integer.parseInt(width));
    }

    private Configuration configureGauge(String title, int width, int height) {
        Configuration configuration = chart.getConfiguration();
        configuration.setTitle(title);
        configuration.getChart().setWidth(width);
        configuration.getChart().setHeight(String.valueOf(height));

        Pane pane = configuration.getPane();
        pane.setStartAngle(-150);
        pane.setEndAngle(150);

        return configuration;
    }

    private YAxis configureYAxis(String unit, int unitMin, int unitMax, int tickLength, TickPosition tickPosition, String tickColor, int minorTickLength, TickPosition minorTickPosition) {
        YAxis yAxis = new YAxis();
        yAxis.setTitle(unit);
        yAxis.setMin(unitMin);
        yAxis.setMax(unitMax);

        yAxis.setTickLength(tickLength);
        yAxis.setTickPosition(tickPosition);
        yAxis.setTickColor(new SolidColor(tickColor));

        yAxis.setMinorTickLength(minorTickLength);
        yAxis.setMinorTickPosition(minorTickPosition);

        Labels labels = new Labels();
        labels.setStep(1); // from main tick to main tick
        //labels.setRotation("auto"); // whether to rotate the labels
        yAxis.setLabels(labels);

        return yAxis;
    }

    private ListSeries configureSeries() {
        series.setName("Name");
        series.setData(65);
        return series;
    }

    public void refreshValue() {
        series.updatePoint(0, getRandomNumber(
                chart.getConfiguration().getyAxis().getMin().intValue(),
                chart.getConfiguration().getyAxis().getMax().intValue())
        );
    }

    public void setValue(int value) {
        series.updatePoint(0, value);
    }

    public void setTitle(String title) {
        gaugeData.setName(title);
        chart.getConfiguration().setTitle(title);
        chart.drawChart();
    }

    public void setScale(int scale) {
        int size = scaleToRange(scale);
        chart.getConfiguration().getChart().setWidth(size);
        chart.getConfiguration().getChart().setHeight(String.valueOf(size));
        chart.drawChart();
        gaugeData.setWidth(size);
        gaugeData.setHeight(size);
    }

    public void setUnit(String unit) {
        gaugeData.setUnit(unit);
        chart.getConfiguration().getyAxis().setTitle(unit);
        chart.drawChart();
    }

    public void setMin(int min) {
        gaugeData.setUnitMin(min);
        chart.getConfiguration().getyAxis().setMin(min);
        refreshValue();
        chart.drawChart();
    }

    public void setMax(int max) {
        gaugeData.setUnitMax(max);
        chart.getConfiguration().getyAxis().setMax(max);
        refreshValue();
        chart.drawChart();
    }

    public void setTickPosition(TickPosition position) {
        gaugeData.setTickPosition(position == TickPosition.INSIDE);
        chart.getConfiguration().getyAxis().setTickPosition(position);
        chart.drawChart();
    }

    public void setTickLength(int length) {
        gaugeData.setTickLength(length);
        chart.getConfiguration().getyAxis().setTickLength(length);
        chart.drawChart();
    }

    public void setTickColor(String value) {
        gaugeData.setTickColor(value);
        chart.getConfiguration().getyAxis().setTickColor(new SolidColor(value));
        chart.drawChart();
    }

    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    private int scaleToRange(int scale) {
        int inputMin = 0;
        int inputMax = 10;
        int outputMin = 300;
        int outputMax = 500;
        return (scale - inputMin) * (outputMax - outputMin) / (inputMax - inputMin) + outputMin;
    }

    public void setRotateLabels(Boolean rotate) {
        Labels labels = chart.getConfiguration().getyAxis().getLabels();
        if (rotate)
            labels.setRotation("auto");
        else
            labels.setRotation("");
        chart.drawChart();
    }

    public GaugeData getGaugeData() {
        return gaugeData;
    }
}
