package org.bmserras.sot.data.domain.readers;

import java.util.Date;

public abstract class Reader {

    private long id;
    private String name;

    public Reader() {
        this(new Date().getTime(), "Default");
    }

    public Reader(long id, String name) {
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
        return "ValueReader{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
