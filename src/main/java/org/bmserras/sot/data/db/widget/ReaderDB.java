package org.bmserras.sot.data.db.widget;

import jakarta.persistence.*;
import org.bmserras.sot.data.db.AbstractEntity;

import java.util.Objects;

@Entity
@Table(name = "reader")
//@Inheritance(strategy = InheritanceType.JOINED)
public /*abstract*/ class ReaderDB extends AbstractEntity {

    private String name;
    private String unit;
    private int unitMin;
    private int unitMax;

    @ManyToOne(targetEntity = WidgetDB.class, fetch = FetchType.LAZY)
    private WidgetDB widget;

    public ReaderDB() {
        super();
    }

    public ReaderDB(long identifier, String name, String unit, int unitMin, int unitMax) {
        super(identifier);
        this.name = name;
        this.unit = unit;
        this.unitMin = unitMin;
        this.unitMax = unitMax;
    }

    public void setWidget(WidgetDB widget) {
        this.widget = widget;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getUnitMin() {
        return unitMin;
    }

    public void setUnitMin(int unitMin) {
        this.unitMin = unitMin;
    }

    public int getUnitMax() {
        return unitMax;
    }

    public void setUnitMax(int unitMax) {
        this.unitMax = unitMax;
    }


    @Override
    public String toString() {
        return "ValueReaderDB{" +
                "name='" + name + '\'' +
                //", widget=" + widget +
                ", unit='" + unit + '\'' +
                ", unitMin=" + unitMin +
                ", unitMax=" + unitMax +
                "} " + super.toString();
    }
}
