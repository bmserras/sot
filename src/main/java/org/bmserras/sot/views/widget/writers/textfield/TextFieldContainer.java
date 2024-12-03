package org.bmserras.sot.views.widget.writers.textfield;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.bmserras.sot.views.widget.writers.toggle.ToggleComponent;

public class TextFieldContainer extends VerticalLayout {

    public TextFieldContainer(TextFieldComponent textFieldComponent) {
        addClassName("textfield-container");
        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);
        add(textFieldComponent);
        textFieldComponent.addClassName("in-button-container");
        setSizeFull();
    }

}
