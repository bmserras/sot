package org.bmserras.sot.views.card;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import org.vaadin.lineawesome.LineAwesomeIcon;

public class NewCard extends Card {

    private static final Component DEFAULT_ICON = LineAwesomeIcon.PLUS_CIRCLE_SOLID.create();
    private static final String DEFAULT_TEXT = "New Card";
    private static final String DEFAULT_TOOLTIP_TEXT = "Create new card";

    public NewCard() {
        this(DEFAULT_ICON, DEFAULT_TEXT, DEFAULT_TOOLTIP_TEXT);
    }

    public NewCard(Component icon, String title, String tooltipText) {
        super(icon, title, tooltipText);
        addClassName("new-card");
    }

    protected void createContextMenu(Component target, boolean openOnClick) {
        ContextMenu contextMenu = new ContextMenu(target);
        contextMenu.setOpenOnClick(openOnClick);
        contextMenu.addItem(new HorizontalLayout(LineAwesomeIcon.PLUS_CIRCLE_SOLID.create(),
                new Span("Create new card")));
        contextMenu.addItem(new HorizontalLayout(LineAwesomeIcon.FILE_ALT.create(),
                new Span("Create from template")));
    }
}
