package org.bmserras.sot.data.domain;

import java.util.Date;
import java.util.Objects;

public class WidgetInstance {

    private long id;
    private String name;
    private Widget widget;

    public WidgetInstance() {
        this.id = new Date().getTime();
        this.name = "";
        this.widget = null;
    }

    public WidgetInstance(String name, Widget widget) {
        this.id = new Date().getTime();
        this.name = name;
        this.widget = widget;
    }

    public WidgetInstance(long id, String name, Widget widget) {
        this.id = id;
        this.name = name;
        this.widget = widget;
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

    @Override
    public String toString() {
        return "WidgetInstance{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", widget=" + widget +
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
