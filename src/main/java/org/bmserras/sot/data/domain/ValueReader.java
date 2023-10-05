package org.bmserras.sot.data.domain;

import java.util.Date;

public class ValueReader {

    private long id;
    private String name;
    private int min;
    private int max;
    private ValueReaderType type;

    public ValueReader() {
        this(new Date().getTime(), "Default");
    }

    public ValueReader(long id, String name) {
        this(id, name, 0, 100, ValueReaderType.GAUGE);
    }

    public ValueReader(long id, String name, int min, int max, ValueReaderType type) {
        this.id = id;
        this.name = name;
        this.min = min;
        this.max = max;
        this.type = type;
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

    public ValueReaderType getType() {
        return type;
    }

    public void setType(ValueReaderType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ValueReader{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", min=" + min +
                ", max=" + max +
                ", type=" + type +
                '}';
    }
}
