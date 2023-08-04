package org.bmserras.sot.data.entity.project;

import jakarta.persistence.*;
import org.bmserras.sot.data.entity.synoptic.Synoptic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "project")
public class Project {

    @Id
    private long identifier;

    private String name;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<ProjectSynoptic> synoptics = new ArrayList<>();

    public Project() {
        this.identifier = new Date().getTime();
        this.name = "Blank project";
    }

    public Project(String name) {
        this();
        this.name = name;
    }

    public Project(long identifier, String name) {
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

    public List<ProjectSynoptic> getSynoptics() {
        return synoptics;
    }

    public void setSynoptics(List<ProjectSynoptic> synoptics) {
        this.synoptics = synoptics;
    }

    public void addSynoptic(Synoptic synoptic, int pos) {
        synoptics.add(new ProjectSynoptic(this, synoptic, pos));
    }

    public void removeSynoptic(Synoptic synoptic, int pos) {
        synoptics.remove(new ProjectSynoptic(this, synoptic, pos));
    }

    @Override
    public String toString() {
        return "Project{" +
                "identifier=" + identifier +
                ", name='" + name + '\'' +
                ", synoptics=" + synoptics +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return identifier == project.identifier;
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier);
    }
}
