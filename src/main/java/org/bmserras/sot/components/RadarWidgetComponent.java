package org.bmserras.sot.components;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import org.bmserras.sot.api.Event;
import org.bmserras.sot.api.Service;
import org.bmserras.sot.data.entity.widget.RadarWidget;
import org.bmserras.sot.data.service.WidgetService;
import org.bmserras.sot.views.widget.RadarWidgetForm;
import org.bmserras.sot.views.widget.WidgetForm;

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

    public RadarWidgetComponent(WidgetService widgetService, RadarWidget radarWidget) {
        super(widgetService, radarWidget);
        setScale(0.8);
        setWarning(0);

        int hostId = switch (getName()) {
            case "IC19 6 D - Cabin" -> 10556;
            case "IC19 7 D - Cabin" -> 10557;
            case "A1 12 C - Cabin" -> 10558;
            default -> 0;
        };

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
                click -> {});
        contextMenu.add(new Hr());
        cabinItem = contextMenu.addItem("Cabin", click -> getUI().ifPresent(ui -> ui.navigate("cabin/" + hostId)));
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
        widgetService.saveWidget(event.getWidget());
        update(event.getWidget().getName());
        dialog.close();
    }

    private void update(String name) {
        RadarWidget widgetByName = (RadarWidget) widgetService.findWidgetByName(name);
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
                int hostId = switch (getName()) {
                    case "IC19 6 D - Cabin" -> 10556;
                    case "IC19 7 D - Cabin" -> 10557;
                    case "A1 12 C - Cabin" -> 10558;
                    default -> 0;
                };
                Event latestData = service.getLatestData(hostId);
                getUI().ifPresent(ui -> ui.access(() -> {
                    int battery = service.getBattery(latestData);
                    int voltage = service.getVoltage(latestData);
                    int temperature = service.getTemperature(latestData);
                    if (battery <= 20 || voltage != 220 || temperature > 40) {
                        setWarning(3);
                    } else {
                        setWarning(1);
                    }
                }));
            } catch (Exception e) {
                //e.printStackTrace();
            }
        }, 500, 1000, TimeUnit.MILLISECONDS);
    }
}
