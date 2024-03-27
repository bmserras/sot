package org.bmserras.sot.data.domain.readers;

public abstract class IntReader extends Reader {

    private int min;
    private int max;

    public IntReader() {
        this(0, 100);
    }

    public IntReader(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public IntReader(long identifier, String name, int min, int max) {
        super(identifier, name);
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
