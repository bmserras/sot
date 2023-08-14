package org.bmserras.sot.data.entity.widgettype;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import org.bmserras.sot.data.entity.AbstractEntity;

@Entity
@Table(name = "property")
@Inheritance(strategy = InheritanceType.JOINED)
public class Property extends AbstractEntity {

    private String name;

    private String type;

    public Property() {
        super();
    }

    @Override
    public String getPath() {
        return "property/";
    }

    public Property(String name, String type) {
        this.name = name;
        this.type = type;
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
                "type='" + type + '\'' +
                "} " + super.toString();
    }
}
