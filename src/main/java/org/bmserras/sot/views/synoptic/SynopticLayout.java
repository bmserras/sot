package org.bmserras.sot.views.synoptic;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.bmserras.sot.data.domain.Synoptic;
import org.bmserras.sot.events.synoptic.SynopticDeleteEvent;
import org.bmserras.sot.events.synoptic.SynopticOpenEvent;
import org.bmserras.sot.events.synoptic.SynopticSaveEvent;
import org.bmserras.sot.views.components.Toolbar;
import org.bmserras.sot.views.synoptic.card.SynopticCardLayout;
import org.bmserras.sot.views.synoptic.list.SynopticListLayout;
import org.vaadin.lineawesome.LineAwesomeIcon;

import java.util.List;

public class SynopticLayout extends VerticalLayout {

    private final HorizontalLayout header;
    private final H2 title;
    private final Toolbar toolbar;

    private final SynopticCardLayout cardLayout;
    private final SynopticListLayout listLayout;

    private final Button changeViewButton;
    private final Component cardIcon = LineAwesomeIcon.TH_SOLID.create();
    private final Component listIcon = LineAwesomeIcon.LIST_SOLID.create();

    private final Button infoButton;
    private final Component infoIcon = LineAwesomeIcon.INFO_CIRCLE_SOLID.create();
    private final Dialog infoDialog;

    // true = card, false = list
    private boolean view = true;

    public SynopticLayout() {
        setSizeFull();

        title = new H2("My Synoptics");
        toolbar = new Toolbar();
        header = new HorizontalLayout(title, toolbar);
        header.setWidthFull();
        header.expand(title);
        header.setAlignItems(Alignment.BASELINE);

        changeViewButton = toolbar.addButton(cardIcon, click -> changeView());

        infoButton = toolbar.addButton(infoIcon, click -> displayInfo());
        infoDialog = new Dialog("This is an information dialog");

        cardLayout = new SynopticCardLayout();
        listLayout = new SynopticListLayout();
        changeView();

        add(header, cardLayout, listLayout, infoDialog);
    }

    private void displayInfo() {
        infoDialog.open();
    }

    private void changeView() {
        view = !view;
        cardLayout.setVisible(view);
        listLayout.setVisible(!view);
        changeViewButton.setIcon(view ? listIcon : cardIcon);
    }

    public void setItems(List<Synoptic> synoptics) {
        cardLayout.setItems(synoptics);
        listLayout.setItems(synoptics);
    }

    public void addOpenListener(ComponentEventListener<SynopticOpenEvent> listener) {
        cardLayout.addOpenListener(listener);
        listLayout.addOpenListener(listener);
    }

    public void addSaveListener(ComponentEventListener<SynopticSaveEvent> listener) {
        cardLayout.addSaveListener(listener);
        listLayout.addSaveListener(listener);
    }

    public void addDeleteListener(ComponentEventListener<SynopticDeleteEvent> listener) {
        cardLayout.addDeleteListener(listener);
        listLayout.addDeleteListener(listener);
    }
}
