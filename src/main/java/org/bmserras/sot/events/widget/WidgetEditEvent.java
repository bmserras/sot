package org.bmserras.sot.events.widget;

import com.vaadin.flow.component.Component;
import org.bmserras.sot.data.domain.Project;
import org.bmserras.sot.data.domain.Widget;
import org.bmserras.sot.events.project.ProjectEvent;

import java.util.Optional;

public class WidgetEditEvent extends WidgetEvent {

    public WidgetEditEvent(Component source, Optional<Widget> widget) {
        super(source, widget);
    }
}
