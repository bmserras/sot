package org.bmserras.sot.views.helloworld;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.ChartType;
import com.vaadin.flow.component.charts.model.Configuration;
import com.vaadin.flow.component.charts.model.ListSeries;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.*;
import org.bmserras.sot.api.Event;
import org.bmserras.sot.api.Service;
import org.bmserras.sot.components.*;
import org.bmserras.sot.views.MainLayout;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@PageTitle("General View")
@Route(value = "hello", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class HelloWorldView extends HorizontalLayout implements HasUrlParameter<String> {

    private MyElement myElement;
    private MyTextField myTextField;
    private CabinWidget cabinWidget;

    private FeederThread feeder;

    public HelloWorldView() {

        /*setMargin(true);
        setPadding(true);

        myElement = new MyElement();

        myTextField = new MyTextField();

        cabinWidget = new CabinWidget();
        cabinWidget.setName("A1 km 12");
        add(cabinWidget);

        CabinWidget cabinWidget1 = new CabinWidget();
        CabinWidget cabinWidget2 = new CabinWidget();

        cabinWidget1.setBattery(30);
        cabinWidget1.setName("IC19 km 7");

        cabinWidget2.setBattery(50);
        cabinWidget2.setName("IC19 km 8");

        CabinWidgetThread cabinWidgetThread = new CabinWidgetThread();
        cabinWidgetThread.start();
        //add(cabinWidget1, cabinWidget2, cabinWidgetThread);

        CabinGauge cabinGauge = new CabinGauge();
        cabinGauge.start();
        add(cabinGauge);

        this.feeder = new FeederThread(UI.getCurrent(), myTextField, cabinWidget);
        this.feeder.start();*/
    }

    // This thread could be at CabinWidget API? start and stop it there
    private static class FeederThread extends Thread {

        private final UI ui;
        private final MyTextField myTextField;
        private final CabinWidget cabinWidget;

        private final Service service = new Service();

        public FeederThread(UI ui, MyTextField myTextField, CabinWidget cabinWidget) {
            this.ui = ui;
            this.myTextField = myTextField;
            this.cabinWidget = cabinWidget;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Event latestData = service.getLatestData(10558);
                    this.ui.access(() -> {
                        int latestValue = Integer.parseInt(latestData.getResult().get(0).getValue());
                        this.myTextField.setValue(String.valueOf(latestValue));
                        this.cabinWidget.setBattery(latestValue);
                        if (latestValue < 20) {
                            this.cabinWidget.setWarning(true);
                        }
                        else {
                            this.cabinWidget.setWarning(false);
                        }
                    });
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, @OptionalParameter String parameter) {

        Location location = beforeEvent.getLocation();
        QueryParameters queryParameters = location.getQueryParameters();

        Map<String, List<String>> parametersMap = queryParameters.getParameters();

        parametersMap.forEach((key, values) -> {
            System.out.print(key + " = " + values);
        });
    }

}
