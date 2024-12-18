package org.bmserras.sot.data.db.widget;

import jakarta.persistence.*;

import java.util.Objects;

@MappedSuperclass
public abstract class IntReaderDB extends ReaderDB {

    private int min;
    private int max;

    public IntReaderDB() {
        super();
    }

    public IntReaderDB(long identifier, String name, WidgetDB widgetDB, int min, int max) {
        super(identifier, name/*, widgetDB*/, "s", 0, 0);
        this.min = min;
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        IntReaderDB that = (IntReaderDB) o;
        return min == that.min && max == that.max;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), min, max);
    }

    @Override
    public String toString() {
        return "IntValueReaderDB{" +
                "min=" + min +
                ", max=" + max +
                "} " + super.toString();
    }
}
