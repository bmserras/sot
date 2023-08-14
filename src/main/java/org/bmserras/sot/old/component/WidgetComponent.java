package org.bmserras.sot.old.component;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.contextmenu.MenuItem;
import org.bmserras.sot.old.service.WidgetService;
import org.bmserras.sot.old.data.Widget;

public abstract class WidgetComponent extends Component implements HasSize, HasTheme {

    private static final PropertyDescriptor<String, String> nameProperty = PropertyDescriptors.propertyWithDefault(
            "name",
            "Default name");

    private final Widget widget;
    public final ContextMenu contextMenu;

    public final WidgetService widgetService;

    public WidgetComponent(WidgetService widgetService, Widget widget) {
        this.widgetService = widgetService;
        this.widget = widget;

        setWidth(20, Unit.PERCENTAGE);
        setHeight(40, Unit.PERCENTAGE);

        contextMenu = new ContextMenu();
        contextMenu.setTarget(this);

        setName(widget.getName());
    }

    public Widget getWidget() {
        return widget;
    }

    public void addContextMenuItem(String item, ComponentEventListener<ClickEvent<MenuItem>> clickListener) {
        contextMenu.addItem(item, clickListener);
    }

    public String getName() {
        return nameProperty.get(this);
    }

    public void setName(String name) {
        nameProperty.set(this, name);
    }

    public abstract void run();

    public abstract void stop();
}
