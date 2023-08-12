package org.bmserras.sot.data.entity.widgettype;

import org.bmserras.sot.icon.CardIcon;

public class WidgetTypeEntity {

    private String name;
    private CardIcon icon;

    public WidgetTypeEntity() {
    }

    public WidgetTypeEntity(String name, CardIcon icon) {
        this.name = name;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CardIcon getIcon() {
        return icon;
    }

    public void setIcon(CardIcon icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "WidgetTypeEntity{" +
                "name='" + name + '\'' +
                ", icon=" + icon +
                '}';
    }
}
