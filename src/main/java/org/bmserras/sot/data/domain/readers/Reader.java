package org.bmserras.sot.data.domain.readers;

import java.util.Date;

public /*abstract*/ class Reader {

    private long id;
    private String name;
    private String unit;
    private int unitMin;
    private int unitMax;

    public Reader() {
        this(new Date().getTime(), "Default name", "Default unit", 0, 10);
    }

    public Reader(long id, String name, String unit, int unitMin, int unitMax) {
        this.id = id;
        this.name = name;
        this.unit = unit;
        this.unitMin = unitMin;
        this.unitMax = unitMax;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
        return "Reader{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", unit='" + unit + '\'' +
                ", unitMin=" + unitMin +
                ", unitMax=" + unitMax +
                '}';
    }
}
