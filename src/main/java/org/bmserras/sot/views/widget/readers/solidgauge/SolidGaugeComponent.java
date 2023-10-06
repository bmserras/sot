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
        configuration.setTitle(solidGauge.getName());

        configuration.getChart().setWidth(300);
        configuration.getChart().setHeight(String.valueOf(300));

        Pane pane = configuration.getPane();
        pane.setSize("125%");           // For positioning tick labels
        pane.setCenter("50%", "70%"); // Move center lower
        pane.setStartAngle(-90);
        pane.setEndAngle(90);

        Background paneBackground = new Background();
        paneBackground.setInnerRadius("60%");
        paneBackground.setOuterRadius("100%");
        paneBackground.setShape(BackgroundShape.ARC);
        pane.setBackground(paneBackground);

        YAxis yaxis = configuration.getyAxis();
        yaxis.setTitle(solidGauge.getName());
        yaxis.getTitle().setY(-80); // Move 80 px upwards from center

        yaxis.setStartOnTick(true);
        yaxis.setEndOnTick(true);

        yaxis.setTickAmount(99);

        // The limits are mandatory
        yaxis.setMin(solidGauge.getMin());
        yaxis.setMax(solidGauge.getMax());

        //yaxis.setTickAmount(null);
        yaxis.setTickInterval(null);
        yaxis.setTickLength(20);
        yaxis.setTickColor(SolidColor.RED);

        //yaxis.getLabels().setY(16);

        yaxis.setMinorTickPosition(TickPosition.OUTSIDE);
        yaxis.setMinorTickInterval(null);
        yaxis.setMinorTickColor(SolidColor.GREEN);

        // Configure ticks and labels
        //yaxis.setTickInterval(100);  // At 0, 100, and 200
        //yaxis.getLabels().setY(16); // Move 16 px upwards
        //yaxis.setGridLineWidth(0); // Disable grid

        /*YAxis yAxis = configuration.getyAxis();
        yAxis.setTickAmount(2);
        yAxis.setTitle(solidGauge.getName());
        yAxis.setMinorTickInterval("null");
        yAxis.getTitle().setY(50);
        yAxis.getLabels().setY(16);
        yAxis.setMin(solidGauge.getMin());
        yAxis.setMax(solidGauge.getMax());*/

        item.setY(getRandomNumber(solidGauge.getMin(), solidGauge.getMax()));
        DataLabels dataLabelsSeries = new DataLabels();

        item.setDataLabels(dataLabelsSeries);

        series.add(item);
        configuration.addSeries(series);

        add(solidGaugeChart);
    }

    private void refreshValue() {
        item.setY(getRandomNumber(solidGaugeChart.getConfiguration().getyAxis().getMin().intValue(),
                solidGaugeChart.getConfiguration().getyAxis().getMax().intValue()));
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
