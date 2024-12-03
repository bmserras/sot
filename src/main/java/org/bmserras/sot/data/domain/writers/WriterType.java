package org.bmserras.sot.data.domain.writers;

public enum WriterType {
    BUTTON("Button"),
    TOGGLE("Toggle"),
    TEXT_FIELD("Text field"),
    CHECKBOX("Checkbox");

    private String type;

    WriterType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
