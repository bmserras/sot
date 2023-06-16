package org.bmserras.sot.components;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.dependency.JsModule;

@JsModule("./src/radar-widget.js")
@Tag("radar-widget")
public class RadarWidgetComponent extends WidgetComponent {

    private static final PropertyDescriptor<Double, Double> scaleProperty = PropertyDescriptors.propertyWithDefault(
            "scale",
            1.0);

    private int posX;
    private int posY;

    public RadarWidgetComponent() {
        this.posX = 0;
        this.posY = 0;
    }

    public RadarWidgetComponent(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;

        addContextMenuItem("Cabin", e -> {
            getUI().ifPresent(ui -> {
                ui.navigate("cin");
            });
        });
        addContextMenuItem("Cinemometer", e -> {

        });
    }

    public RadarWidgetComponent(double scale) {
        this(0,0);
        setScale(scale);
    }

    public Double getScale() {
        return scaleProperty.get(this);
    }

    public void setScale(double scale) {
        scaleProperty.set(this, scale);
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
}
