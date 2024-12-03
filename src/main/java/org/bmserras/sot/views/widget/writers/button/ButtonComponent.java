package org.bmserras.sot.views.widget.writers.button;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import org.bmserras.sot.data.domain.writers.ButtonData;

public class ButtonComponent extends Div {

    private final Button button = new Button();

    private final ButtonData buttonData;

    public ButtonComponent() {
        this(new ButtonData(
                "Default button",
                "Small",
                "Primary",
                "Success"
        ));
    }

    public ButtonComponent(ButtonData buttonData) {
        this.buttonData = buttonData;
        setLabel(buttonData.getName());
        setSize(buttonData.getSize());
        setMainVariant(buttonData.getMainVariant());
        setSecondaryVariant(buttonData.getSecondaryVariant());
        add(button);
    }

    public Button getButton() {
        return button;
    }

    public void setLabel(String label) {
        buttonData.setName(label);
        button.setText(label);
    }

    public void setSize(String size) {
        buttonData.setSize(size);
        button.removeThemeVariants(ButtonVariant.LUMO_SMALL, ButtonVariant.LUMO_LARGE);
        switch (size) {
            case "Small" -> button.addThemeVariants(ButtonVariant.LUMO_SMALL);
            case "Large" -> button.addThemeVariants(ButtonVariant.LUMO_LARGE);
        }
    }

    public void setMainVariant(String value) {
        buttonData.setMainVariant(value);
        button.removeThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_TERTIARY);
        switch (value) {
            case "Primary" -> button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
            case "Tertiary" -> button.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        }
    }

    public void setSecondaryVariant(String value) {
        buttonData.setSecondaryVariant(value);
        button.removeThemeVariants(ButtonVariant.LUMO_SUCCESS, ButtonVariant.LUMO_ERROR);
        switch (value) {
            case "Success" -> button.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
            case "Error" -> button.addThemeVariants(ButtonVariant.LUMO_ERROR);
        }
    }

    public ButtonData getButtonData() {
        return buttonData;
    }
}
