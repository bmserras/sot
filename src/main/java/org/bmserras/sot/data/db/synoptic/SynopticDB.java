package org.bmserras.sot.data.db.synoptic;

import jakarta.persistence.*;
import org.bmserras.sot.data.db.AbstractEntity;
import org.bmserras.sot.data.db.WidgetInstanceDB;
import org.bmserras.sot.data.domain.Grid;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "synoptic")
public class SynopticDB extends AbstractEntity {

    private String name;

    @Enumerated(EnumType.ORDINAL)
    private Grid grid;

    @OneToMany(targetEntity = WidgetInstanceDB.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy =
            "synoptic")
    private List<WidgetInstanceDB> widgetInstances;

    public SynopticDB() {
        super();
    }

    public SynopticDB(String name) {
        this.name = name;
        this.widgetInstances = new ArrayList<>();
        this.grid = Grid.r3c2c3c2;
    }

    public SynopticDB(long identifier, String name) {
        super(identifier);
        this.name = name;
        this.widgetInstances = new ArrayList<>();
        this.grid = Grid.r3c2c3c2;
    }

    public SynopticDB(long identifier, String name, Grid grid) {
        super(identifier);
        this.name = name;
        this.widgetInstances = new ArrayList<>();
        this.grid = grid;
    }

    public SynopticDB(long identifier, String name, List<WidgetInstanceDB> widgetInstances, Grid grid) {
        super(identifier);
        this.name = name;
        this.widgetInstances = widgetInstances;
        this.grid = grid;
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

    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    @Override
    public String toString() {
        return "SynopticDB{" +
                "name='" + name + '\'' +
                ", grid=" + grid +
                ", widgetInstances=" + widgetInstances +
                "} " + super.toString();
    }
}
