package org.bmserras.sot.views.widget.card;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import org.bmserras.sot.data.domain.Widget;
import org.bmserras.sot.events.widget.WidgetDeleteEvent;
import org.bmserras.sot.events.widget.WidgetEditEvent;
import org.bmserras.sot.events.widget.WidgetOpenEvent;
import org.vaadin.lineawesome.LineAwesomeIcon;

import java.util.Optional;

public class WidgetCardContextMenu extends ContextMenu {

    private Component openIcon = LineAwesomeIcon.EXTERNAL_LINK_ALT_SOLID.create();
    private Component editIcon = LineAwesomeIcon.EDIT.create();
    private Component deleteIcon = LineAwesomeIcon.TRASH_SOLID.create();

    private MenuItem open;
    private MenuItem edit;
    private MenuItem delete;

    public WidgetCardContextMenu(Component target, Widget widget) {
        super(target);

        open = addItem(new HorizontalLayout(openIcon, new Span("Open")),
                click -> fireEvent(new WidgetOpenEvent(this, Optional.of(widget))));
        edit = addItem(new HorizontalLayout(editIcon, new Span("Edit")),
                click -> fireEvent(new WidgetEditEvent(this, Optional.of(widget))));
        delete = addItem(new HorizontalLayout(deleteIcon, new Span("Delete")),
                click -> fireEvent(new WidgetDeleteEvent(this, Optional.of(widget))));
    }

    public void addOpenListener(ComponentEventListener<WidgetOpenEvent> listener) {
        addListener(WidgetOpenEvent.class, listener);
    }

    public void addEditListener(ComponentEventListener<WidgetEditEvent> listener) {
        addListener(WidgetEditEvent.class, listener);
    }

    public void addDeleteListener(ComponentEventListener<WidgetDeleteEvent> listener) {
        addListener(WidgetDeleteEvent.class, listener);
    }
}
