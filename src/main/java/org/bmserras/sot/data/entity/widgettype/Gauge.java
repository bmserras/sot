package org.bmserras.sot.data.entity.widgettype;

import jakarta.persistence.*;
import org.bmserras.sot.data.entity.AbstractEntity;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "gauge")
@Inheritance(strategy = InheritanceType.JOINED)
public class Gauge extends AbstractEntity {

    private String name;

    private double minValue;

    private double maxValue;

    private String color;

    private String type;

    public Gauge() {
        super();
    }

    @Override
    public String getPath() {
        return "gauge/";
    }

    public Gauge(String name, double minValue, double maxValue, String color, String type) {
        this.name = name;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.color = color;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMinValue() {
        return minValue;
    }

    public void setMinValue(double minValue) {
        this.minValue = minValue;
    }

    public double getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(double maxValue) {
        this.maxValue = maxValue;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Gauge{" +
                "minValue=" + minValue +
                ", maxValue=" + maxValue +
                ", color='" + color + '\'' +
                ", type='" + type + '\'' +
                "} " + super.toString();
    }
}
