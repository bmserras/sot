package org.bmserras.sot.data.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "widget")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Widget {

    @Id
    private long identifier;

    private String name;

    @ManyToMany(mappedBy = "widgets")
    //@OneToMany(mappedBy = "widget")
    private List<Synoptic> synoptic = new LinkedList<>();

    public Widget() {
        this.identifier = new Date().getTime();
    }

    public Widget(int identifier, String name) {
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

    public List<Synoptic> getSynoptic() {
        return synoptic;
    }

    public void setSynoptic(List<Synoptic> synoptic) {
        this.synoptic = synoptic;
    }

    @Override
    public String toString() {
        return "Widget{" +
                "identifier='" + identifier + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Widget widget = (Widget) o;
        return identifier == widget.identifier;
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier);
    }
}
