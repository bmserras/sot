package org.bmserras.sot.data.domain.readers;

public class Gauge extends IntValueReader {

    public Gauge() {
    }

    public Gauge(int min, int max) {
        super(min, max);
    }

    @Override
    public String toString() {
        return "Gauge{} " + super.toString();
    }
}
