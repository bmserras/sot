package org.bmserras.sot.views.synoptic.list;

import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.shared.Registration;
import org.bmserras.sot.data.entity.synoptic.Synoptic;
import org.bmserras.sot.events.synoptic.SynopticMapEvent;
import org.bmserras.sot.events.synoptic.SynopticOpenEvent;
import org.bmserras.sot.events.synoptic.SynopticRemoveEvent;
import org.vaadin.lineawesome.LineAwesomeIcon;

import java.util.Optional;

public class SynopticToolbar extends HorizontalLayout {

    LineAwesomeIcon openIcon = LineAwesomeIcon.EXTERNAL_LINK_ALT_SOLID;
    LineAwesomeIcon mapIcon = LineAwesomeIcon.MAP_MARKED_ALT_SOLID;
    LineAwesomeIcon removeIcon = LineAwesomeIcon.TRASH_SOLID;

    Button openButton = new Button(openIcon.create());
    Button mapButton = new Button(mapIcon.create());
    Button removeButton = new Button(removeIcon.create());

    Optional<Synoptic> synoptic = Optional.empty();

    public SynopticToolbar() {

        openButton.addClickListener(click -> fireEvent(new SynopticOpenEvent(this, synoptic)));
        mapButton.addClickListener(click -> fireEvent(new SynopticMapEvent(this, synoptic)));
        removeButton.addClickListener(click -> fireEvent(new SynopticRemoveEvent(this, synoptic)));

        add(openButton, mapButton, removeButton);
    }

    public void setSynoptic(Synoptic s) {
        synoptic = Optional.ofNullable(s);
    }

    public Registration addOpenListener(ComponentEventListener<SynopticOpenEvent> listener) {
        return addListener(SynopticOpenEvent.class, listener);
    }

    public Registration addMapListener(ComponentEventListener<SynopticMapEvent> listener) {
        return addListener(SynopticMapEvent.class, listener);
    }

    public Registration addRemoveListener(ComponentEventListener<SynopticRemoveEvent> listener) {
        return addListener(SynopticRemoveEvent.class, listener);
    }
}
