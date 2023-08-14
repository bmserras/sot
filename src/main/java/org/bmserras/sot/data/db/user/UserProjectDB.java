package org.bmserras.sot.data.db.user;

import jakarta.persistence.*;
import org.bmserras.sot.data.db.project.ProjectDB;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "user_project")
@IdClass(UserProjectDB.UserProjectId.class)
public class UserProjectDB {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_identifier", referencedColumnName = "identifier")
    private UserDB user;

    @Id
    @ManyToOne
    @JoinColumn(name = "project_identifier", referencedColumnName = "identifier")
    private ProjectDB project;

    public UserProjectDB() {
    }

    public UserProjectDB(UserDB user, ProjectDB project) {
        this.user = user;
        this.project = project;
    }

    public UserDB getUser() {
        return user;
    }

    public void setUser(UserDB userDB) {
        this.user = userDB;
    }

    public ProjectDB getProject() {
        return project;
    }

    public void setProject(ProjectDB projectDB) {
        this.project = projectDB;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProjectDB that = (UserProjectDB) o;
        return Objects.equals(user, that.user) && Objects.equals(project, that.project);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, project);
    }

    public static class UserProjectId implements Serializable {

        private long user;
        private long project;

        public long getUser() {
            return user;
        }

        public void setUser(long user) {
            this.user = user;
        }

        public long getProject() {
            return project;
        }

        public void setProject(long project) {
            this.project = project;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            UserProjectId that = (UserProjectId) o;
            return user == that.user && project == that.project;
        }

        @Override
        public int hashCode() {
            return Objects.hash(user, project);
        }
    }

}
