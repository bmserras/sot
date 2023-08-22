package org.bmserras.sot.data.db.user;

import jakarta.persistence.*;
import org.bmserras.sot.data.db.AbstractEntity;
import org.bmserras.sot.data.db.project.ProjectDB;
import org.bmserras.sot.data.db.synoptic.SynopticDB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user_account")
public class UserDB extends AbstractEntity {

    @Column(unique = true)
    private String username;

    private String passwordHash;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<UserProjectDB> projects;

    public UserDB() {
        super();
    }

    public UserDB(long identifier) {
        this(identifier, "default name", "default pass");
    }

    public UserDB(String username, String passwordHash) {
        this(new Date().getTime(), username, passwordHash);
    }

    public UserDB(long identifier, String username, String passwordHash) {
        this(identifier, username, passwordHash, new ArrayList<>());
    }

    public UserDB(long identifier, String username, String passwordHash, List<UserProjectDB> projects) {
        super(identifier);
        this.username = username;
        this.passwordHash = passwordHash;
        this.projects = projects;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public List<UserProjectDB> getProjects() {
        return projects;
    }

    public void setProjects(List<UserProjectDB> projects) {
        this.projects = projects;
    }

    public void addProject(ProjectDB projectDB) {
        projects.add(new UserProjectDB(this, projectDB));
    }

    public void addProjects(ProjectDB... projectsDB) {
        for (ProjectDB projectDB : projectsDB) addProject(projectDB);
    }

    @Override
    public String toString() {
        return "UserDB{" +
                "username='" + username + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", projects=" + projects +
                "} " + super.toString();
    }
}