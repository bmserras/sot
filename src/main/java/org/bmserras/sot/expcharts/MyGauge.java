package org.bmserras.sot.expcharts;

import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.*;
import com.vaadin.flow.component.charts.model.style.SolidColor;
import com.vaadin.flow.component.html.Div;

import java.awt.*;

public class MyGauge extends Div {

    private final Chart chart = new Chart(ChartType.GAUGE);
    private final ListSeries series = new ListSeries();

    private final int WIDTH = 200;
    private final int HEIGHT = 200;

    private final String TITLE = "Title";

    private final String UNIT = "unit";
    private final int UNIT_MIN = 0;
    private final int UNIT_MAX = 100;

    private final int TICK_LENGTH = 10;
    private final TickPosition TICK_POSITION = TickPosition.OUTSIDE;

    private final int NUMBER_OF_INTERVALS = 8;

    private final int MINOR_TICK_LENGTH = 10;
    private final TickPosition MINOR_TICK_POSITION = TickPosition.INSIDE;

    public MyGauge() {
        addClassName("my-gauge");

        Configuration configuration = configureGauge();
        configuration.addyAxis(configureYAxis());
        configuration.addSeries(configureSeries());

        add(chart);
    }

    private Configuration configureGauge() {
        Configuration configuration = chart.getConfiguration();
        configuration.setTitle(TITLE);
        configuration.getChart().setWidth(WIDTH);
        configuration.getChart().setHeight(String.valueOf(HEIGHT));

        Pane pane = configuration.getPane();
        pane.setStartAngle(-150);
        pane.setEndAngle(150);

        return configuration;
    }

    private YAxis configureYAxis() {
        YAxis yAxis = new YAxis();
        yAxis.setTitle(UNIT);
        yAxis.setMin(UNIT_MIN);
        yAxis.setMax(UNIT_MAX);

        yAxis.setTickLength(TICK_LENGTH);
        yAxis.setTickPosition(TICK_POSITION);
        yAxis.setTickColor(new SolidColor("#FF00FF"));

        yAxis.setMinorTickLength(MINOR_TICK_LENGTH);
        yAxis.setMinorTickPosition(MINOR_TICK_POSITION);

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

    public void setTitle(String title) {
        chart.getConfiguration().setTitle(title);
        chart.drawChart();
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
    }

    public void setMin(int min) {
        chart.getConfiguration().getyAxis().setMin(min);
        refreshValue();
        chart.drawChart();
    }

    public void setMax(int max) {
        chart.getConfiguration().getyAxis().setMax(max);
        refreshValue();
        chart.drawChart();
    }

    public void setTickPosition(TickPosition position) {
        chart.getConfiguration().getyAxis().setTickPosition(position);
        chart.drawChart();
    }

    public void setTickLength(int length) {
        chart.getConfiguration().getyAxis().setTickLength(length);
        chart.drawChart();
    }

    public void setTickColor(String value) {
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
        return  (scale - inputMin) * (outputMax - outputMin) / (inputMax - inputMin) + outputMin;
    }

    public void setRotateLabels(Boolean rotate) {
        Labels labels = chart.getConfiguration().getyAxis().getLabels();
        if (rotate)
            labels.setRotation("auto");
        else
            labels.setRotation("");
        chart.drawChart();
    }
}
