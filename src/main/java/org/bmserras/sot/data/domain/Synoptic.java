package org.bmserras.sot.data.domain;

import org.bmserras.sot.data.db.WidgetInstanceDB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Synoptic {

    private long id;

    private String name;

    private List<WidgetInstance> widgetInstances;

    public Synoptic(long id, String name, List<WidgetInstance> widgetInstances) {
        this.id = id;
        this.name = name;
        this.widgetInstances = widgetInstances;
    }

    public Synoptic(long id, String name) {
        this(id, name, new ArrayList<>());
    }

    public Synoptic() {
        this(new Date().getTime(), "Untitled Synoptic");
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

    public List<WidgetInstance> getWidgetInstances() {
        return widgetInstances;
    }

    public void setWidgetInstances(List<WidgetInstance> widgetInstances) {
        this.widgetInstances = widgetInstances;
    }

    public void addWidgetInstance(WidgetInstance widgetInstance) {
        this.widgetInstances.add(widgetInstance);
    }

    public void addWidgetInstance(WidgetInstance... widgetInstances) {
        this.widgetInstances.addAll(Arrays.asList(widgetInstances));
    }

    public void removeWidgetInstance(WidgetInstance widgetInstance) {
        this.widgetInstances.remove(widgetInstance);
    }

    @Override
    public String toString() {
        return "Synoptic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", widgetInstances=" + widgetInstances +
                '}';
    }


}
