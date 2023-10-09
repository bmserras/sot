package org.bmserras.sot.data.db.widget;

import jakarta.persistence.*;
import org.bmserras.sot.data.db.AbstractEntity;

import java.util.Objects;

@Entity
@Table(name = "reader")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ValueReaderDB extends AbstractEntity {

    private String name;

    @ManyToOne(targetEntity = WidgetDB.class, fetch = FetchType.LAZY)
    private WidgetDB widget;

    public ValueReaderDB() {
        super();
    }

    public ValueReaderDB(long identifier, String name, WidgetDB widgetDB) {
        super(identifier);
        this.name = name;
        this.widget = widgetDB;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWidget(WidgetDB widgetDB) {
        this.widget = widgetDB;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ValueReaderDB that = (ValueReaderDB) o;
        return Objects.equals(widget, that.widget);
    }

    @Override
    public int hashCode() {
        return Objects.hash(widget);
    }

    @Override
    public String toString() {
        return "ValueReaderDB{" +
                "name='" + name + '\'' +
                ", widget=" + widget +
                "} " + super.toString();
    }
}
