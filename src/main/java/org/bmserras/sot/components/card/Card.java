package org.bmserras.sot.components.card;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.vaadin.lineawesome.LineAwesomeIcon;

public abstract class Card extends VerticalLayout {

    private final Button mainButton;
    private final HorizontalLayout container;
    private final Span title;
    private final Button optionsButton;

    public Card(Component icon, String title, String tooltipText) {
        addClassName("card");

        this.mainButton = new Button(icon);
        this.mainButton.addClassName("main-button");
        this.mainButton.setTooltipText(tooltipText);

        this.title = new Span(title);
        this.title.addClassName("title");

        this.optionsButton = new Button(LineAwesomeIcon.ELLIPSIS_V_SOLID.create());
        this.optionsButton.addClassName("options-button");
        this.optionsButton.setTooltipText("Options");

        this.container = new HorizontalLayout(this.title, optionsButton);
        this.container.addClassName("container");

        createContextMenu(this, false);
        createContextMenu(optionsButton, true);

        add(mainButton, container);
    }

    protected abstract void createContextMenu(Component target, boolean openOnClick);

    public void addMainButtonClickListener(ComponentEventListener<ClickEvent<Button>> listener) {
        mainButton.addClickListener(listener);
    }
}
