package org.bmserras.sot.data.entity.synoptic;

import jakarta.persistence.*;
import org.bmserras.sot.data.entity.AbstractEntity;
import org.bmserras.sot.old.data.Widget;

import java.util.*;

@Entity
@Table(name = "synoptic")
public class Synoptic extends AbstractEntity {

    private String name;

    @OneToMany(mappedBy = "synoptic", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<SynopticWidget> widgets = new ArrayList<>();

    public Synoptic() {
        super();
    }

    public Synoptic(String name) {
        this.name = name;
    }

    public Synoptic(long identifier, String name) {
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
        return "Synoptic{" +
                "widgets=" + widgets +
                "} " + super.toString();
    }
}
