package org.bmserras.sot.views.widget.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import org.bmserras.sot.data.domain.Widget;
import org.bmserras.sot.events.widget.WidgetAddEvent;
import org.bmserras.sot.events.widget.WidgetDeleteEvent;
import org.bmserras.sot.events.widget.WidgetOpenEvent;
import org.vaadin.lineawesome.LineAwesomeIcon;

import java.util.Optional;

public class WidgetToolbar extends HorizontalLayout {

    Component addIcon = LineAwesomeIcon.PLUS_CIRCLE_SOLID.create();
    Component openIcon = LineAwesomeIcon.EXTERNAL_LINK_ALT_SOLID.create();
    Component deleteIcon = LineAwesomeIcon.TRASH_SOLID.create();

    Button add = new Button(addIcon);
    Button open = new Button(openIcon);
    Button delete = new Button(deleteIcon);

    Widget widget;

    public WidgetToolbar() {
        add.addClickListener(click -> fireEvent(new WidgetAddEvent(this)));
        open.addClickListener(click -> fireEvent(new WidgetOpenEvent(this, Optional.of(widget))));
        delete.addClickListener(click -> fireEvent(new WidgetDeleteEvent(this, Optional.of(widget))));

        add(add, open, delete);
    }

    public void setWidget(Widget w) {
        widget = w;
        open.setVisible(w != null);
        delete.setVisible(w != null);
    }

    public void addAddListener(ComponentEventListener<WidgetAddEvent> listener) {
        addListener(WidgetAddEvent.class, listener);
    }

    public void addOpenListener(ComponentEventListener<WidgetOpenEvent> listener) {
        addListener(WidgetOpenEvent.class, listener);
    }

    public void addDeleteListener(ComponentEventListener<WidgetDeleteEvent> listener) {
        addListener(WidgetDeleteEvent.class, listener);
    }
}
