package org.bmserras.sot.data.domain;

import org.bmserras.sot.data.db.WidgetInstanceDB;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Synoptic {

    private long id;
    private String name;
    private List<WidgetInstance> widgetInstances = new ArrayList<>();

    public Synoptic() {
        this.id = new Date().getTime();
    }

    public Synoptic(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Synoptic(long id, String name, List<WidgetInstance> widgetInstances) {
        this.id = id;
        this.name = name;
        this.widgetInstances = widgetInstances;
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

    public void addWidgetInstance(WidgetInstance widgetInstance) {
        this.widgetInstances.add(widgetInstance);
    }

    @Override
    public String toString() {
        return "Synoptic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", widgetInstances=" + widgetInstances +
                '}';
    }

    public List<WidgetInstance> getWidgetInstances() {
        return widgetInstances;
    }
}
