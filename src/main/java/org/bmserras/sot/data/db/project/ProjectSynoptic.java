package org.bmserras.sot.data.db.project;

import jakarta.persistence.*;
import org.bmserras.sot.data.db.synoptic.Synoptic;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "project_synoptic")
@IdClass(ProjectSynoptic.ProjectSynopticId.class)
public class ProjectSynoptic implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "project_identifier", referencedColumnName = "identifier")
    private ProjectDB project;

    @Id
    @ManyToOne
    @JoinColumn(name = "synoptic_identifier", referencedColumnName = "identifier")
    private Synoptic synoptic;

    public ProjectSynoptic() {
    }

    public ProjectSynoptic(ProjectDB project, Synoptic synoptic) {
        this.project = project;
        this.synoptic = synoptic;
    }

    public ProjectDB getProject() {
        return project;
    }

    public void setProject(ProjectDB projectDB) {
        this.project = projectDB;
    }

    public Synoptic getSynoptic() {
        return synoptic;
    }

    public void setSynoptic(Synoptic synoptic) {
        this.synoptic = synoptic;
    }

    @Override
    public String toString() {
        return "ProjectSynoptic{" +
                ", synoptic=" + synoptic +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectSynoptic that = (ProjectSynoptic) o;
        return project.equals(that.project) && synoptic.equals(that.synoptic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(project, synoptic);
    }

    public static class ProjectSynopticId implements Serializable {

        private long project;
        private long synoptic;

        public long getProject() {
            return project;
        }

        public void setProject(long project) {
            this.project = project;
        }

        public long getSynoptic() {
            return synoptic;
        }

        public void setSynoptic(long synoptic) {
            this.synoptic = synoptic;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ProjectSynopticId that = (ProjectSynopticId) o;
            return project == that.project && synoptic == that.synoptic;
        }

        @Override
        public int hashCode() {
            return Objects.hash(project, synoptic);
        }
    }
}
