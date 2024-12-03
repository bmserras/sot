package org.bmserras.sot.data.domain.writers;

import java.util.Date;

public class TextFieldData extends Writer {

    private String placeholder;
    private String size;
    private String alignment;
    private String suffix;

    public TextFieldData() {}

    public TextFieldData(String label, String placeholder, String size, String alignment, String suffix) {
        super(new Date().getTime(), label);
        this.placeholder = placeholder;
        this.size = size;
        this.alignment = alignment;
        this.suffix = suffix;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getAlignment() {
        return alignment;
    }

    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
