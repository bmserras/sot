package org.bmserras.sot.data.domain.writers;

import java.util.Date;

public class ButtonData extends Writer {

    private String size;
    private String mainVariant;
    private String secondaryVariant;

    public ButtonData() {}

    public ButtonData(String name, String size, String mainVariant, String secondaryVariant) {
        super(new Date().getTime(), name);
        this.size = size;
        this.mainVariant = mainVariant;
        this.secondaryVariant = secondaryVariant;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getMainVariant() {
        return mainVariant;
    }

    public void setMainVariant(String mainVariant) {
        this.mainVariant = mainVariant;
    }

    public String getSecondaryVariant() {
        return secondaryVariant;
    }

    public void setSecondaryVariant(String secondaryVariant) {
        this.secondaryVariant = secondaryVariant;
    }
}
