package org.bmserras.sot.views.cabin;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.bmserras.sot.api.Event;
import org.bmserras.sot.api.Service;
import org.bmserras.sot.components.CabinWidget;
import org.bmserras.sot.components.RadarWidgetComponent;
import org.bmserras.sot.views.AbsoluteLayout;
import org.bmserras.sot.views.MainLayout;

import java.io.IOException;

@PageTitle("Cabin View")
@Route(value = "cabin", layout = MainLayout.class)
public class CabinView extends HorizontalLayout {

    public CabinView() {

        setSizeFull();

        /*CabinWidget cabinWidget = new CabinWidget();
        cabinWidget.setName("A1 km 12");
        add(cabinWidget);

        cabinWidget.addCircleClickListener(e -> Notification.show("CIRCLE!"));
        cabinWidget.addRectClickListener(e -> Notification.show("RECTANGLE!"));

        FeederThread feederThread = new FeederThread(UI.getCurrent(), cabinWidget);
        feederThread.start();*/

        /*CabinWidgetThread cabinWidgetThread = new CabinWidgetThread();
        cabinWidgetThread.start();
        add(cabinWidgetThread);*/

        RadarWidgetComponent radarWidget = new RadarWidgetComponent(1);

        /*NumberField numberField = new NumberField();
        numberField.setValue(1d);
        numberField.setStepButtonsVisible(true);
        numberField.setMin(0);
        numberField.setMax(2);
        numberField.setStep(0.1);

        numberField.addValueChangeListener(e -> {
            Double value = e.getValue();
            if (value == 1.0)
                radarWidget.setScale(0.99999);
            else radarWidget.setScale(value);
        });

        add(numberField, radarWidget);*/

        AbsoluteLayout absoluteLayout = new AbsoluteLayout();
        absoluteLayout.setWidth("50%");
        absoluteLayout.setHeightFull();
        add(absoluteLayout);

        absoluteLayout.add(radarWidget, radarWidget.getPosX(), radarWidget.getPosY());

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setWidth("50%");
        horizontalLayout.setHeightFull();
        add(horizontalLayout);

        Button leftButton = new Button("<", e -> {
            radarWidget.setPosX(radarWidget.getPosX() - 1);
            absoluteLayout.removeAll();
            absoluteLayout.add(radarWidget, radarWidget.getPosX(), radarWidget.getPosY());
        });
        Button upButton = new Button("^");
        Button downButton = new Button("v");
        Button rightButton = new Button(">", e -> {
            radarWidget.setPosX(radarWidget.getPosX() + 1);
            absoluteLayout.removeAll();
            absoluteLayout.add(radarWidget, radarWidget.getPosX(), radarWidget.getPosY());
        });
        horizontalLayout.add(leftButton, upButton, downButton, rightButton);


    }

    private static class FeederThread extends Thread {

        private final UI ui;
        private final CabinWidget cabinWidget;

        private final Service service = new Service();

        public FeederThread(UI ui, CabinWidget cabinWidget) {
            this.ui = ui;
            this.cabinWidget = cabinWidget;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Event latestData = service.getLatestData(10558);
                    this.ui.access(() -> {
                        int latestValue = Integer.parseInt(latestData.getResult().get(0).getValue());
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
}
