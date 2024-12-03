package org.bmserras.sot.views.widget.writers.checkbox;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class CheckboxContainer extends VerticalLayout {

    public CheckboxContainer(CheckboxComponent checkboxComponent) {
        addClassName("button-container");
        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);
        add(checkboxComponent);
        checkboxComponent.addClassName("in-button-container");
    }

}
