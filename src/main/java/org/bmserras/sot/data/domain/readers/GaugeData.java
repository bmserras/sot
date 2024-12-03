package org.bmserras.sot.data.domain.readers;

import java.util.Date;

public class GaugeData extends Reader {

    private int width;
    private int height;

    private int tickLength;
    private boolean tickPosition;
    private String tickColor;

    private int minorTickLength;
    private boolean minorTickPosition;

    private int borderWidthPixels;
    private String borderColor;
    private String borderStyle;

    public GaugeData() {}

    public GaugeData(String name, String unit, int unitMin, int unitMax, int width, int height, int tickLength,
                     boolean tickPosition, String tickColor, int minorTickLength, boolean minorTickPosition, int borderWidthPixels, String borderStyle) {
        super(new Date().getTime(), name, unit, unitMin, unitMax);
        this.width = width;
        this.height = height;
        this.tickLength = tickLength;
        this.tickPosition = tickPosition;
        this.tickColor = tickColor;
        this.minorTickLength = minorTickLength;
        this.minorTickPosition = minorTickPosition;
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

    public int getTickLength() {
        return tickLength;
    }

    public void setTickLength(int tickLength) {
        this.tickLength = tickLength;
    }

    public boolean isTickPosition() {
        return tickPosition;
    }

    public void setTickPosition(boolean tickPosition) {
        this.tickPosition = tickPosition;
    }

    public String getTickColor() {
        return tickColor;
    }

    public void setTickColor(String tickColor) {
        this.tickColor = tickColor;
    }

    public int getMinorTickLength() {
        return minorTickLength;
    }

    public void setMinorTickLength(int minorTickLength) {
        this.minorTickLength = minorTickLength;
    }

    public boolean isMinorTickPosition() {
        return minorTickPosition;
    }

    public void setMinorTickPosition(boolean minorTickPosition) {
        this.minorTickPosition = minorTickPosition;
    }

    public int getBorderWidthPixels() {
        return borderWidthPixels;
    }

    public void setBorderWidthPixels(int borderWidthPixels) {
        this.borderWidthPixels = borderWidthPixels;
    }

    @Override
    public String toString() {
        return super.toString() + " Gauge{" +
                "width=" + width +
                ", height=" + height +
                ", tickLength=" + tickLength +
                ", tickPosition=" + tickPosition +
                ", tickColor='" + tickColor + '\'' +
                ", minorTickLength=" + minorTickLength +
                ", minorTickPosition=" + minorTickPosition +
                ", borderWidthPixels=" + borderWidthPixels +
                ", borderColor='" + borderColor + '\'' +
                "}";
    }

    public String getBorderStyle() {
        return borderStyle;
    }

    public String getBorderWidth() {
        return "" + borderWidthPixels;
    }

    public void setBorderStyle(String borderStyle) {
        this.borderStyle = borderStyle;
    }
}
