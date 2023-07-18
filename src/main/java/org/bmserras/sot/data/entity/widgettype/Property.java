package org.bmserras.sot.data.entity.widgettype;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "property")
@Inheritance(strategy = InheritanceType.JOINED)
public class Property {

    @Id
    private long identifier;

    private String name;

    private String type;

    public Property() {
        this.identifier = new Date().getTime();
    }

    public Property(String name, String type) {
        this();
        this.name = name;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Property{" +
                "identifier=" + identifier +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Property property = (Property) o;
        return identifier == property.identifier;
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier);
    }
}
