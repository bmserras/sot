package org.bmserras.sot.views.example.cabingauge;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.bmserras.sot.views.layout.MainLayout;

@PageTitle("CabinGauge View")
@Route(value = "cabin-gauge", layout = MainLayout.class)
public class CabinGaugeView extends HorizontalLayout {

    private final CabinGauge cabinGauge;

    public CabinGaugeView() {

        cabinGauge = new CabinGauge();

        cabinGauge.start();

        add(cabinGauge);
    }
}
