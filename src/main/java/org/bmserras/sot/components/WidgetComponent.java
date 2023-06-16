package org.bmserras.sot.components;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.contextmenu.MenuItem;

public class WidgetComponent extends Component implements HasSize, HasTheme {

    public final ContextMenu contextMenu;

    public WidgetComponent() {
        contextMenu = new ContextMenu();
        contextMenu.setTarget(this);
    }

    public void addContextMenuItem(String item, ComponentEventListener<ClickEvent<MenuItem>> clickListener) {
        contextMenu.addItem(item, clickListener);
    }
}
