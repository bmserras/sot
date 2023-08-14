package org.bmserras.sot.data.db.project;

import jakarta.persistence.*;
import org.bmserras.sot.data.db.AbstractEntity;
import org.bmserras.sot.data.db.synoptic.Synoptic;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "project")
public class Project extends AbstractEntity {

    private String name;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<ProjectSynoptic> synoptics = new ArrayList<>();

    public Project() {
        super();
    }

    public Project(String name) {
        this.name = name;
    }

    public Project(long identifier, String name) {
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
        return "project/";
    }

    public List<ProjectSynoptic> getSynoptics() {
        return synoptics;
    }

    public void setSynoptics(List<ProjectSynoptic> synoptics) {
        this.synoptics = synoptics;
    }

    public void addSynoptic(Synoptic synoptic) {
        synoptics.add(new ProjectSynoptic(this, synoptic));
    }

    public void removeSynoptic(Synoptic synoptic) {
        synoptics.remove(new ProjectSynoptic(this, synoptic));
    }

    @Override
    public String toString() {
        return "Project{" +
                "synoptics=" + synoptics +
                "} " + super.toString();
    }
}
