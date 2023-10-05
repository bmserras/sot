package org.bmserras.sot.test.cabingauge;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import org.bmserras.sot.views.widget.SolidGaugeWidget;
import org.bmserras.sot.zabbix.api.Event;
import org.bmserras.sot.zabbix.api.Service;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CabinGauge extends HorizontalLayout {

    private final Service service = new Service();
    private final SolidGaugeWidget solidGaugeWidget;

    public CabinGauge() {
        solidGaugeWidget = new SolidGaugeWidget("Battery", 100, 0, 100, 300, 300);

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
                this.getUI().ifPresent(ui -> ui.access(() -> {
                    int latestValue = Integer.parseInt(latestData.getResult().get(0).getValue());
                    solidGaugeWidget.setValue(latestValue);
                }));
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, 500, 1000, TimeUnit.MILLISECONDS);
    }
}
