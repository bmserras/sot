package org.bmserras.sot.data.domain.readers;

public enum ReaderType {
    GAUGE("Gauge"),
    SOLID_GAUGE("Solid Gauge"),
    RO_TEXT_FIELD("Read-only Text field");

    private String type;

    ReaderType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
