package org.bmserras.sot.views.widget.writers.button;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class ButtonContainer extends VerticalLayout {

    public ButtonContainer(ButtonComponent buttonComponent) {
        addClassName("button-container");
        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);
        add(buttonComponent);
        buttonComponent.addClassName("in-button-container");
    }

}
