package org.bmserras.sot.views.card;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import org.vaadin.lineawesome.LineAwesomeIcon;

public class ExistingCard extends Card {

    private static final Component DEFAULT_ICON = LineAwesomeIcon.CHECK_CIRCLE_SOLID.create();
    private static final String DEFAULT_TEXT = "Existing Card";
    private static final String DEFAULT_TOOLTIP_TEXT = "Open card";

    private MenuItem open;
    private MenuItem delete;

    public ExistingCard() {
        this(DEFAULT_ICON, DEFAULT_TEXT, DEFAULT_TOOLTIP_TEXT);
    }

    public ExistingCard(Component icon, String title, String tooltipText) {
        super(icon, title, tooltipText);
    }

    protected void createContextMenu(Component target, boolean openOnClick) {
        ContextMenu contextMenu = new ContextMenu(target);
        contextMenu.setOpenOnClick(openOnClick);
        open = contextMenu.addItem(new HorizontalLayout(LineAwesomeIcon.EYE.create(), new Span("Open")));
        contextMenu.addItem(new HorizontalLayout(LineAwesomeIcon.PENCIL_ALT_SOLID.create(), new Span("Rename")));
        contextMenu.addItem(new HorizontalLayout(LineAwesomeIcon.COPY.create(), new Span("Duplicate")));
        delete = contextMenu.addItem(new HorizontalLayout(LineAwesomeIcon.TRASH_ALT.create(), new Span("Delete")));
    }

    public void addOpenButtonClickListener(ComponentEventListener<ClickEvent<MenuItem>> listener) {
        open.addClickListener(listener);
    }

    public void addDeleteButtonClickListener(ComponentEventListener<ClickEvent<MenuItem>> listener) {
        delete.addClickListener(listener);
    }
}
