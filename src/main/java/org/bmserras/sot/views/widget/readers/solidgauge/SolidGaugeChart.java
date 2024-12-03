package org.bmserras.sot.views.widget.readers.solidgauge;

import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.*;
import com.vaadin.flow.component.charts.model.style.SolidColor;
import com.vaadin.flow.component.html.Div;
import org.bmserras.sot.data.domain.readers.Reader;
import org.bmserras.sot.data.domain.readers.SolidGaugeData;

public class SolidGaugeChart extends Div {

    private final Chart chart = new Chart(ChartType.SOLIDGAUGE);
    private final DataSeries series = new DataSeries();
    private final DataSeriesItem item = new DataSeriesItem();

    // default props:
    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;

    private static final String NAME = "Default solid gauge";

    private static final String UNIT = "unit";
    private static final int UNIT_MIN = 0;
    private static final int UNIT_MAX = 100;
    private static final int BORDER_WIDTH_PIXELS = 5;
    private static final String BORDER_STYLE = "solid";

    private final SolidGaugeData solidGaugeData;

    public SolidGaugeChart() {
        this(new SolidGaugeData(
                NAME,
                UNIT,
                UNIT_MIN,
                UNIT_MAX,
                WIDTH,
                HEIGHT,
                BORDER_WIDTH_PIXELS,
                BORDER_STYLE
        ));
    }

    public SolidGaugeChart(SolidGaugeData solidGaugeData) {
        this.solidGaugeData = solidGaugeData;
        Configuration configuration = configureSolidGauge(solidGaugeData.getName());
        configuration.addyAxis(configureYAxis(
                solidGaugeData.getUnit(),
                solidGaugeData.getUnitMin(),
                solidGaugeData.getUnitMax()
        ));
        configuration.addSeries(configureSeries());

        getStyle().set("border-color", "#33ccff");

        setBorderStyle(solidGaugeData.getBorderStyle());
        setBorderWidth(solidGaugeData.getBorderWidthPixels());

        add(chart);
    }

    private void setBorderStyle(String borderStyle) {
        getStyle().set("border-style", borderStyle);
        solidGaugeData.setBorderStyle(borderStyle);
    }

    private void setBorderWidth(int borderWidth) {
        getStyle().set("border-width", borderWidth + "px");
        solidGaugeData.setBorderWidthPixels(borderWidth);
    }

    private Configuration configureSolidGauge(String name) {
        Configuration configuration = chart.getConfiguration();
        configuration.setTitle(name);
        configuration.getChart().setWidth(WIDTH);
        configuration.getChart().setHeight(String.valueOf(HEIGHT));

        Pane pane = configuration.getPane();
        pane.setSize("90%");           // For positioning tick labels
        pane.setCenter("50%", "70%"); // Move center lower
        pane.setStartAngle(-90);
        pane.setEndAngle(90);

        Background paneBackground = new Background();
        paneBackground.setInnerRadius("60%");
        paneBackground.setOuterRadius("100%");
        paneBackground.setShape(BackgroundShape.ARC);
        pane.setBackground(paneBackground);

        return configuration;
    }

    private YAxis configureYAxis(String unit, int unitMin, int unitMax) {
        YAxis yAxis = new YAxis();
        yAxis.setTitle(unit);
        yAxis.setMin(unitMin);
        yAxis.setMax(unitMax);

        Labels labels = new Labels();
        labels.setStep(1); // from main tick to main tick
        //labels.setRotation("auto"); // whether to rotate the labels
        yAxis.setLabels(labels);

        yAxis.getTitle().setY(-80); // Move 80 px upwards from center

        yAxis.setStartOnTick(true);
        yAxis.setEndOnTick(true);

        //yAxis.setTickAmount(99);

        // The limits are mandatory
        yAxis.setMin(unitMin);
        yAxis.setMax(unitMax);

        //yaxis.setTickAmount(null);
        yAxis.setTickInterval(null);
        yAxis.setTickLength(20);
        yAxis.setTickColor(SolidColor.RED);

        //yaxis.getLabels().setY(16);

        yAxis.setMinorTickPosition(TickPosition.INSIDE);
        yAxis.setMinorTickInterval(null);
        yAxis.setMinorTickColor(SolidColor.BLACK);

        return yAxis;
    }

    private DataSeries configureSeries() {
        series.setName("Name");
        series.setData(500);
        return series;
    }

    public void refreshValue() {
        item.setY(getRandomNumber(
                chart.getConfiguration().getyAxis().getMin().intValue(),
                chart.getConfiguration().getyAxis().getMax().intValue()));
        series.update(item);
    }

    public void setTitle(String title) {
        chart.getConfiguration().setTitle(title);
        chart.drawChart();
        solidGaugeData.setName(title);
    }

    public void setScale(int scale) {
        int size = scaleToRange(scale);
        chart.getConfiguration().getChart().setWidth(size);
        chart.getConfiguration().getChart().setHeight(String.valueOf(size));
        chart.drawChart();
    }

    public void setUnit(String unit) {
        chart.getConfiguration().getyAxis().setTitle(unit);
        chart.drawChart();
        solidGaugeData.setUnit(unit);
    }

    public void setMin(int min) {
        chart.getConfiguration().getyAxis().setMin(min);
        refreshValue();
        chart.drawChart();
        solidGaugeData.setUnitMin(min);
    }

    public void setMax(int max) {
        chart.getConfiguration().getyAxis().setMax(max);
        refreshValue();
        chart.drawChart();
        solidGaugeData.setUnitMax(max);
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

    public Reader getSolidGaugeData() {
        return solidGaugeData;
    }
}
