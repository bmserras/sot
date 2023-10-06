package org.bmserras.sot.data.domain.readers;

public enum ValueReaderType {
    GAUGE("Gauge"),
    SOLID_GAUGE("Solid Gauge"),
    TEXT_FIELD("Text field"),
    NUMBER_FIELD("Number field");

    private String type;

    ValueReaderType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
