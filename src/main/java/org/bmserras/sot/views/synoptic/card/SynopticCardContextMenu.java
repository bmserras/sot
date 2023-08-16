package org.bmserras.sot.views.synoptic.card;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import org.bmserras.sot.data.domain.Synoptic;
import org.bmserras.sot.events.synoptic.SynopticDeleteEvent;
import org.bmserras.sot.events.synoptic.SynopticEditEvent;
import org.bmserras.sot.events.synoptic.SynopticOpenEvent;
import org.vaadin.lineawesome.LineAwesomeIcon;

import java.util.Optional;

public class SynopticCardContextMenu extends ContextMenu {

    private Component openIcon = LineAwesomeIcon.EXTERNAL_LINK_ALT_SOLID.create();
    private Component editIcon = LineAwesomeIcon.EDIT.create();
    private Component deleteIcon = LineAwesomeIcon.TRASH_SOLID.create();

    private MenuItem open;
    private MenuItem edit;
    private MenuItem delete;

    public SynopticCardContextMenu(Component target, Synoptic synoptic) {
        super(target);

        open = addItem(new HorizontalLayout(openIcon, new Span("Open")),
                click -> fireEvent(new SynopticOpenEvent(this, Optional.of(synoptic))));
        edit = addItem(new HorizontalLayout(editIcon, new Span("Edit")),
                click -> fireEvent(new SynopticEditEvent(this, Optional.of(synoptic))));
        delete = addItem(new HorizontalLayout(deleteIcon, new Span("Delete")),
                click -> fireEvent(new SynopticDeleteEvent(this, Optional.of(synoptic))));
    }

    public void addOpenListener(ComponentEventListener<SynopticOpenEvent> listener) {
        addListener(SynopticOpenEvent.class, listener);
    }

    public void addEditListener(ComponentEventListener<SynopticEditEvent> listener) {
        addListener(SynopticEditEvent.class, listener);
    }

    public void addDeleteListener(ComponentEventListener<SynopticDeleteEvent> listener) {
        addListener(SynopticDeleteEvent.class, listener);
    }
}
