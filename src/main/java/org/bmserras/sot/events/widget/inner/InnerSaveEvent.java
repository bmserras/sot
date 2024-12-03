package org.bmserras.sot.events.widget.inner;

import com.vaadin.flow.component.Component;
import org.bmserras.sot.data.domain.Widget;

import java.util.Optional;

public class InnerSaveEvent extends InnerEvent {

    public InnerSaveEvent(Component source, Optional<Widget> inner) {
        super(source, inner);
    }
}
