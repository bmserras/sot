package org.bmserras.sot.views.widget.list;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.contextmenu.GridContextMenu;
import com.vaadin.flow.component.grid.contextmenu.GridMenuItem;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import org.bmserras.sot.data.domain.Widget;
import org.bmserras.sot.events.widget.WidgetAddEvent;
import org.bmserras.sot.events.widget.WidgetDeleteEvent;
import org.bmserras.sot.events.widget.WidgetEditEvent;
import org.bmserras.sot.events.widget.WidgetOpenEvent;
import org.vaadin.lineawesome.LineAwesomeIcon;

public class WidgetContextMenu extends GridContextMenu<Widget> {

    Component addIcon = LineAwesomeIcon.PLUS_CIRCLE_SOLID.create();
    Component openIcon = LineAwesomeIcon.EXTERNAL_LINK_ALT_SOLID.create();
    Component editIcon = LineAwesomeIcon.EDIT.create();
    Component deleteIcon = LineAwesomeIcon.TRASH_SOLID.create();

    GridMenuItem<Widget> add;
    GridMenuItem<Widget> open;
    GridMenuItem<Widget> edit;
    GridMenuItem<Widget> delete;

    public WidgetContextMenu(Grid<Widget> target) {
        super(target);

        add = addItem(new WidgetContextMenuItem(addIcon, "Add"),
                click -> fireEvent(new WidgetAddEvent(this)));
        open = addItem(new WidgetContextMenuItem(openIcon, "Open"),
                click -> fireEvent(new WidgetOpenEvent(this, click.getItem())));
        edit = addItem(new WidgetContextMenuItem(editIcon, "Edit"),
                click -> fireEvent(new WidgetEditEvent(this, click.getItem())));
        delete = addItem(new WidgetContextMenuItem(deleteIcon, "Delete"),
                click -> {
                    ConfirmDialog confirmDialog = new ConfirmDialog();
                    confirmDialog.setHeader("Delete widget");
                    confirmDialog.setText("Are you sure you want to delete this widget?");

                    confirmDialog.setCancelable(true);
                    confirmDialog.setConfirmText("Yes");
                    confirmDialog.addConfirmListener(confirm -> fireEvent(new WidgetDeleteEvent(this, click.getItem())));

                    confirmDialog.open();
                });

        this.addGridContextMenuOpenedListener(event -> {
            boolean isPresent = event.getItem().isPresent();
            add.setVisible(!isPresent);
            open.setVisible(isPresent);
            edit.setVisible(isPresent);
            delete.setVisible(isPresent);
        });
    }

    public void addAddListener(ComponentEventListener<WidgetAddEvent> listener) {
        addListener(WidgetAddEvent.class, listener);
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

    private static class WidgetContextMenuItem extends HorizontalLayout {
        public WidgetContextMenuItem(Component icon, String text) {
            this.setAlignItems(Alignment.CENTER);
            add(icon, new Span(text));
        }
    }
}