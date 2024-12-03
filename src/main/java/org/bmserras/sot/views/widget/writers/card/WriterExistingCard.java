package org.bmserras.sot.views.widget.writers.card;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.bmserras.sot.data.domain.writers.Writer;
import org.bmserras.sot.events.widget.writer.WriterDeleteEvent;
import org.bmserras.sot.events.widget.writer.WriterEditEvent;
import org.bmserras.sot.events.widget.writer.WriterOpenEvent;
import org.vaadin.lineawesome.LineAwesomeIcon;

import java.util.Optional;

public class WriterExistingCard extends VerticalLayout {

    private final Button mainButton;
    private final HorizontalLayout container;
    private final Span title;
    private final Button optionsButton;

    private WriterCardContextMenu mainContextMenu;
    private WriterCardContextMenu optionsContextMenu;

    public WriterExistingCard(Component icon, String title, String tooltipText, Writer writer) {
        addClassName("card");
        addClassName("reader-card");

        this.mainButton = new Button(icon);
        this.mainButton.addClassName("main-button");
        this.mainButton.setTooltipText(tooltipText);
        this.mainButton.addClickListener(click -> fireEvent(new WriterOpenEvent(this, Optional.of(writer))));

        this.title = new Span(title);
        this.title.addClassName("title");

        this.optionsButton = new Button(LineAwesomeIcon.ELLIPSIS_V_SOLID.create());
        this.optionsButton.addClassName("options-button");
        this.optionsButton.setTooltipText("Options");

        this.container = new HorizontalLayout(this.title, optionsButton);
        this.container.addClassName("container");

        mainContextMenu = new WriterCardContextMenu(this, writer);
        mainContextMenu.setOpenOnClick(false);

        optionsContextMenu = new WriterCardContextMenu(optionsButton, writer);
        optionsContextMenu.setOpenOnClick(true);

        add(mainButton, container);
    }

    public void addMainListener(ComponentEventListener<WriterOpenEvent> listener) {
        addListener(WriterOpenEvent.class, listener);
    }

    public void addOpenListener(ComponentEventListener<WriterOpenEvent> listener) {
        mainContextMenu.addOpenListener(listener);
        optionsContextMenu.addOpenListener(listener);
        mainContextMenu.addOpenListener(click -> mainContextMenu.close());
        optionsContextMenu.addOpenListener(click -> optionsContextMenu.close());
    }

    public void addEditListener(ComponentEventListener<WriterEditEvent> listener) {
        mainContextMenu.addEditListener(listener);
        optionsContextMenu.addEditListener(listener);
    }

    public void addDeleteListener(ComponentEventListener<WriterDeleteEvent> listener) {
        mainContextMenu.addDeleteListener(listener);
        optionsContextMenu.addDeleteListener(listener);
        mainContextMenu.addDeleteListener(click -> mainContextMenu.close());
        optionsContextMenu.addDeleteListener(click -> optionsContextMenu.close());
    }
}
