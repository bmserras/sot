package org.bmserras.sot.data.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "widget")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Widget {

    @Id
    private String identifier;
    private String name;
    private String type;

    public Widget() {
    }

    public Widget(String identifier, String name, String type) {
        this.identifier = identifier;
        this.name = name;
        this.type = type;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Widget{" +
                "identifier='" + identifier + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
