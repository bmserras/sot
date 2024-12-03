package org.bmserras.sot.events.widget.inner;

import com.vaadin.flow.component.Component;

import java.util.Optional;

public class InnerAddEvent extends InnerEvent {

    public InnerAddEvent(Component source) {
        super(source, Optional.empty());
    }
}
