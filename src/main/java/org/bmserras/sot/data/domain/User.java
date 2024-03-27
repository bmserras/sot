package org.bmserras.sot.data.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class User {

    private long id;

    private String username;

    private String passwordHash;

    private List<Project> projects;

    private List<Widget> widgets;

    public User(long id, String username, String passwordHash, List<Project> projects, List<Widget> widgets) {
        this.id = id;
        this.username = username;
        this.passwordHash = passwordHash;
        this.projects = projects;
        this.widgets = widgets;
    }

    public User(long id, String username, String passwordHash) {
        this(id, username, passwordHash, new ArrayList<>(), new ArrayList<>());
    }

    public User(String username, String passwordHash) {
        this(new Date().getTime(), username, passwordHash);
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

    public void addProjects(Project... projects) {
        this.projects.addAll(Arrays.asList(projects));
    }

    public void removeProject(Project project) {
        this.projects.remove(project);
    }
    
    public List<Widget> getWidgets() {
        return widgets;
    }

    public void setWidgets(List<Widget> widgets) {
        this.widgets = widgets;
    }

    public void addWidget(Widget widget) {
        this.widgets.add(widget);
    }

    public void addWidgets(Widget... widgets) {
        this.widgets.addAll(Arrays.asList(widgets));
    }

    public void removeWidget(Widget widget) {
        this.widgets.remove(widget);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", projects=" + projects +
                ", widgets=" + widgets +
                '}';
    }
}
