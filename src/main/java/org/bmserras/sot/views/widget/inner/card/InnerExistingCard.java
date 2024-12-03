package org.bmserras.sot.views.widget.inner.card;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.bmserras.sot.data.domain.Widget;
import org.bmserras.sot.events.widget.inner.InnerDeleteEvent;
import org.bmserras.sot.events.widget.inner.InnerEditEvent;
import org.bmserras.sot.events.widget.inner.InnerOpenEvent;
import org.vaadin.lineawesome.LineAwesomeIcon;

import java.util.Optional;

public class InnerExistingCard extends VerticalLayout {

    private final Button mainButton;
    private final HorizontalLayout container;
    private final Span title;
    private final Button optionsButton;

    private InnerCardContextMenu mainContextMenu;
    private InnerCardContextMenu optionsContextMenu;

    public InnerExistingCard(Component icon, String title, String tooltipText, Widget inner) {
        addClassName("card");
        addClassName("reader-card");

        this.mainButton = new Button(icon);
        this.mainButton.addClassName("main-button");
        this.mainButton.setTooltipText(tooltipText);
        this.mainButton.addClickListener(click -> fireEvent(new InnerOpenEvent(this, Optional.of(inner))));

        this.title = new Span(title);
        this.title.addClassName("title");

        this.optionsButton = new Button(LineAwesomeIcon.ELLIPSIS_V_SOLID.create());
        this.optionsButton.addClassName("options-button");
        this.optionsButton.setTooltipText("Options");

        this.container = new HorizontalLayout(this.title, optionsButton);
        this.container.addClassName("container");

        mainContextMenu = new InnerCardContextMenu(this, inner);
        mainContextMenu.setOpenOnClick(false);

        optionsContextMenu = new InnerCardContextMenu(optionsButton, inner);
        optionsContextMenu.setOpenOnClick(true);

        add(mainButton, container);
    }

    public void addMainListener(ComponentEventListener<InnerOpenEvent> listener) {
        addListener(InnerOpenEvent.class, listener);
    }

    public void addOpenListener(ComponentEventListener<InnerOpenEvent> listener) {
        mainContextMenu.addOpenListener(listener);
        optionsContextMenu.addOpenListener(listener);
        mainContextMenu.addOpenListener(click -> mainContextMenu.close());
        optionsContextMenu.addOpenListener(click -> optionsContextMenu.close());
    }

    public void addEditListener(ComponentEventListener<InnerEditEvent> listener) {
        mainContextMenu.addEditListener(listener);
        optionsContextMenu.addEditListener(listener);
    }

    public void addDeleteListener(ComponentEventListener<InnerDeleteEvent> listener) {
        mainContextMenu.addDeleteListener(listener);
        optionsContextMenu.addDeleteListener(listener);
        mainContextMenu.addDeleteListener(click -> mainContextMenu.close());
        optionsContextMenu.addDeleteListener(click -> optionsContextMenu.close());
    }
}
