package org.bmserras.sot.views.widget;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.textfield.NumberField;

public class TextFieldWidget implements IReader {
    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setName(String name) {

    }

    @Override
    public int getMin() {
        return 0;
    }

    @Override
    public void setMin(int min) {

    }

    @Override
    public int getMax() {
        return 0;
    }

    @Override
    public void setMax(int max) {

    }

    @Override
    public void setValue(int value) {

    }

    @Override
    public Component getComponent() {
        return new NumberField();
    }
}
