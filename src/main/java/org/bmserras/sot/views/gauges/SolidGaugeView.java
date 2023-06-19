package org.bmserras.sot.views.gauges;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.*;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.bmserras.sot.views.MainLayout;

@PageTitle("Solid Gauge")
@Route(value = "solid-gauge", layout = MainLayout.class)
public class SolidGaugeView extends VerticalLayout {

    public SolidGaugeView() {

        Chart chart = new Chart(ChartType.SOLIDGAUGE);

        Configuration configuration = chart.getConfiguration();

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
        yAxis.setTitle("Speed");
        yAxis.setMinorTickInterval("null");
        yAxis.getTitle().setY(-50);
        yAxis.getLabels().setY(16);
        yAxis.setMin(0);
        yAxis.setMax(200);


        PlotOptionsSolidgauge plotOptionsSolidgauge = new PlotOptionsSolidgauge();

        DataLabels dataLabels = plotOptionsSolidgauge.getDataLabels();
        dataLabels.setY(5);
        dataLabels.setUseHTML(true);

        configuration.setPlotOptions(plotOptionsSolidgauge);

        DataSeries series = new DataSeries("Speed (km/h)");

        DataSeriesItem item = new DataSeriesItem();
        item.setY(60);
        DataLabels dataLabelsSeries = new DataLabels();

        item.setDataLabels(dataLabelsSeries);

        series.add(item);

        configuration.addSeries(series);

        TextField newValueTextField = new TextField("Enter a new value");

        Button updateValueButton = new Button("Update", e -> {
            Integer newValue = Integer.valueOf(newValueTextField.getValue());
            item.setY(newValue);
            series.update(item);
        });

        add(chart, newValueTextField, updateValueButton);

        /*SolidGaugeWidget solidGaugeWidget = new SolidGaugeWidget("Speed", 100);

        add(solidGaugeWidget);

        Button updateValueButton = new Button("Update", e -> {
            solidGaugeWidget.setValue(10);
        });
        add(updateValueButton);*/

    }
}
