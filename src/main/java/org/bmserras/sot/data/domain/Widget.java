package org.bmserras.sot.data.domain;

import org.bmserras.sot.data.domain.readers.Reader;
import org.bmserras.sot.data.domain.writers.Writer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Widget {

    private long id;

    private String name;

    private List<Reader> readers;

    private List<Writer> writers;

    private List<Property> properties;

    public Widget() {
        this(new Date().getTime(), "");
    }

    public Widget(long id, String name) {
        this(id, name, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    }

    public Widget(long id, String name, List<Reader> readers, List<Writer> writers, List<Property> properties) {
        this.id = id;
        this.name = name;
        this.readers = readers;
        this.writers = writers;
        this.properties = properties;
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

    public List<Reader> getReaders() {
        return readers;
    }

    public void setReaders(List<Reader> readers) {
        this.readers = readers;
    }

    public void addReaders(Reader... readers) {
        this.readers.addAll(Arrays.asList(readers));
    }

    public void removeReader(Reader reader) {
        this.readers.remove(reader);
    }

    public List<Writer> getWriters() {
        return writers;
    }

    public void setWriters(List<Writer> writers) {
        this.writers = writers;
    }

    public void addWriters(Writer... writers) {
        this.writers.addAll(Arrays.asList(writers));
    }

    public void removeWriter(Writer writer) {
        this.writers.remove(writer);
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    public void addProperties(Property... properties) {
        this.properties.addAll(Arrays.asList(properties));
    }

    public void removeProperty(Property property) {
        this.properties.remove(property);
    }

    @Override
    public String toString() {
        return "Widget{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", readers=" + readers +
                ", writers=" + writers +
                ", properties=" + properties +
                '}';
    }
}
