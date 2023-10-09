package org.bmserras.sot.data.domain.readers;

public class SolidGauge extends IntValueReader {

    private String color;

    public SolidGauge() {
        this("black");
    }

    public SolidGauge(long identifier, String name, int min, int max, String color) {
        super(identifier, name, min, max);
        this.color = color;
    }

    public SolidGauge(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "SolidGauge{" +
                "color='" + color + '\'' +
                "} " + super.toString();
    }
}
