package org.bmserras.sot.views.widget.component;

import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.*;
import com.vaadin.flow.component.charts.model.style.SolidColor;
import com.vaadin.flow.component.html.Div;
import org.bmserras.sot.data.domain.readers.SolidGauge;

public class SolidGaugeComponent extends Div {

    private final Chart solidGaugeChart = new Chart(ChartType.SOLIDGAUGE);
    private final DataSeries series = new DataSeries("Speed");
    private final DataSeriesItem item = new DataSeriesItem();

    private final SolidGauge solidGauge;

    public SolidGaugeComponent(SolidGauge solidGauge) {
        this.solidGauge = solidGauge;

        Configuration configuration = solidGaugeChart.getConfiguration();
        configuration.getChart().setWidth(300);
        configuration.getChart().setHeight(String.valueOf(300));

        Pane pane = configuration.getPane();
        pane.setStartAngle(-90);
        pane.setEndAngle(90);

        Background paneBackground = new Background();
        paneBackground.setInnerRadius("60%");
        paneBackground.setOuterRadius("100%");
        paneBackground.setShape(BackgroundShape.ARC);
        pane.setBackground(paneBackground);
        paneBackground.setBackgroundColor(SolidColor.RED);

        YAxis yAxis = configuration.getyAxis();
        yAxis.setTickAmount(2);
        yAxis.setTitle(solidGauge.getName());
        yAxis.setMinorTickInterval("null");
        yAxis.getTitle().setY(50);
        yAxis.getLabels().setY(16);
        yAxis.setMin(solidGauge.getMin());
        yAxis.setMax(solidGauge.getMax());

        item.setY(getRandomNumber(solidGauge.getMin(), solidGauge.getMax()));
        DataLabels dataLabelsSeries = new DataLabels();

        item.setDataLabels(dataLabelsSeries);

        series.add(item);
        configuration.addSeries(series);

        add(solidGaugeChart);
    }

    public void refreshValue() {
        item.setY(getRandomNumber(solidGauge.getMin(), solidGauge.getMax()));
        series.update(item);
    }

    public void setName(String title) {
        solidGaugeChart.getConfiguration().setTitle(title);
        solidGaugeChart.drawChart();
    }

    public void setMin(int min) {
        solidGaugeChart.getConfiguration().getyAxis().setMin(min);
        refreshValue();
        solidGaugeChart.drawChart();
    }

    public void setMax(int max) {
        solidGaugeChart.getConfiguration().getyAxis().setMax(max);
        refreshValue();
        solidGaugeChart.drawChart();
    }

    public void setColor(String color) {
        solidGaugeChart.getConfiguration().getPane().getBackground()[0].setBackgroundColor(SolidColor.RED);
        solidGaugeChart.drawChart();
    }

    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
