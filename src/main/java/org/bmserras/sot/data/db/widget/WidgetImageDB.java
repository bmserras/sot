package org.bmserras.sot.data.db.widget;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import org.bmserras.sot.data.db.AbstractEntity;

import java.util.Arrays;

@Entity
@Table(name = "image")
public class WidgetImageDB extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @Lob
    @Column(name = "data")
    private byte[] data;

    private String iconName;

    public WidgetImageDB() {
        super();
    }

    public WidgetImageDB(String name, byte[] data, String iconName) {
        this.name = name;
        this.data = data;
        this.iconName = iconName;
    }

    public WidgetImageDB(long identifier, String name, byte[] data, String iconName) {
        super(identifier);
        this.name = name;
        this.data = data;
        this.iconName = iconName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return "ImageDB{" +
                "name='" + name + '\'' +
                ", data=" + Arrays.toString(data) +
                ", iconName='" + iconName + '\'' +
                "} " + super.toString();
    }
}
