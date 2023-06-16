package org.bmserras.sot.components;

import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.PropertyDescriptor;
import com.vaadin.flow.component.PropertyDescriptors;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.shared.Registration;
import org.bmserras.sot.events.CircleClickEvent;
import org.bmserras.sot.events.RectClickEvent;

@JsModule("./src/cabin-widget.js")
@Tag("cabin-widget")
public class CabinWidget extends LitTemplate {

    private static final PropertyDescriptor<String, String> nameProperty = PropertyDescriptors.propertyWithDefault(
            "name",
            "Cabin name");

    private static final PropertyDescriptor<Integer, Integer> batteryProperty = PropertyDescriptors.propertyWithDefault(
            "battery",
            0);

    private static final PropertyDescriptor<Boolean, Boolean> hasWarningProperty = PropertyDescriptors.propertyWithDefault(
            "hasWarning",
            false);

    public CabinWidget() {
        addClassName("cabin-widget");
    }

    public CabinWidget(String name) {
        addClassName("cabin-widget");
        setName(name);
    }

    public String getName() {
        return nameProperty.get(this);
    }

    public void setName(String name) {
        nameProperty.set(this, name);
    }

    public int getBattery() {
        return batteryProperty.get(this);
    }

    public void setBattery(int battery) {
        batteryProperty.set(this, battery);
    }

    public boolean getWarning() {
        return hasWarningProperty.get(this);
    }

    public void setWarning(boolean hasWarning) {
        hasWarningProperty.set(this, hasWarning);
    }

    public Registration addCircleClickListener(ComponentEventListener<CircleClickEvent> listener) {
        return addListener(CircleClickEvent.class, listener);
    }

    public Registration addRectClickListener(ComponentEventListener<RectClickEvent> listener) {
        return addListener(RectClickEvent.class, listener);
    }

}
