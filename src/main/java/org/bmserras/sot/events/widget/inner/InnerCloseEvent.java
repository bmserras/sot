package org.bmserras.sot.events.widget.inner;

import com.vaadin.flow.component.Component;

import java.util.Optional;

public class InnerCloseEvent extends InnerEvent {

    public InnerCloseEvent(Component source) {
        super(source, Optional.empty());
    }
}
