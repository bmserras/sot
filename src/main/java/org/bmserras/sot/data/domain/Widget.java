package org.bmserras.sot.data.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Widget {

    private long id;
    private String name;
    private List<ValueReader> readers;
    private List<ValueWriter> writers;
    private List<Property> properties;

    public Widget() {
        this(new Date().getTime(), "");
    }

    public Widget(long id, String name) {
        this(id, name, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    }

    public Widget(long id, String name, List<ValueReader> readers, List<ValueWriter> writers, List<Property> properties) {
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

    public List<ValueReader> getReaders() {
        return readers;
    }

    public void setReaders(List<ValueReader> readers) {
        this.readers = readers;
    }

    public void addReaders(ValueReader... readers) {
        this.readers.addAll(Arrays.asList(readers));
    }

    public void removeReader(ValueReader reader) {
        this.readers.remove(reader);
    }

    public List<ValueWriter> getWriters() {
        return writers;
    }

    public void setWriters(List<ValueWriter> writers) {
        this.writers = writers;
    }

    public void addWriters(ValueWriter... writers) {
        this.writers.addAll(Arrays.asList(writers));
    }

    public void removeWriter(ValueWriter writer) {
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
