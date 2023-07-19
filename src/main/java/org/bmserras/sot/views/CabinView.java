package org.bmserras.sot.views;

import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import jakarta.annotation.security.PermitAll;
import org.bmserras.sot.api.Event;
import org.bmserras.sot.api.Service;
import org.bmserras.sot.views.example.cabingauge.SolidGaugeWidget;
import org.bmserras.sot.views.layout.MainLayout;

import java.util.HashMap;
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
    private int temperatureId;
    private int voltageId;
    private int batteryId;

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

                    int battery = service.getValueFromLatestData(latestData,
                            batteryId);
                    int voltage = service.getValueFromLatestData(latestData,
                            voltageId);
                    int temperature = service.getValueFromLatestData(latestData,
                            temperatureId);

                    batteryGauge.setValue(battery);
                    voltageGauge.setValue(voltage);
                    temperatureGauge.setValue(temperature);
                }));
            } catch (Exception e) {
                //e.printStackTrace();
            }
        }, 500, 1000, TimeUnit.MILLISECONDS);
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, @OptionalParameter String parameter) {
        System.out.println(parameter);

        String[] parameterSplit = parameter.split("\\?");
        String param = parameterSplit[0];
        String query = parameterSplit[1];

        hostId  = Integer.parseInt(param);

        HashMap<String, String> pairsMap = new HashMap<>();
        String[] pairs = query.split("&");
        for (String pair : pairs) {
            String[] nameValueSplit = pair.split("=");
            String name = nameValueSplit[0];
            String value = nameValueSplit[1];
            pairsMap.put(name, value);
        }

        temperatureId = Integer.parseInt(pairsMap.get("temperatureId"));
        voltageId = Integer.parseInt(pairsMap.get("voltageId"));
        batteryId = Integer.parseInt(pairsMap.get("batteryId"));

        // For some reason, there are some unexpected bugs here
        /*
        Location location = beforeEvent.getLocation();
        QueryParameters queryParameters = location.getQueryParameters();

        Map<String, List<String>> parametersMap = queryParameters.getParameters();
        System.out.println(parametersMap);

        temperatureId = Integer.parseInt(parametersMap.get("temperatureId").get(0));
        voltageId = Integer.parseInt(parametersMap.get("voltageId").get(0));
        batteryId = Integer.parseInt(parametersMap.get("batteryId").get(0));
        */

        schedule();
    }

}
