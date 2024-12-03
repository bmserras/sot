package org.bmserras.sot.data.domain.writers;

import java.util.Date;

public class Writer {

    private long id;
    private String name;

    public Writer() {
        this(new Date().getTime(), "Default name");
    }

    public Writer(long id, String name) {
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
        return "Writer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
