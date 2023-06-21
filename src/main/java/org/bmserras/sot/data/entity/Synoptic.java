package org.bmserras.sot.data.entity;

import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "synoptic")
public class Synoptic {

    @Id
    private long identifier;

    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "synoptic", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<SynopticWidget> widgets = new ArrayList<>();

    public Synoptic() {
        this.identifier = new Date().getTime();
    }

    public Synoptic(String name) {
        this();
        this.name = name;
    }

    public Synoptic(long identifier, String name) {
        this.identifier = identifier;
        this.name = name;
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
                "identifier=" + identifier +
                ", name='" + name + '\'' +
                ", widgets=" + widgets +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Synoptic synoptic = (Synoptic) o;
        return identifier == synoptic.identifier;
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier);
    }
}
