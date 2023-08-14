package org.bmserras.sot.data.entity.synoptic;

import jakarta.persistence.*;
import org.bmserras.sot.old.data.Widget;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "synoptic_widget")
@IdClass(SynopticWidget.SynopticWidgetId.class)
public class SynopticWidget implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "synoptic_identifier", referencedColumnName = "identifier")
    private Synoptic synoptic;

    @Id
    @ManyToOne
    @JoinColumn(name = "widget_identifier", referencedColumnName = "identifier")
    private Widget widget;

    private int pos;

    public SynopticWidget() {
    }

    public SynopticWidget(Synoptic synoptic, Widget widget, int pos) {
        this.synoptic = synoptic;
        this.widget = widget;
        this.pos = pos;
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

    public int getPos() {
        return pos;
    }

    public void setPos(int posX) {
        this.pos = posX;
    }

    @Override
    public String toString() {
        return "SynopticWidget{" +
                ", widget=" + widget +
                ", posX=" + pos +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SynopticWidget that = (SynopticWidget) o;
        return pos == that.pos && synoptic.equals(that.synoptic) && widget.equals(that.widget);
    }

    @Override
    public int hashCode() {
        return Objects.hash(synoptic, widget, pos);
    }

    public static class SynopticWidgetId implements Serializable {

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
    }
}
