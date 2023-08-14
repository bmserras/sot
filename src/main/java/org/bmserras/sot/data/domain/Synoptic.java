package org.bmserras.sot.data.domain;

import java.util.Date;

public class Synoptic {

    private long id;
    private String name;

    public Synoptic() {
        this.id = new Date().getTime();
    }

    public Synoptic(long id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return "Synoptic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
