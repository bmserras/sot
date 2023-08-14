package org.bmserras.sot.test.gauge;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.*;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.bmserras.sot.views.layout.AppLayoutNavbar;

@PageTitle("Gauge")
@Route(value = "gauge", layout = AppLayoutNavbar.class)
public class GaugeView extends VerticalLayout {

    public GaugeView() {

        Chart speedometerChart = new Chart(ChartType.GAUGE);

        Configuration speedometerChartConf = speedometerChart.getConfiguration();
        speedometerChartConf.setTitle("Speedometer");
        speedometerChartConf.getPane().setStartAngle(-135);
        speedometerChartConf.getPane().setEndAngle(135);

        ListSeries series = new ListSeries("Speed", 80);
        speedometerChartConf.addSeries(series);

        YAxis yaxis = new YAxis();
        yaxis.setTitle("km/h");

        // The limits are mandatory
        yaxis.setMin(0);
        yaxis.setMax(100);

        // Other configuration
        yaxis.getLabels().setStep(1);
        yaxis.setTickInterval(10);
        yaxis.setTickLength(10);
        yaxis.setTickWidth(1);
        yaxis.setMinorTickInterval("1");
        yaxis.setMinorTickLength(5);
        yaxis.setMinorTickWidth(1);

        PlotBand green = new PlotBand(0, 60, null);
        green.setClassName("green");
        PlotBand yellow = new PlotBand(60, 80, null);
        yellow.setClassName("yellow");
        PlotBand red = new PlotBand(80, 100, null);
        red.setClassName("red");

        yaxis.setPlotBands(green, yellow, red);

        speedometerChartConf.addyAxis(yaxis);

        TextField newValueTextField = new TextField("Enter a new value");

        Button updateValueButton = new Button("Update", e -> {
            Integer newValue = Integer.valueOf(newValueTextField.getValue());
            series.updatePoint(0, newValue);
        });

        add(speedometerChart, newValueTextField, updateValueButton);
    }
}
