package org.bmserras.sot.views.widget;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class GeneralForm extends VerticalLayout {

    private TextField name = new TextField("Name");

    public GeneralForm() {

        add(name);
    }
}
