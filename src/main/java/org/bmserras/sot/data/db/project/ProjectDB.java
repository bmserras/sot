package org.bmserras.sot.data.db.project;

import jakarta.persistence.*;
import org.bmserras.sot.data.db.AbstractEntity;
import org.bmserras.sot.data.db.synoptic.SynopticDB;
import org.bmserras.sot.data.db.user.UserDB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "project")
public class ProjectDB extends AbstractEntity {

    private String name;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<ProjectSynopticDB> synoptics = new ArrayList<>();

    public ProjectDB() {
        super();
    }

    public ProjectDB(String name) {
        this.name = name;
    }

    public ProjectDB(long identifier, String name) {
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

    public List<ProjectSynopticDB> getSynoptics() {
        return synoptics;
    }

    public void setSynoptics(List<ProjectSynopticDB> synoptics) {
        this.synoptics = synoptics;
    }

    public void addSynoptic(SynopticDB synopticDB) {
        synoptics.add(new ProjectSynopticDB(this, synopticDB));
    }

    public void addSynoptics(SynopticDB... synopticsDB) {
        for (SynopticDB synopticDB : synopticsDB) addSynoptic(synopticDB);
    }

    public void removeSynoptic(SynopticDB synopticDB) {
        synoptics.remove(new ProjectSynopticDB(this, synopticDB));
    }

    @Override
    public String toString() {
        return "Project{" +
                "synoptics=" + synoptics +
                "} " + super.toString();
    }
}
