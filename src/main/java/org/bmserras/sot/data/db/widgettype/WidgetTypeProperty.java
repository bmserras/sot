package org.bmserras.sot.data.db.widgettype;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "widget_type_property")
@IdClass(WidgetTypeProperty.WidgetTypePropertyId.class)
public class WidgetTypeProperty implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "widget_type_identifier", referencedColumnName = "identifier")
    private WidgetType widgetType;

    @Id
    @ManyToOne
    @JoinColumn(name = "property_identifier", referencedColumnName = "identifier")
    private Property property;

    public WidgetTypeProperty() {
    }

    public WidgetTypeProperty(WidgetType widgetType, Property property) {
        this.widgetType = widgetType;
        this.property = property;
    }

    public WidgetType getWidgetType() {
        return widgetType;
    }

    public void setWidgetType(WidgetType widgetType) {
        this.widgetType = widgetType;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    @Override
    public String toString() {
        return "WidgetTypeProperty{" +
                ", property=" + property +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WidgetTypeProperty that = (WidgetTypeProperty) o;
        return widgetType.equals(that.widgetType) && property.equals(that.property);
    }

    @Override
    public int hashCode() {
        return Objects.hash(widgetType, property);
    }

    public static class WidgetTypePropertyId implements Serializable {

        private long widgetType;
        private long property;

        public long getWidgetType() {
            return widgetType;
        }

        public void setWidgetType(long widgetType) {
            this.widgetType = widgetType;
        }

        public long getProperty() {
            return property;
        }

        public void setProperty(long property) {
            this.property = property;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            WidgetTypePropertyId that = (WidgetTypePropertyId) o;
            return widgetType == that.widgetType && property == that.property;
        }

        @Override
        public int hashCode() {
            return Objects.hash(widgetType, property);
        }
    }
}
