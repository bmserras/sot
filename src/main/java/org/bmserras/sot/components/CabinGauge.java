package org.bmserras.sot.components;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import org.bmserras.sot.api.Event;
import org.bmserras.sot.api.Service;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CabinGauge extends HorizontalLayout {

    private final Service service = new Service();
    private final SolidGaugeWidget solidGaugeWidget;

    public CabinGauge() {
        solidGaugeWidget = new SolidGaugeWidget("Speed", 100);

        add(solidGaugeWidget);
    }

    public void start() {
        schedule();
    }

    private void schedule() {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(() -> {
            try {
                Event latestData = service.getLatestData(10558);
                this.getUI().get().access(() -> {
                    int latestValue = Integer.parseInt(latestData.getResult().get(0).getValue());
                    /*this.setBattery(latestValue);
                    this.setWarning(latestValue < 20);*/
                    //System.out.println("#######################################");
                    solidGaugeWidget.setValue(latestValue);
                });
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, 500, 1000, TimeUnit.MILLISECONDS);
    }
}
