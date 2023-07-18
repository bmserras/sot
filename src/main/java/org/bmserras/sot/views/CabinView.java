package org.bmserras.sot.views;

import com.vaadin.flow.component.UIDetachedException;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.security.AuthenticationContext;
import jakarta.annotation.security.PermitAll;
import org.bmserras.sot.api.Event;
import org.bmserras.sot.api.Service;
import org.bmserras.sot.views.example.cabingauge.SolidGaugeWidget;
import org.bmserras.sot.views.layout.MainLayout;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@PageTitle("Cabin View")
@Route(value = "cabin", layout = MainLayout.class)
@PermitAll
public class CabinView extends VerticalLayout implements HasUrlParameter<String> {

    private final SolidGaugeWidget batteryGauge;
    private final SolidGaugeWidget voltageGauge;
    private final SolidGaugeWidget temperatureGauge;

    private final Service service;

    private int hostId;

    public CabinView() {
        setSizeFull();

        service = new Service();

        HorizontalLayout gaugesLayout = new HorizontalLayout();
        gaugesLayout.setHeight("200px");
        //expand(gaugesLayout);


        batteryGauge = new SolidGaugeWidget("Battery", 0, 0, 100);
        voltageGauge = new SolidGaugeWidget("Voltage", 0, 0, 500);
        temperatureGauge = new SolidGaugeWidget("Temperature", 0, 0, 100);
        gaugesLayout.add(batteryGauge, voltageGauge, temperatureGauge);

        HorizontalLayout actuatorsLayouts = new HorizontalLayout();
        actuatorsLayouts.setWidth(200, Unit.PIXELS);
        actuatorsLayouts.setHeight(200, Unit.PIXELS);

        VerticalLayout fanLayout = new VerticalLayout();

        Image image = new Image("icons/fan-blades-icon.svg", "fan");
        image.setWidth(300, Unit.PIXELS);
        image.setHeight(300, Unit.PIXELS);

        fanLayout.add(new Image("icons/fan-blades-icon.svg", "fan"), new Button("Turn on"));
        fanLayout.setAlignItems(Alignment.CENTER);
        actuatorsLayouts.add(fanLayout);
        expand(actuatorsLayouts);
        add(gaugesLayout/*, actuatorsLayouts*/);
    }

    private void schedule() {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(() -> {
            try {
                Event latestData = service.getLatestData(hostId);
                getUI().ifPresent(ui -> ui.access(() -> {
                    batteryGauge.setValue(service.getBattery(latestData));
                    voltageGauge.setValue(service.getVoltage(latestData));
                    temperatureGauge.setValue(service.getTemperature(latestData));
                }));
            } catch (Exception e) {
                //e.printStackTrace();
            }
        }, 500, 1000, TimeUnit.MILLISECONDS);
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, String parameter) {
        hostId  = Integer.parseInt(parameter);
        schedule();
    }

}
