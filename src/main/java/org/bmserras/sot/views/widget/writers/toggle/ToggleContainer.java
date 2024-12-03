package org.bmserras.sot.views.widget.writers.toggle;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class ToggleContainer extends VerticalLayout {

    public ToggleContainer(ToggleComponent toggleComponent) {
        addClassName("button-container");
        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);
        add(toggleComponent);
        toggleComponent.addClassName("in-button-container");
    }

}
