package org.bmserras.sot.views.widget.readers.gauge;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.charts.model.TickPosition;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;

import java.util.Arrays;

public class GaugeForm extends Scroller {

    public GaugeForm(GaugeChart gaugeChart) {

        setScrollDirection(ScrollDirection.VERTICAL);
        setHeight("800px");

        getStyle().set("margin", "var(--lumo-space-xs)");

        TextField title = new TextField("Title", e -> gaugeChart.setTitle(e.getValue()));
        title.setValue("Chart");

        NumberField scale = new NumberField("Scale", e -> gaugeChart.setScale(e.getValue().intValue()));
        scale.setValue(5d);
        scale.setStepButtonsVisible(true);
        scale.setMin(0);
        scale.setMax(10);

        TextField unit = new TextField("Unit", e -> gaugeChart.setUnit(e.getValue()));
        unit.setValue("Unit");

        NumberField minValue = new NumberField("Minimum", e -> gaugeChart.setMin(e.getValue().intValue()));
        minValue.setValue(0d);
        minValue.setStepButtonsVisible(true);

        NumberField maxValue = new NumberField("Maximum", e -> gaugeChart.setMax(e.getValue().intValue()));
        maxValue.setValue(100d);
        maxValue.setStepButtonsVisible(true);

        ComboBox<TickPosition> tickPosition = new ComboBox<>("Tick position", e -> gaugeChart.setTickPosition(e.getValue()));
        tickPosition.setItems(TickPosition.values());
        tickPosition.setValue(TickPosition.INSIDE);

        NumberField tickLength = new NumberField("Tick length", e -> gaugeChart.setTickLength(e.getValue().intValue()));
        tickLength.setValue(10d);
        tickLength.setStepButtonsVisible(true);
        tickLength.setMin(0);
        tickLength.setMax(20);

        ComboBox<String> tickColor = new ComboBox<>("Tick color", e -> gaugeChart.setTickColor(e.getValue()));
        tickColor.setItems(Arrays.asList("red", "green", "blue", "yellow", "purple", "black", "white"));
        tickColor.setValue("black");

        Checkbox rotateLabels = new Checkbox("Rotate labels", e -> gaugeChart.setRotateLabels(e.getValue()));
        rotateLabels.setValue(false);

        Button refresh = new Button("Refresh", e -> {
            gaugeChart.refreshValue();
            System.out.println(gaugeChart.getGaugeData());
        });

        ComboBox<TickPosition> minorTickPosition = new ComboBox<>("Minor tick position", e -> gaugeChart.setTickPosition(e.getValue()));
        minorTickPosition.setItems(TickPosition.values());
        minorTickPosition.setValue(TickPosition.INSIDE);

        NumberField minorTickLength = new NumberField("Minor tick length", e -> gaugeChart.setTickLength(e.getValue().intValue()));
        minorTickLength.setValue(10d);
        minorTickLength.setStepButtonsVisible(true);
        minorTickLength.setMin(0);
        minorTickLength.setMax(20);

        ComboBox<String> minorTickColor = new ComboBox<>("Minor tick color", e -> gaugeChart.setTickColor(e.getValue()));
        minorTickColor.setItems(Arrays.asList("red", "green", "blue", "yellow", "purple", "black", "white"));
        minorTickColor.setValue("black");

        NumberField borderWidth = new NumberField("Border width (px)", e -> gaugeChart.getStyle().set("border-width", e.getValue() + "px"));
        borderWidth.setValue(5d);
        borderWidth.setStepButtonsVisible(true);
        borderWidth.setMin(0);
        borderWidth.setMax(10);

        ComboBox<String> borderStyle = new ComboBox<>("Border style", "solid", "dotted", "dashed");
        borderStyle.addValueChangeListener(e -> gaugeChart.getStyle().set("border-style", e.getValue()));
        borderStyle.setValue("solid");

        VerticalLayout customization = new VerticalLayout(new H4("Customization"), title, scale, borderWidth, borderStyle);
        customization.setSpacing(false);

        VerticalLayout chart = new VerticalLayout(new H4("Chart"), unit, minValue, maxValue, tickPosition, tickLength, tickColor, minorTickPosition, minorTickLength, minorTickColor, rotateLabels, refresh);
        chart.setSpacing(false);

        IntegerField minStatus = new IntegerField("Minimum value");
        IntegerField maxStatus = new IntegerField("Maximum value");
        IntegerField marginStatus = new IntegerField("Margin");
        Checkbox propagateStatus = new Checkbox("Propagate");
        VerticalLayout status = new VerticalLayout(new H4("Status"), minStatus, maxStatus, marginStatus, propagateStatus);
        status.setSpacing(false);

        setContent(new HorizontalLayout(customization, chart, status));

    }
}
