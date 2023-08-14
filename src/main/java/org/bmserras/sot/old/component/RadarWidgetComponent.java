package org.bmserras.sot.old.component;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.shared.Registration;
import org.bmserras.sot.old.service.WidgetService;
import org.bmserras.sot.old.views.RadarWidgetForm;
import org.bmserras.sot.old.views.WidgetForm;
import org.bmserras.sot.zabbix.api.Event;
import org.bmserras.sot.zabbix.api.Service;
import org.bmserras.sot.old.data.RadarWidget;
import org.bmserras.sot.old.events.WidgetRemoveEvent;
import org.bmserras.sot.test.cabingauge.SolidGaugeWidget;

import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@JsModule("./src/radar-widget.js")
@Tag("radar-widget")
public class RadarWidgetComponent extends WidgetComponent {

    private static final PropertyDescriptor<Double, Double> scaleProperty = PropertyDescriptors.propertyWithDefault(
            "scale",
            1.0);

    private static final PropertyDescriptor<Integer, Integer> warningProperty =
            PropertyDescriptors.propertyWithDefault(
                    "warning",
                    0);

    private final Dialog dialog = new Dialog("Edit widget");
    private final RadarWidgetForm form = new RadarWidgetForm();

    private final MenuItem editItem;
    private final MenuItem removeItem;
    private final MenuItem cabinItem;
    private final MenuItem cinemometerItem;

    private final RadarWidget radarWidget;

    private final SolidGaugeWidget batteryGauge;
    private final SolidGaugeWidget voltageGauge;
    private final SolidGaugeWidget temperatureGauge;

    public RadarWidgetComponent(WidgetService widgetService, RadarWidget radarWidget) {
        super(widgetService, radarWidget);
        setScale(0.8);
        setWarning(0);

        this.radarWidget = radarWidget;

        HorizontalLayout gaugesLayout = new HorizontalLayout();
        gaugesLayout.setHeight("200px");
        //expand(gaugesLayout);

        batteryGauge = new SolidGaugeWidget("Battery", 0, 0, 100);
        voltageGauge = new SolidGaugeWidget("Voltage", 0, 0, 500);
        temperatureGauge = new SolidGaugeWidget("Temperature", 0, 0, 100);
        gaugesLayout.add(batteryGauge, voltageGauge, temperatureGauge);

        Dialog dialogCabin = new Dialog();
        dialogCabin.setWidth("80%");
        dialogCabin.setHeight("80%");
        dialogCabin.add(gaugesLayout);

        int hostId = radarWidget.getZabbixConfig().getHostId();

        dialog.add(form);
        dialog.setWidth(30, Unit.PERCENTAGE);

        editItem = contextMenu.addItem(new HorizontalLayout(new Icon(VaadinIcon.EDIT), new Span("Edit")),
                click -> {
                    form.addSaveListener(this::saveWidget);
                    /*form.addDeleteListener(this::deleteWidget);*/
                    form.addCloseListener(e -> dialog.close());

                    form.setWidget(getWidget());
                    dialog.open();
                });
        removeItem = contextMenu.addItem(new HorizontalLayout(new Icon(VaadinIcon.CLOSE), new Span("Remove")),
                click -> fireEvent(new WidgetRemoveEvent(this, Optional.of(radarWidget))));
        contextMenu.add(new Hr());

        cabinItem = contextMenu.addItem("Cabin", click -> getUI().ifPresent(ui -> ui.navigate(
                "cabin/" + hostId
                        + "?temperatureId=" + radarWidget.getZabbixConfig().getTemperatureItemId()
                        + "&voltageId=" + radarWidget.getZabbixConfig().getVoltageItemId()
                        + "&batteryId=" + radarWidget.getZabbixConfig().getBatteryItemId()
        )));
        /*cabinItem = contextMenu.addItem("Cabin", click -> {
            dialogCabin.open();
        });*/

        cinemometerItem = contextMenu.addItem("Cinemometer", click -> getUI().ifPresent(ui -> ui.navigate("cinemometer")));

        cabinItem.setEnabled(false);
        cinemometerItem.setEnabled(false);
    }

    public Double getScale() {
        return scaleProperty.get(this);
    }

    public void setScale(double scale) {
        scaleProperty.set(this, scale);
    }

    public Integer getWarning() {
        return warningProperty.get(this);
    }

    public void setWarning(Integer warning) {
        warningProperty.set(this, warning);
    }

    private void saveWidget(WidgetForm.SaveEvent event) {
        widgetService.save(event.getWidget());
        update(event.getWidget().getName());
        dialog.close();
    }

    private void update(String name) {
        RadarWidget widgetByName = (RadarWidget) widgetService.findByName(name).get();
        setName(widgetByName.getName());
    }

    /*private void deleteWidget(WidgetForm.DeleteEvent event) {
        service.deleteWidget(event.getWidget());
        closeEditor();
    }*/

    @Override
    public void run() {
        schedule();
        editItem.setEnabled(false);
        removeItem.setEnabled(false);
        cabinItem.setEnabled(true);
        cinemometerItem.setEnabled(true);
    }

    @Override
    public void stop() {
        setWarning(0);
        executor.shutdown();
        editItem.setEnabled(true);
        removeItem.setEnabled(true);
        cabinItem.setEnabled(false);
        cinemometerItem.setEnabled(false);
    }

    private final Service service = new Service();

    private ScheduledExecutorService executor;

    private void schedule() {
        executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(() -> {
            try {
                int hostId = radarWidget.getZabbixConfig().getHostId();

                Event latestData = service.getLatestData(hostId);
                getUI().ifPresent(ui -> ui.access(() -> {
                    int battery = service.getValueFromLatestData(latestData,
                            radarWidget.getZabbixConfig().getBatteryItemId());
                    int voltage = service.getValueFromLatestData(latestData,
                            radarWidget.getZabbixConfig().getVoltageItemId());
                    int temperature = service.getValueFromLatestData(latestData,
                            radarWidget.getZabbixConfig().getTemperatureItemId());

                    if (battery <= 20 || voltage != 220 || temperature >= 40) {
                        setWarning(3);
                    } else {
                        setWarning(1);
                    }

                    batteryGauge.setValue(battery);
                    voltageGauge.setValue(voltage);
                    temperatureGauge.setValue(temperature);
                }));
            } catch (Exception e) {
                //e.printStackTrace();
            }
        }, 500, 1000, TimeUnit.MILLISECONDS);
    }

    public Registration addRemoveWidgetListener(ComponentEventListener<WidgetRemoveEvent> listener) {
        return addListener(WidgetRemoveEvent.class, listener);
    }
}
