package org.bmserras.sot.views.components;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class Toolbar extends HorizontalLayout {

    private HorizontalLayout buttons;

    public Toolbar() {

        buttons = new HorizontalLayout();

        add(buttons);
        this.setAlignItems(Alignment.END);
    }

    public Button addButton(Component icon, ComponentEventListener<ClickEvent<Button>> listener) {
        Button button = new Button();
        button.setIcon(icon);
        button.addClickListener(listener);
        buttons.add(button);
        return button;
    }
}
