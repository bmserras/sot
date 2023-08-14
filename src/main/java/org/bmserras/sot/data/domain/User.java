package org.bmserras.sot.data.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User {

    private long id;
    private String username;
    private String passwordHash;
    private List<Project> projects;

    public User(String username, String passwordHash) {
        this(new Date().getTime(), username, passwordHash, new ArrayList<>());
    }

    public User(long id, String username, String passwordHash) {
        this(id, username, passwordHash, new ArrayList<>());
    }

    public User(long id, String username, String passwordHash, List<Project> projects) {
        this.id = id;
        this.username = username;
        this.passwordHash = passwordHash;
        this.projects = projects;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public void addProject(Project project) {
        this.projects.add(project);
    }

    public void removeProject(Project project) {
        this.projects.remove(project);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", projects=" + projects +
                '}';
    }
}
