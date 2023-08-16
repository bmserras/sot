package org.bmserras.sot.views.synoptic.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import org.bmserras.sot.data.domain.Synoptic;
import org.bmserras.sot.events.synoptic.SynopticAddEvent;
import org.bmserras.sot.events.synoptic.SynopticDeleteEvent;
import org.bmserras.sot.events.synoptic.SynopticOpenEvent;
import org.vaadin.lineawesome.LineAwesomeIcon;

import java.util.Optional;

public class SynopticToolbar extends HorizontalLayout {

    Component addIcon = LineAwesomeIcon.PLUS_CIRCLE_SOLID.create();
    Component openIcon = LineAwesomeIcon.EXTERNAL_LINK_ALT_SOLID.create();
    Component deleteIcon = LineAwesomeIcon.TRASH_SOLID.create();

    Button add = new Button(addIcon);
    Button open = new Button(openIcon);
    Button delete = new Button(deleteIcon);

    Synoptic synoptic;

    public SynopticToolbar() {
        add.addClickListener(click -> fireEvent(new SynopticAddEvent(this)));
        open.addClickListener(click -> fireEvent(new SynopticOpenEvent(this, Optional.of(synoptic))));
        delete.addClickListener(click -> fireEvent(new SynopticDeleteEvent(this, Optional.of(synoptic))));

        add(add, open, delete);
    }

    public void setSynoptic(Synoptic p) {
        synoptic = p;
        open.setVisible(p != null);
        delete.setVisible(p != null);
    }

    public void addAddListener(ComponentEventListener<SynopticAddEvent> listener) {
        addListener(SynopticAddEvent.class, listener);
    }

    public void addOpenListener(ComponentEventListener<SynopticOpenEvent> listener) {
        addListener(SynopticOpenEvent.class, listener);
    }

    public void addDeleteListener(ComponentEventListener<SynopticDeleteEvent> listener) {
        addListener(SynopticDeleteEvent.class, listener);
    }
}
