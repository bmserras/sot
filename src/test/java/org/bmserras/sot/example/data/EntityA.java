package org.bmserras.sot.example.data;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "table_a")
public class EntityA {

    @Id
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "a", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<JoinEntity> bs;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<JoinEntity> getBs() {
        return bs;
    }

    public void setBs(List<JoinEntity> bs) {
        this.bs = bs;
    }

    public void addB(EntityB b) {
        bs.add(new JoinEntity(new Date(), this, b));
    }

    public void removeB(EntityB b) {
        bs.remove(new JoinEntity(new Date(), this, b));
    }

    @Override
    public String toString() {
        return "EntityA{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", bs=" + bs +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntityA entityA = (EntityA) o;
        return Objects.equals(id, entityA.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
