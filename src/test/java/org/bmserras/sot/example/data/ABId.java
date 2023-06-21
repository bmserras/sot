package org.bmserras.sot.example.data;

import java.io.Serializable;
import java.util.Objects;

public class ABId implements Serializable
{
    private Integer a;

    private Integer b;

    public Integer getA()
    {
        return a;
    }

    public Integer getB()
    {
        return b;
    }

    public void setA(Integer a) {
        this.a = a;
    }

    public void setB(Integer b) {
        this.b = b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ABId abId = (ABId) o;
        return Objects.equals(a, abId.a) && Objects.equals(b, abId.b);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }
}
