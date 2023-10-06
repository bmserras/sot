package org.bmserras.sot.data.db.widget;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.bmserras.sot.data.db.AbstractEntity;

@Entity
@Table(name = "widget")
public class WidgetDB extends AbstractEntity {

    private String name;

    public WidgetDB() {
        super();
    }

    public WidgetDB(String name) {
        this.name = name;
    }

    public WidgetDB(long identifier, String name) {
        super(identifier);
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
        return "WidgetDB{" +
                "name='" + name + '\'' +
                "} " + super.toString();
    }
}
