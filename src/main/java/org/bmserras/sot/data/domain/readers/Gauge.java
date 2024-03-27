package org.bmserras.sot.data.domain.readers;

public class Gauge extends IntReader {

    public Gauge() {
    }

    public Gauge(long identifier, String name, int min, int max) {
        super(identifier, name, min, max);
    }

    public Gauge(int min, int max) {
        super(min, max);
    }

    @Override
    public String toString() {
        return "Gauge{} " + super.toString();
    }
}
