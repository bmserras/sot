package org.bmserras.sot.data.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/*@Entity
@Table(name = "synoptic_widget")
@IdClass(SynopticWidget.SynopticWidgetId.class)*/
public class SynopticWidget {

    /*@Id
    @ManyToOne
    @JoinColumn(name = "synoptic_identifier", referencedColumnName = "identifier")
    private Synoptic synoptic;

    @Id
    @ManyToOne
    @JoinColumn(name = "widget_identifier", referencedColumnName = "identifier")
    private Widget widget;

    private int posX;
    private int posy;

    public SynopticWidget() {
    }

    public Synoptic getSynoptic() {
        return synoptic;
    }

    public void setSynoptic(Synoptic synoptic) {
        this.synoptic = synoptic;
    }

    public Widget getWidget() {
        return widget;
    }

    public void setWidget(Widget widget) {
        this.widget = widget;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosy() {
        return posy;
    }

    public void setPosy(int posy) {
        this.posy = posy;
    }

    @Override
    public String toString() {
        return "SynopticWidget{" +
                "synoptic=" + synoptic +
                ", widget=" + widget +
                ", posX=" + posX +
                ", posy=" + posy +
                '}';
    }

    static class SynopticWidgetId implements Serializable {

        private long synoptic;
        private long widget;

        public long getSynoptic() {
            return synoptic;
        }

        public void setSynoptic(long synoptic) {
            this.synoptic = synoptic;
        }

        public long getWidget() {
            return widget;
        }

        public void setWidget(long widget) {
            this.widget = widget;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            SynopticWidgetId that = (SynopticWidgetId) o;
            return synoptic == that.synoptic && widget == that.widget;
        }

        @Override
        public int hashCode() {
            return Objects.hash(synoptic, widget);
        }
    }*/
}
