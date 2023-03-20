package org.bmserras.sot.components;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextField;
import org.bmserras.sot.api.Event;
import org.bmserras.sot.api.Service;

import java.io.IOException;

public class MyTextField extends TextField {

    private FeederThread feeder;

    public MyTextField() {
        this.setValue("Init");

        this.feeder = new FeederThread(UI.getCurrent(), this);
        this.feeder.start();
    }

    private static class FeederThread extends Thread {

        private final UI ui;
        private final MyTextField myTextField;

        private final Service service = new Service();

        public FeederThread(UI ui, MyTextField myTextField) {
            this.ui = ui;
            this.myTextField = myTextField;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Event latestData = service.getLatestData();
                    this.ui.access(() -> {
                        this.myTextField.setValue(latestData.getResult().get(0).getValue());
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
