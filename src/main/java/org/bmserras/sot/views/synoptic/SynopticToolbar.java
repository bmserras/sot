package org.bmserras.sot.views.synoptic;

import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.shared.Registration;
import org.bmserras.sot.data.entity.synoptic.Synoptic;
import org.bmserras.sot.events.synoptic.MapEvent;
import org.bmserras.sot.events.synoptic.OpenEvent;
import org.bmserras.sot.events.synoptic.RemoveEvent;
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

        openButton.addClickListener(click -> fireEvent(new OpenEvent(this, synoptic)));
        mapButton.addClickListener(click -> fireEvent(new MapEvent(this, synoptic)));
        removeButton.addClickListener(click -> fireEvent(new RemoveEvent(this, synoptic)));

        add(openButton, mapButton, removeButton);
    }

    public void setSynoptic(Synoptic s) {
        synoptic = Optional.ofNullable(s);
    }

    public Registration addOpenListener(ComponentEventListener<OpenEvent> listener) {
        return addListener(OpenEvent.class, listener);
    }

    public Registration addMapListener(ComponentEventListener<MapEvent> listener) {
        return addListener(MapEvent.class, listener);
    }

    public Registration addRemoveListener(ComponentEventListener<RemoveEvent> listener) {
        return addListener(RemoveEvent.class, listener);
    }
}
