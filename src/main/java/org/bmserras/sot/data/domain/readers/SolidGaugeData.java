package org.bmserras.sot.data.domain.readers;

import java.util.Date;

public class SolidGaugeData extends Reader {

    private int width;
    private int height;

    private int borderWidthPixels;
    private String borderStyle;

    public SolidGaugeData(String name, String unit, int unitMin, int unitMax, int width, int height, int borderWidthPixels, String borderStyle) {
        super(new Date().getTime(), name, unit, unitMin, unitMax);
        this.width = width;
        this.height = height;
        this.borderWidthPixels = borderWidthPixels;
        this.borderStyle = borderStyle;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getBorderWidthPixels() {
        return borderWidthPixels;
    }

    public void setBorderWidthPixels(int borderWidthPixels) {
        this.borderWidthPixels = borderWidthPixels;
    }

    public String getBorderStyle() {
        return borderStyle;
    }

    public void setBorderStyle(String borderStyle) {
        this.borderStyle = borderStyle;
    }
}
