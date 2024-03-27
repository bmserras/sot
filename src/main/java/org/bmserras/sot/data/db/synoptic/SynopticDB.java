package org.bmserras.sot.data.db.synoptic;

import jakarta.persistence.*;
import org.bmserras.sot.data.db.AbstractEntity;
import org.bmserras.sot.data.db.WidgetInstanceDB;
import org.bmserras.sot.data.db.widget.ValueReaderDB;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "synoptic")
public class SynopticDB extends AbstractEntity {

    private String name;

    @OneToMany(targetEntity = WidgetInstanceDB.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy =
            "synoptic")
    private List<WidgetInstanceDB> widgetInstances;

    public SynopticDB() {
        super();
    }

    public SynopticDB(String name) {
        this.name = name;
        this.widgetInstances = new ArrayList<>();
    }

    public SynopticDB(long identifier, String name) {
        super(identifier);
        this.name = name;
        this.widgetInstances = new ArrayList<>();
    }

    public SynopticDB(long identifier, String name, List<WidgetInstanceDB> widgetInstances) {
        super(identifier);
        this.name = name;
        this.widgetInstances = widgetInstances;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<WidgetInstanceDB> getWidgetInstances() {
        return widgetInstances;
    }

    public void setWidgetInstances(List<WidgetInstanceDB> widgetInstances) {
        this.widgetInstances = widgetInstances;
    }

    public void addWidgetInstance(WidgetInstanceDB widgetInstanceDB){
        widgetInstances.add(widgetInstanceDB);
        widgetInstanceDB.setSynoptic(this);
    }

    @Override
    public String toString() {
        return "SynopticDB{" +
                "name='" + name + '\'' +
                ", widgetInstances=" + widgetInstances +
                '}';
    }
}
