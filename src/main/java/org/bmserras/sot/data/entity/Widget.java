package org.bmserras.sot.data.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "widget")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Widget {

    @Id
    private long identifier;

    private String name;

    public Widget() {
        this.identifier = new Date().getTime();
    }

    public Widget(int identifier, String name) {
        this.identifier = identifier;
        this.name = name;
    }

    public Widget(String name) {
        this();
        this.name = name;
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

    @Override
    public String toString() {
        return "Widget{" +
                "identifier='" + identifier + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Widget widget = (Widget) o;
        return identifier == widget.identifier;
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier);
    }
}
