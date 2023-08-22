package org.bmserras.sot.data.db.widgettype;

import jakarta.persistence.*;
import org.bmserras.sot.data.db.AbstractEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "widget_type")
public class WidgetType extends AbstractEntity {

    private String name;

    private String image;

    @OneToMany(mappedBy = "widgetType", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<WidgetTypeProperty> properties = new ArrayList<>();

    public WidgetType() {
        super();
    }

    public String getPath() {
        return "widget-type/";
    }

    public WidgetType(String name, String image) {
        this.name = name;
        this.image = image;
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
                "image='" + image + '\'' +
                ", properties=" + properties +
                "} " + super.toString();
    }
}
