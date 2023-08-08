package org.bmserras.sot.data.entity.widgettype;

import jakarta.persistence.*;
import org.bmserras.sot.data.entity.AbstractEntity;
import org.bmserras.sot.data.entity.synoptic.SynopticWidget;
import org.bmserras.sot.data.entity.widget.Widget;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "widget_type")
public class WidgetType extends AbstractEntity {

    private String image;

    @OneToMany(mappedBy = "widgetType", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<WidgetTypeProperty> properties = new ArrayList<>();

    public WidgetType() {
        super();
    }

    @Override
    public String getPath() {
        return "widget-type/";
    }

    public WidgetType(String name, String image) {
        super(name);
        this.image = image;
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

