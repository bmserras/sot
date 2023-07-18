package org.bmserras.sot.data.entity.widgettype;

import jakarta.persistence.*;
import org.bmserras.sot.data.entity.synoptic.SynopticWidget;
import org.bmserras.sot.data.entity.widget.Widget;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "widget_type")
public class WidgetType {

    @Id
    private long identifier;

    @Column(unique = true)
    private String name;

    private String image;

    @OneToMany(mappedBy = "widgetType", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<WidgetTypeProperty> properties = new ArrayList<>();

    public WidgetType() {
        this.identifier = new Date().getTime();
    }

    public WidgetType(String name, String image) {
        this();
        this.name = name;
        this.image = image;
    }

    public long getIdentifier() {
        return identifier;
    }

    public void setIdentifier(long identifier) {
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<WidgetTypeProperty> getProperties() {
        return properties;
    }

    public void setProperties(List<WidgetTypeProperty> properties) {
        this.properties = properties;
    }

    public void addProperty(Property property) {
        properties.add(new WidgetTypeProperty(this, property));
    }

    public void removeProperty(Property property, int pos) {
        properties.remove(new WidgetTypeProperty(this, property));
    }

    @Override
    public String toString() {
        return "WidgetType{" +
                "identifier=" + identifier +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", properties=" + properties +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WidgetType that = (WidgetType) o;
        return identifier == that.identifier;
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier);
    }
}

