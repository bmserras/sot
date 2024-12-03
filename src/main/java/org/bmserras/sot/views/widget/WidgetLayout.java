package org.bmserras.sot.views.widget;

import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.bmserras.sot.data.domain.Widget;
import org.bmserras.sot.data.service.WidgetService;
import org.bmserras.sot.events.widget.WidgetDeleteEvent;
import org.bmserras.sot.events.widget.WidgetOpenEvent;
import org.bmserras.sot.events.widget.WidgetSaveEvent;
import org.bmserras.sot.views.components.Toolbar;
import org.bmserras.sot.views.widget.card.WidgetCardLayout;

import java.util.List;

public class WidgetLayout extends VerticalLayout {

    private final HorizontalLayout header;
    private final H2 title;
    private final Toolbar toolbar;

    private final WidgetCardLayout cardLayout;

    public WidgetLayout(String name, WidgetService widgetService) {
        setSizeFull();
        setMargin(false);

        title = new H2(name);
        toolbar = new Toolbar();
        header = new HorizontalLayout(title, toolbar);
        header.setWidthFull();
        header.setJustifyContentMode(JustifyContentMode.CENTER);
        header.setAlignItems(Alignment.BASELINE);

        cardLayout = new WidgetCardLayout(widgetService);

        add(header, cardLayout);
    }

    public void setItems(List<Widget> widgets) {
        cardLayout.setItems(widgets);

    }

    public void addOpenListener(ComponentEventListener<WidgetOpenEvent> listener) {
        cardLayout.addOpenListener(listener);
    }

    public void addSaveListener(ComponentEventListener<WidgetSaveEvent> listener) {
        cardLayout.addSaveListener(listener);
    }

    public void addDeleteListener(ComponentEventListener<WidgetDeleteEvent> listener) {
        cardLayout.addDeleteListener(listener);
    }
}
