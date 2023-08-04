package org.bmserras.sot.data.entity.widgettype;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "gauge")
@Inheritance(strategy = InheritanceType.JOINED)
public class Gauge {

    @Id
    private long identifier;

    private String name;

    private double minValue;

    private double maxValue;

    private String color;

    private String type;

    public Gauge() {
        this.identifier = new Date().getTime();
    }

    public Gauge(String name, double minValue, double maxValue, String color, String type) {
        this();
        this.name = name;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.color = color;
        this.type = type;
    }

    public long getIdentifier() {
        return identifier;
    }

    public void setIdentifier(long identifier) {
        this.identifier = identifier;
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
                "identifier=" + identifier +
                ", name='" + name + '\'' +
                ", minValue=" + minValue +
                ", maxValue=" + maxValue +
                ", color='" + color + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gauge gauge = (Gauge) o;
        return identifier == gauge.identifier;
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier);
    }
}
