package org.bmserras.sot.expcharts;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.charts.model.TickPosition;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import org.bmserras.sot.views.layout.AppLayoutNavbar;

import java.util.Arrays;

@PageTitle("Charts View")
@Route(value = "charts", layout = AppLayoutNavbar.class)
@PermitAll
public class ChartsView extends FlexLayout {

    public ChartsView() {
        setSizeFull();
        setFlexDirection(FlexDirection.ROW);

        MyGauge myGauge = new MyGauge();

        VerticalLayout form = new VerticalLayout();
        form.addClassName("my-form");

        TextField title = new TextField("Title", e -> myGauge.setTitle(e.getValue()));
        title.setValue("Chart");

        NumberField scale = new NumberField("Scale", e -> myGauge.setScale(e.getValue().intValue()));
        scale.setValue(5d);
        scale.setStepButtonsVisible(true);
        scale.setMin(0);
        scale.setMax(10);

        TextField unit = new TextField("Unit", e -> myGauge.setUnit(e.getValue()));
        unit.setValue("Unit");

        NumberField minValue = new NumberField("Minimum", e -> myGauge.setMin(e.getValue().intValue()));
        minValue.setValue(0d);
        minValue.setStepButtonsVisible(true);

        NumberField maxValue = new NumberField("Maximum", e -> myGauge.setMax(e.getValue().intValue()));
        maxValue.setValue(100d);
        maxValue.setStepButtonsVisible(true);

        ComboBox<TickPosition> tickPosition = new ComboBox<>("Tick position", e -> myGauge.setTickPosition(e.getValue()));
        tickPosition.setItems(TickPosition.values());
        tickPosition.setValue(TickPosition.INSIDE);

        NumberField tickLength = new NumberField("Tick length", e -> myGauge.setTickLength(e.getValue().intValue()));
        tickLength.setValue(10d);
        tickLength.setStepButtonsVisible(true);
        tickLength.setMin(0);
        tickLength.setMax(20);

        ComboBox<String> tickColor = new ComboBox<>("Tick color", e -> myGauge.setTickColor(e.getValue()));
        tickColor.setItems(Arrays.asList("red", "green", "blue", "yellow", "purple", "black", "white"));
        tickColor.setValue("black");

        Checkbox rotateLabels = new Checkbox("Rotate labels", e -> myGauge.setRotateLabels(e.getValue()));
        rotateLabels.setValue(false);

        Button refresh = new Button("Refresh", e -> myGauge.refreshValue());

        form.setAlignItems(Alignment.END);
        form.add(title, scale, unit, minValue, maxValue, tickPosition, tickLength, tickColor, rotateLabels, refresh);

        add(myGauge, form);
    }
}
