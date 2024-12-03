package org.bmserras.sot.data.domain;

import java.util.Date;
import java.util.Objects;

public class WidgetInstance {

    private long id;
    private String name;
    private Widget widget;

    private int posX;
    private int posY;

    public WidgetInstance() {
        this.id = new Date().getTime();
        this.name = "";
        this.widget = null;
        this.posX = 0;
        this.posY = 0;
    }

    public WidgetInstance(String name, Widget widget) {
        this.id = new Date().getTime();
        this.name = name;
        this.widget = widget;
        this.posX = 0;
        this.posY = 0;
    }

    public WidgetInstance(long id, String name, Widget widget) {
        this.id = id;
        this.name = name;
        this.widget = widget;
        this.posX = 0;
        this.posY = 0;
    }

    public WidgetInstance(long id, String name, Widget widget, int posX, int posY) {
        this.id = id;
        this.name = name;
        this.widget = widget;
        this.posX = posX;
        this.posY = posY;
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

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    @Override
    public String toString() {
        return "WidgetInstance{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", widget=" + widget +
                ", posX=" + posX +
                ", posY=" + posY +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WidgetInstance that = (WidgetInstance) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(widget, that.widget);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, widget);
    }
}
