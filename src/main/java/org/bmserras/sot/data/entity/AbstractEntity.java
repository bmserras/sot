package org.bmserras.sot.data.entity;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import java.util.Date;
import java.util.Objects;

@MappedSuperclass
public abstract class AbstractEntity {

    @Id
    private long identifier;

    private String name;

    public AbstractEntity() {
        this.identifier = new Date().getTime();
    }

    public AbstractEntity(String name) {
        this();
        this.name = name;
    }

    public AbstractEntity(long identifier, String name) {
        this.identifier = identifier;
        this.name = name;
    }

    public long getIdentifier() {
        return identifier;
    }

    public void setIdentifier(long identifier) {
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract String getPath();

    @Override
    public String toString() {
        return "CustomEntity{" +
                "identifier=" + identifier +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractEntity that = (AbstractEntity) o;
        return identifier == that.identifier;
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier);
    }
}
