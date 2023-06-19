package org.bmserras.sot.data.entity;

import jakarta.persistence.*;

import java.util.*;

@Entity
public class Synoptic {

    @Id
    private long identifier;

    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="synoptic_widget")
    //@OneToMany(mappedBy = "synoptic")
    private List<Widget> widgets = new ArrayList<>();

    public Synoptic() {
        this.identifier = new Date().getTime();
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

    public List<Widget> getWidgets() {
        return widgets;
    }

    public void setWidgets(List<Widget> widgets) {
        this.widgets = widgets;
    }

    @Override
    public String toString() {
        return "Synoptic{" +
                "identifier=" + identifier +
                ", name='" + name + '\'' +
                ", widgets=" + widgets +
                '}';
    }
}
