package org.bmserras.sot.old.data;

import jakarta.persistence.*;
import org.bmserras.sot.data.entity.AbstractEntity;

@Entity
@Table(name = "widget")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Widget extends AbstractEntity {

    private String name;

    public Widget() {
        super();
    }

    public Widget(int identifier, String name) {
        super(identifier);
        this.name = name;
    }

    public Widget(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Widget{} " + super.toString();
    }
}
