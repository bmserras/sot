package org.bmserras.sot.ops;

import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.*;
import jakarta.annotation.security.PermitAll;
import org.bmserras.sot.data.domain.readers.Gauge;
import org.bmserras.sot.data.domain.readers.SolidGauge;
import org.bmserras.sot.data.service.SynopticService;
import org.bmserras.sot.data.service.WidgetService;
import org.bmserras.sot.views.layout.AppLayoutNavbar;
import org.bmserras.sot.views.widget.readers.gauge.GaugeComponent;
import org.bmserras.sot.views.widget.readers.solidgauge.SolidGaugeComponent;

@PageTitle("Synoptic View (Operations)")
@Route(value = "ops/synoptic", layout = AppLayoutNavbar.class)
@PermitAll
public class OpsSynopticView extends HorizontalLayout implements HasUrlParameter<String>, AfterNavigationObserver {

    private final TextField synopticName = new TextField();

    private final SynopticService synopticService;

    public OpsSynopticView(SynopticService synopticService) {
        this.synopticService = synopticService;

        add(synopticName);
        System.out.println("CTOR");
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, String parameter) {
        System.out.println("PARAM");
        synopticService.findById(parameter).ifPresent(synoptic -> synopticName.setValue(synoptic.getName()));
    }

    @Override
    public void afterNavigation(AfterNavigationEvent afterNavigationEvent) {
        System.out.println("AFTER");
        System.out.println(synopticName.getValue());
        synopticService.findByName(synopticName.getValue()).ifPresent(s -> {

            s.getWidgetInstances().forEach(widgetInstance -> {

                Span span = new Span(widgetInstance.getName());
                ContextMenu contextMenu = new ContextMenu(span);
                widgetInstance.getWidget().getReaders().forEach(reader -> {
                    if (reader instanceof SolidGauge solidGauge) {
                        contextMenu.add(new SolidGaugeComponent(solidGauge));
                    }
                    else if (reader instanceof Gauge gauge) {
                        contextMenu.add(new GaugeComponent(gauge));
                    }
                });
                add(span);

            });

        });
    }
}
