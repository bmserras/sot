package org.bmserras.sot.data.db;

import jakarta.persistence.*;
import org.bmserras.sot.data.db.synoptic.SynopticDB;
import org.bmserras.sot.data.db.widget.WidgetDB;

import java.util.Objects;

@Entity
@Table(name = "widget_instance")
@Inheritance(strategy = InheritanceType.JOINED)
public class WidgetInstanceDB extends AbstractEntity {

    private String name;

    @ManyToOne(targetEntity = SynopticDB.class, fetch = FetchType.EAGER)
    private SynopticDB synoptic;

    @ManyToOne(targetEntity = WidgetDB.class, fetch = FetchType.EAGER)
    private WidgetDB widget;

    private int posX;
    private int posY;

    public WidgetInstanceDB() {
        super();
    }

    public WidgetInstanceDB(long identifier, String name, WidgetDB widgetDB) {
        super(identifier);
        this.name = name;
        this.widget = widgetDB;
        this.posX = 0;
        this.posY = 0;
    }

    public WidgetInstanceDB(long identifier, String name, WidgetDB widgetDB, int posX, int posY) {
        super(identifier);
        this.name = name;
        this.widget = widgetDB;
        this.posX = posX;
        this.posY = posY;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WidgetDB getWidget() {
        return widget;
    }

    public void setWidget(WidgetDB widgetDB) {
        this.widget = widgetDB;
    }

    public void setSynoptic(SynopticDB synopticDB) {
        this.synoptic = synopticDB;
    }

    public SynopticDB getSynoptic() {
        return synoptic;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WidgetInstanceDB that = (WidgetInstanceDB) o;
        return Objects.equals(widget, that.widget);
    }

    @Override
    public int hashCode() {
        return Objects.hash(widget);
    }

    @Override
    public String toString() {
        return "WidgetInstanceDB{" +
                "name='" + name + '\'' +
                ", synoptic=" + synoptic +
                ", widget=" + widget +
                ", posX=" + posX +
                ", posY=" + posY +
                "} " + super.toString();
    }
}
