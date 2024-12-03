package org.bmserras.sot.events.widget.inner;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import org.bmserras.sot.data.domain.Widget;

import java.util.Optional;

public class InnerEvent extends ComponentEvent<Component> {

    private final Optional<Widget> inner;

    public InnerEvent(Component source, Optional<Widget> inner) {
        super(source, false);
        this.inner = inner;
    }

    public Optional<Widget> getInner() {
        return inner;
    }

}
