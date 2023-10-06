package org.bmserras.sot.data.domain.readers;

public abstract class IntValueReader extends ValueReader {

    private int min;
    private int max;

    public IntValueReader() {
        this(0, 100);
    }

    public IntValueReader(int min, int max) {
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
    public String toString() {
        return "IntValueReader{" +
                "min=" + min +
                ", max=" + max +
                "} " + super.toString();
    }
}