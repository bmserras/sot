package org.bmserras.sot.views.synoptic.list;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.contextmenu.GridContextMenu;
import com.vaadin.flow.component.grid.contextmenu.GridMenuItem;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import org.bmserras.sot.data.domain.Synoptic;
import org.bmserras.sot.events.synoptic.SynopticAddEvent;
import org.bmserras.sot.events.synoptic.SynopticDeleteEvent;
import org.bmserras.sot.events.synoptic.SynopticEditEvent;
import org.bmserras.sot.events.synoptic.SynopticOpenEvent;
import org.vaadin.lineawesome.LineAwesomeIcon;

public class SynopticContextMenu extends GridContextMenu<Synoptic> {

    Component addIcon = LineAwesomeIcon.PLUS_CIRCLE_SOLID.create();
    Component openIcon = LineAwesomeIcon.EXTERNAL_LINK_ALT_SOLID.create();
    Component editIcon = LineAwesomeIcon.EDIT.create();
    Component deleteIcon = LineAwesomeIcon.TRASH_SOLID.create();

    GridMenuItem<Synoptic> add;
    GridMenuItem<Synoptic> open;
    GridMenuItem<Synoptic> edit;
    GridMenuItem<Synoptic> delete;

    public SynopticContextMenu(Grid<Synoptic> target) {
        super(target);

        add = addItem(new SynopticContextMenuItem(addIcon, "Add"),
                click -> fireEvent(new SynopticAddEvent(this)));
        open = addItem(new SynopticContextMenuItem(openIcon, "Open"),
                click -> fireEvent(new SynopticOpenEvent(this, click.getItem())));
        edit = addItem(new SynopticContextMenuItem(editIcon, "Edit"),
                click -> fireEvent(new SynopticEditEvent(this, click.getItem())));
        delete = addItem(new SynopticContextMenuItem(deleteIcon, "Delete"),
                click -> fireEvent(new SynopticDeleteEvent(this, click.getItem())));

        this.addGridContextMenuOpenedListener(event -> {
            boolean isPresent = event.getItem().isPresent();
            add.setVisible(!isPresent);
            open.setVisible(isPresent);
            edit.setVisible(isPresent);
            delete.setVisible(isPresent);
        });
    }

    public void addAddListener(ComponentEventListener<SynopticAddEvent> listener) {
        addListener(SynopticAddEvent.class, listener);
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

    private static class SynopticContextMenuItem extends HorizontalLayout {
        public SynopticContextMenuItem(Component icon, String text) {
            this.setAlignItems(Alignment.CENTER);
            add(icon, new Span(text));
        }
    }
}