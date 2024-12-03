package org.bmserras.sot.views.synoptic;

import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.bmserras.sot.data.domain.Synoptic;
import org.bmserras.sot.events.synoptic.SynopticDeleteEvent;
import org.bmserras.sot.events.synoptic.SynopticExportEvent;
import org.bmserras.sot.events.synoptic.SynopticOpenEvent;
import org.bmserras.sot.events.synoptic.SynopticSaveEvent;
import org.bmserras.sot.views.components.Toolbar;
import org.bmserras.sot.views.synoptic.card.SynopticCardLayout;

import java.util.List;

public class SynopticLayout extends VerticalLayout {

    private final HorizontalLayout header;
    private final H2 title;
    private final Toolbar toolbar;

    private final SynopticCardLayout cardLayout;

    public SynopticLayout() {
        setSizeFull();
        setMargin(false);

        title = new H2("Synoptics");
        toolbar = new Toolbar();
        header = new HorizontalLayout(title, toolbar);
        header.setWidthFull();
        header.setJustifyContentMode(JustifyContentMode.CENTER);
        header.setAlignItems(Alignment.BASELINE);

        cardLayout = new SynopticCardLayout();

        add(header, cardLayout);
    }

    public void setItems(List<Synoptic> synoptics) {
        cardLayout.setItems(synoptics);
    }

    public void addOpenListener(ComponentEventListener<SynopticOpenEvent> listener) {
        cardLayout.addOpenListener(listener);
    }

    public void addSaveListener(ComponentEventListener<SynopticSaveEvent> listener) {
        cardLayout.addSaveListener(listener);
    }

    public void addDeleteListener(ComponentEventListener<SynopticDeleteEvent> listener) {
        cardLayout.addDeleteListener(listener);
    }

    public void addExportListener(ComponentEventListener<SynopticExportEvent> listener) {
        cardLayout.addExportListener(listener);
    }
}
