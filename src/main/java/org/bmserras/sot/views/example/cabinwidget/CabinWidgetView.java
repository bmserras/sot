package org.bmserras.sot.views.example.cabinwidget;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.bmserras.sot.api.Event;
import org.bmserras.sot.api.Service;
import org.bmserras.sot.views.layout.MainLayout;

import java.io.IOException;

@PageTitle("CabinWidget View")
@Route(value = "cabin-widget", layout = MainLayout.class)
public class CabinWidgetView extends VerticalLayout {

    private TextField textField;
    private CabinWidget cabinWidget;
    private FeederThread feeder;

    public CabinWidgetView() {

        setMargin(true);
        setPadding(true);

        textField = new TextField();

        cabinWidget = new CabinWidget();
        cabinWidget.setName("A1 km 12");
        cabinWidget.addCircleClickListener(e -> Notification.show("CIRCLE!"));
        cabinWidget.addRectClickListener(e -> Notification.show("RECTANGLE!"));

        add(cabinWidget, textField);

        this.feeder = new FeederThread(UI.getCurrent(), textField, cabinWidget);
        this.feeder.start();
    }

    // This thread could be at CabinWidget API? start and stop it there
    private static class FeederThread extends Thread {

        private final UI ui;
        private final TextField textField;
        private final CabinWidget cabinWidget;

        private final Service service = new Service();

        public FeederThread(UI ui, TextField textField, CabinWidget cabinWidget) {
            this.ui = ui;
            this.textField = textField;
            this.cabinWidget = cabinWidget;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Event latestData = service.getLatestData(10558);
                    /*this.ui*/this.cabinWidget.getUI().ifPresent(ui -> ui.access(() -> {
                        int latestValue = Integer.parseInt(latestData.getResult().get(0).getValue());
                        this.textField.setValue(String.valueOf(latestValue));
                        this.cabinWidget.setBattery(latestValue);
                        if (latestValue < 20) {
                            this.cabinWidget.setWarning(true);
                        }
                        else {
                            this.cabinWidget.setWarning(false);
                        }
                    }));
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
