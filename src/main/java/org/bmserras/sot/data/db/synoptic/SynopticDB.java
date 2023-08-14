package org.bmserras.sot.data.db.synoptic;

import jakarta.persistence.*;
import org.bmserras.sot.data.db.AbstractEntity;
import org.bmserras.sot.old.data.Widget;

import java.util.*;

@Entity
@Table(name = "synoptic")
public class SynopticDB extends AbstractEntity {

    private String name;

    @OneToMany(mappedBy = "synoptic", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<SynopticWidget> widgets = new ArrayList<>();

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

    public String getPath() {
        return "synoptic/";
    }

    public List<SynopticWidget> getWidgets() {
        return widgets;
    }

    public void setWidgets(List<SynopticWidget> widgets) {
        this.widgets = widgets;
    }

    public void addWidget(Widget widget, int pos) {
        widgets.add(new SynopticWidget(this, widget, pos));
    }

    public void removeWidget(Widget widget, int pos) {
        widgets.remove(new SynopticWidget(this, widget, pos));
    }

    @Override
    public String toString() {
        return "SynopticDB{" +
                "name='" + name + '\'' +
                ", widgets=" + widgets +
                "} " + super.toString();
    }
}
