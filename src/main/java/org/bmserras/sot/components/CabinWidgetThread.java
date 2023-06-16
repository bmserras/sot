package org.bmserras.sot.components;

import com.vaadin.flow.component.PropertyDescriptor;
import com.vaadin.flow.component.PropertyDescriptors;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.littemplate.LitTemplate;
import org.bmserras.sot.api.Event;
import org.bmserras.sot.api.Service;
import org.bmserras.sot.views.helloworld.HelloWorldView;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@JsModule("./src/cabin-widget.js")
@Tag("cabin-widget")
public class CabinWidgetThread extends LitTemplate {

    private final Service service = new Service();



    private static final PropertyDescriptor<String, String> nameProperty = PropertyDescriptors.propertyWithDefault(
            "name",
            "Cabin name");

    private static final PropertyDescriptor<Integer, Integer> batteryProperty = PropertyDescriptors.propertyWithDefault(
            "battery",
            0);

    private static final PropertyDescriptor<Boolean, Boolean> hasWarningProperty = PropertyDescriptors.propertyWithDefault(
            "hasWarning",
            false);

    public CabinWidgetThread() {
        addClassName("cabin-widget");
    }

    public void start() {
        schedule();
    }

    /*public void stop() {
        executor.shutdown();
    }*/

    public String getName() {
        return nameProperty.get(this);
    }

    public void setName(String name) {
        nameProperty.set(this, name);
    }

    public int getBattery() {
        return batteryProperty.get(this);
    }

    public void setBattery(int battery) {
        batteryProperty.set(this, battery);
    }

    public boolean getWarning() {
        return hasWarningProperty.get(this);
    }

    public void setWarning(boolean hasWarning) {
        hasWarningProperty.set(this, hasWarning);
    }

    private void schedule() {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(() -> {
            try {
                Event latestData = service.getLatestData(10558);
                this.getUI().get().access(() -> {
                    int latestValue = Integer.parseInt(latestData.getResult().get(0).getValue());
                    this.setBattery(latestValue);
                    this.setWarning(latestValue < 20);
                    //System.out.println("#######################################");
                });
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, 500, 1000, TimeUnit.MILLISECONDS);
    }

}
