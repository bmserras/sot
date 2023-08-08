package org.bmserras.sot.data.entity.synoptic;

import jakarta.persistence.*;
import org.bmserras.sot.data.entity.AbstractEntity;
import org.bmserras.sot.data.entity.widget.Widget;

import java.util.*;

@Entity
@Table(name = "synoptic")
public class Synoptic extends AbstractEntity {

    @OneToMany(mappedBy = "synoptic", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<SynopticWidget> widgets = new ArrayList<>();

    public Synoptic() {
        super();
    }

    public Synoptic(String name) {
        super(name);
    }

    public Synoptic(long identifier, String name) {
        super(identifier, name);
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
