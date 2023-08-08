package org.bmserras.sot.data.entity.widget;

import jakarta.persistence.*;
import org.bmserras.sot.data.entity.AbstractEntity;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "widget")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Widget extends AbstractEntity {

    public Widget() {
        super();
    }

    public Widget(int identifier, String name) {
        super(identifier, name);
    }

    public Widget(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "Widget{} " + super.toString();
    }
}
