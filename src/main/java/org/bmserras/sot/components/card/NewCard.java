package org.bmserras.sot.components.card;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.vaadin.lineawesome.LineAwesomeIcon;

public class NewCard extends Card {

    public NewCard(Component icon, String title) {
        super(icon, title, "Create new project");
        addClassName("new-card");
    }

    protected void createContextMenu(Component target, boolean openOnClick) {
        ContextMenu contextMenu = new ContextMenu(target);
        contextMenu.setOpenOnClick(openOnClick);
        contextMenu.addItem(new HorizontalLayout(LineAwesomeIcon.PLUS_CIRCLE_SOLID.create(),
                new Span("Create new project")));
        contextMenu.addItem(new HorizontalLayout(LineAwesomeIcon.FILE_ALT.create(),
                new Span("Create from template")));
    }
}
