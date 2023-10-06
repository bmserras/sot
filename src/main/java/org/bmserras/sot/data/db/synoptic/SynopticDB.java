package org.bmserras.sot.data.db.synoptic;

import jakarta.persistence.*;
import org.bmserras.sot.data.db.AbstractEntity;

@Entity
@Table(name = "synoptic")
public class SynopticDB extends AbstractEntity {

    private String name;

    public SynopticDB() {
        super();
    }

    public SynopticDB(String name) {
        this.name = name;
    }

    public SynopticDB(long identifier, String name) {
        super(identifier);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SynopticDB{" +
                "name='" + name + '\'' +
                "} " + super.toString();
    }
}
