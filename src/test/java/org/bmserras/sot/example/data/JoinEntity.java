package org.bmserras.sot.example.data;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name="join_ab")
@IdClass(ABId.class)
public class JoinEntity implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "a_id", referencedColumnName = "id")
    private EntityA a;

    @Id
    @ManyToOne
    @JoinColumn(name = "b_id", referencedColumnName = "id")
    private EntityB b;

    @Column(name = "join_date")
    private Date date;

    public JoinEntity() {
    }

    public JoinEntity(Date date, EntityA a, EntityB b) {
        this.date = date;
        this.a = a;
        this.b = b;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public EntityA getA() {
        return a;
    }

    public void setA(EntityA a) {
        this.a = a;
    }

    public EntityB getB() {
        return b;
    }

    public void setB(EntityB b) {
        this.b = b;
    }

    @Override
    public String toString() {
        return "JoinEntity{" +
                "date=" + date +
                ", b=" + b +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JoinEntity that = (JoinEntity) o;
        return a.equals(that.a) && b.equals(that.b);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }
}
