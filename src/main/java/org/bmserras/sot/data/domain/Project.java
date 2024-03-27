package org.bmserras.sot.data.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Project {

    private long id;

    private String name;

    private List<Synoptic> synoptics;

    public Project(long id, String name, List<Synoptic> synoptics) {
        this.id = id;
        this.name = name;
        this.synoptics = synoptics;
    }

    public Project(long id, String name) {
        this(id, name, new ArrayList<>());
    }

    public Project() {
        this(new Date().getTime(), "Untitled Project");
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

    public void addSynoptics(Synoptic... synoptics) {
        this.synoptics.addAll(Arrays.asList(synoptics));
    }

    public void removeSynoptic(Synoptic synoptic) {
        this.synoptics.remove(synoptic);
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", synoptics=" + synoptics +
                '}';
    }
}
