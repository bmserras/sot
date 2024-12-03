package org.bmserras.sot.views.widget.readers.solidgauge;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;

public class SolidGaugeForm extends Scroller {

    public SolidGaugeForm(SolidGaugeChart gaugeChart) {

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

        Button refresh = new Button("Refresh", e -> {
            gaugeChart.refreshValue();
            System.out.println(gaugeChart.getSolidGaugeData());
        });

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

        VerticalLayout chart = new VerticalLayout(new H4("Chart"), unit, minValue, maxValue, refresh);
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
