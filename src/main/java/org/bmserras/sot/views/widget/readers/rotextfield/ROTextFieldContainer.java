package org.bmserras.sot.views.widget.readers.rotextfield;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class ROTextFieldContainer extends VerticalLayout {

    public ROTextFieldContainer(ROTextFieldComponent roTextFieldComponent) {
        addClassName("button-container");
        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);
        add(roTextFieldComponent);
        roTextFieldComponent.addClassName("in-gauge-container");
    }
}
