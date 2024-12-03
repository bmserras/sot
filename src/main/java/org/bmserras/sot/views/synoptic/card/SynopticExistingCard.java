package org.bmserras.sot.views.synoptic.card;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.bmserras.sot.data.domain.Synoptic;
import org.bmserras.sot.events.synoptic.SynopticDeleteEvent;
import org.bmserras.sot.events.synoptic.SynopticEditEvent;
import org.bmserras.sot.events.synoptic.SynopticExportEvent;
import org.bmserras.sot.events.synoptic.SynopticOpenEvent;
import org.vaadin.lineawesome.LineAwesomeIcon;

import java.util.Optional;

public class SynopticExistingCard extends VerticalLayout {

    private final Button mainButton;
    private final HorizontalLayout container;
    private final Span title;
    private final Button optionsButton;

    private SynopticCardContextMenu mainContextMenu;
    private SynopticCardContextMenu optionsContextMenu;

    public SynopticExistingCard(Component icon, String title, String tooltipText, Synoptic synoptic) {
        addClassName("card");

        setSizeFull();
        setMargin(true);

        this.mainButton = new Button(icon);
        this.mainButton.addClassName("main-button");
        this.mainButton.setTooltipText(tooltipText);
        this.mainButton.addClickListener(click -> fireEvent(new SynopticOpenEvent(this, Optional.of(synoptic))));

        this.title = new Span(title);
        this.title.addClassName("title");

        this.optionsButton = new Button(LineAwesomeIcon.ELLIPSIS_V_SOLID.create());
        this.optionsButton.addClassName("options-button");
        this.optionsButton.setTooltipText("Options");

        this.container = new HorizontalLayout(this.title, optionsButton);
        this.container.addClassName("container");

        mainContextMenu = new SynopticCardContextMenu(this, synoptic);
        mainContextMenu.setOpenOnClick(false);

        optionsContextMenu = new SynopticCardContextMenu(optionsButton, synoptic);
        optionsContextMenu.setOpenOnClick(true);

        add(mainButton, container);
    }

    public void addMainListener(ComponentEventListener<SynopticOpenEvent> listener) {
        addListener(SynopticOpenEvent.class, listener);
    }

    public void addOpenListener(ComponentEventListener<SynopticOpenEvent> listener) {
        mainContextMenu.addOpenListener(listener);
        optionsContextMenu.addOpenListener(listener);
        mainContextMenu.addOpenListener(click -> mainContextMenu.close());
        optionsContextMenu.addOpenListener(click -> optionsContextMenu.close());
    }

    public void addEditListener(ComponentEventListener<SynopticEditEvent> listener) {
        mainContextMenu.addEditListener(listener);
        optionsContextMenu.addEditListener(listener);
    }

    public void addDeleteListener(ComponentEventListener<SynopticDeleteEvent> listener) {
        mainContextMenu.addDeleteListener(listener);
        optionsContextMenu.addDeleteListener(listener);
        mainContextMenu.addDeleteListener(click -> mainContextMenu.close());
        optionsContextMenu.addDeleteListener(click -> optionsContextMenu.close());
    }

    public void addExportListener(ComponentEventListener<SynopticExportEvent> listener) {
        mainContextMenu.addExportListener(listener);
        optionsContextMenu.addExportListener(listener);
    }
}
