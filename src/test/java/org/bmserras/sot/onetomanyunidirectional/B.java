package org.bmserras.sot.onetomanyunidirectional;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class B {

    @Id
    private long id;

    public B() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "B{" +
                "id=" + id +
                '}';
    }
}
