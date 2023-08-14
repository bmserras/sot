package org.bmserras.sot.data.db;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import java.util.Date;
import java.util.Objects;

@MappedSuperclass
public abstract class AbstractEntity {

    @Id
    private long identifier;

    public AbstractEntity() {
        super();
    }

    public AbstractEntity(long identifier) {
        this.identifier = identifier;
    }

    public long getIdentifier() {
        return identifier;
    }

    public void setIdentifier(long identifier) {
        this.identifier = identifier;
    }

    @Override
    public String toString() {
        return "AbstractEntity{" +
                "identifier=" + identifier +
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
