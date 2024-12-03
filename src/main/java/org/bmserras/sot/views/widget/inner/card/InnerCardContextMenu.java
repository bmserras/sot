package org.bmserras.sot.views.widget.inner.card;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import org.bmserras.sot.data.domain.Widget;
import org.bmserras.sot.events.widget.inner.InnerDeleteEvent;
import org.bmserras.sot.events.widget.inner.InnerEditEvent;
import org.bmserras.sot.events.widget.inner.InnerOpenEvent;
import org.vaadin.lineawesome.LineAwesomeIcon;

import java.util.Optional;

public class InnerCardContextMenu extends ContextMenu {

    private Component openIcon = LineAwesomeIcon.EXTERNAL_LINK_ALT_SOLID.create();
    private Component editIcon = LineAwesomeIcon.EDIT.create();
    private Component deleteIcon = LineAwesomeIcon.TRASH_SOLID.create();

    private MenuItem open;
    private MenuItem edit;
    private MenuItem delete;

    public InnerCardContextMenu(Component target, Widget inner) {
        super(target);

        open = addItem(new HorizontalLayout(openIcon, new Span("Open")),
                click -> fireEvent(new InnerOpenEvent(this, Optional.of(inner))));
        edit = addItem(new HorizontalLayout(editIcon, new Span("Edit")),
                click -> fireEvent(new InnerEditEvent(this, Optional.of(inner))));
        delete = addItem(new HorizontalLayout(deleteIcon, new Span("Delete")),
                click -> fireEvent(new InnerDeleteEvent(this, Optional.of(inner))));
    }

    public void addOpenListener(ComponentEventListener<InnerOpenEvent> listener) {
        addListener(InnerOpenEvent.class, listener);
    }

    public void addEditListener(ComponentEventListener<InnerEditEvent> listener) {
        addListener(InnerEditEvent.class, listener);
    }

    public void addDeleteListener(ComponentEventListener<InnerDeleteEvent> listener) {
        addListener(InnerDeleteEvent.class, listener);
    }
}
