package org.bmserras.sot.data.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Project {

    private long id;
    private String name;
    private List<Synoptic> synoptics;

    public Project() {
        this.id = new Date().getTime();
    }

    public Project(long id, String name) {
        this(id, name, new ArrayList<>());
    }

    public Project(long id, String name, List<Synoptic> synoptics) {
        this.id = id;
        this.name = name;
        this.synoptics = synoptics;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Synoptic> getSynoptics() {
        return synoptics;
    }

    public void setSynoptics(List<Synoptic> synoptics) {
        this.synoptics = synoptics;
    }

    public void addSynoptic(Synoptic synoptic) {
        this.synoptics.add(synoptic);
    }

    public void removeSynoptic(Synoptic synoptic) {
        this.synoptics.remove(synoptic);
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
