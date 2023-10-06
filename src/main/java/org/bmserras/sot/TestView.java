package org.bmserras.sot;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import org.bmserras.sot.views.components.CustomDialog;
import org.bmserras.sot.views.layout.AppLayoutNavbar;

@PageTitle("Test")
@Route(value = "test", layout = AppLayoutNavbar.class)
@PermitAll
public class TestView extends VerticalLayout {

    public TestView() {

        setPadding(false);

        /*SolidGauge solidGauge = new SolidGauge();
        SolidGaugeComponent solidGaugeComponent = new SolidGaugeComponent(solidGauge);
        NumberField numberField = new NumberField();
        numberField.addValueChangeListener(event -> solidGaugeComponent.setMin(event.getValue().intValue()));
        add(solidGaugeComponent, numberField);*/

        CustomDialog customDialog = new CustomDialog("Title", 50, 50);

        customDialog.addToHeader(new TextField(), new Select<>());

        customDialog.open();
    }
}
