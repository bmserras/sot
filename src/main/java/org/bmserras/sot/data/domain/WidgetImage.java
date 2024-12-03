package org.bmserras.sot.data.domain;

import java.util.Arrays;
import java.util.Date;

public class WidgetImage {

    private long id;
    private String type;
    private byte[] data;
    private String iconName;

    public WidgetImage() {
        this(new Date().getTime(), "Default image", null, null);
    }

    public WidgetImage(byte[] data, String type) {
        this();
        this.type = "image/" + type;
        this.data = data;
    }

    public WidgetImage(String iconName, String type) {
        this();
        this.type = "icon/" + type;
        this.iconName = iconName;
    }

    public WidgetImage(long id, String type, byte[] data, String iconName) {
        this.id = id;
        this.type = type;
        this.data = data;
        this.iconName = iconName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getIconName() {
        return iconName;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
    }

    @Override
    public String toString() {
        return "WidgetImage{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", data=" + Arrays.toString(data) +
                ", iconName='" + iconName + '\'' +
                '}';
    }
}
