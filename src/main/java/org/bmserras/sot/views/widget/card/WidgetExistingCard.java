package org.bmserras.sot.views.widget.card;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.bmserras.sot.data.domain.Widget;
import org.bmserras.sot.events.widget.WidgetDeleteEvent;
import org.bmserras.sot.events.widget.WidgetEditEvent;
import org.bmserras.sot.events.widget.WidgetOpenEvent;
import org.bmserras.sot.views.widget.card.WidgetCardContextMenu;
import org.vaadin.lineawesome.LineAwesomeIcon;

import java.util.Optional;

public class WidgetExistingCard extends VerticalLayout {

    private final Button mainButton;
    private final HorizontalLayout container;
    private final Span title;
    private final Button optionsButton;

    private WidgetCardContextMenu mainContextMenu;
    private WidgetCardContextMenu optionsContextMenu;

    public WidgetExistingCard(Component icon, String title, String tooltipText, Widget widget) {
        addClassName("card");

        this.mainButton = new Button(icon);
        this.mainButton.addClassName("main-button");
        this.mainButton.setTooltipText(tooltipText);
        this.mainButton.addClickListener(click -> fireEvent(new WidgetOpenEvent(this, Optional.of(widget))));

        this.title = new Span(title);
        this.title.addClassName("title");

        this.optionsButton = new Button(LineAwesomeIcon.ELLIPSIS_V_SOLID.create());
        this.optionsButton.addClassName("options-button");
        this.optionsButton.setTooltipText("Options");

        this.container = new HorizontalLayout(this.title, optionsButton);
        this.container.addClassName("container");

        mainContextMenu = new WidgetCardContextMenu(this, widget);
        mainContextMenu.setOpenOnClick(false);

        optionsContextMenu = new WidgetCardContextMenu(optionsButton, widget);
        optionsContextMenu.setOpenOnClick(true);

        add(mainButton, container);
    }

    public void addMainListener(ComponentEventListener<WidgetOpenEvent> listener) {
        addListener(WidgetOpenEvent.class, listener);
    }

    public void addOpenListener(ComponentEventListener<WidgetOpenEvent> listener) {
        mainContextMenu.addOpenListener(listener);
        optionsContextMenu.addOpenListener(listener);
        mainContextMenu.addOpenListener(click -> mainContextMenu.close());
        optionsContextMenu.addOpenListener(click -> optionsContextMenu.close());
    }

    public void addEditListener(ComponentEventListener<WidgetEditEvent> listener) {
        mainContextMenu.addEditListener(listener);
        optionsContextMenu.addEditListener(listener);
    }

    public void addDeleteListener(ComponentEventListener<WidgetDeleteEvent> listener) {
        mainContextMenu.addDeleteListener(listener);
        optionsContextMenu.addDeleteListener(listener);
        mainContextMenu.addDeleteListener(click -> mainContextMenu.close());
        optionsContextMenu.addDeleteListener(click -> optionsContextMenu.close());
    }
}
