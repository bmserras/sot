package org.bmserras.sot.data.db.widget;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "reader_solid_gauge")
public class SolidGaugeDB extends IntReaderDB {

    private String color;

    public SolidGaugeDB() {
        super();
    }

    public SolidGaugeDB(long identifier, String name, WidgetDB widgetDB, int min, int max, String color) {
        super(identifier, name, widgetDB, min, max);
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SolidGaugeDB that = (SolidGaugeDB) o;
        return Objects.equals(color, that.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), color);
    }

    @Override
    public String toString() {
        return "SolidGaugeDB{" +
                "color='" + color + '\'' +
                "} " + super.toString();
    }
}
