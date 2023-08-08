package org.bmserras.sot.data.entity.project;

import jakarta.persistence.*;
import org.bmserras.sot.data.entity.AbstractEntity;
import org.bmserras.sot.data.entity.synoptic.Synoptic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "project")
public class Project extends AbstractEntity {

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<ProjectSynoptic> synoptics = new ArrayList<>();

    public Project() {
        super();
    }

    public Project(String name) {
        super(name);
    }

    public Project(long identifier, String name) {
        super(identifier, name);
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
                "synoptics=" + synoptics +
                "} " + super.toString();
    }
}
