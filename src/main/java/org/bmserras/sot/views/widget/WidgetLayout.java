package org.bmserras.sot.views.widget;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.bmserras.sot.data.domain.Widget;
import org.bmserras.sot.events.widget.WidgetDeleteEvent;
import org.bmserras.sot.events.widget.WidgetOpenEvent;
import org.bmserras.sot.events.widget.WidgetSaveEvent;
import org.bmserras.sot.views.components.Toolbar;
import org.bmserras.sot.views.widget.card.WidgetCardLayout;
import org.bmserras.sot.views.widget.list.WidgetListLayout;
import org.vaadin.lineawesome.LineAwesomeIcon;

import java.util.List;

public class WidgetLayout extends VerticalLayout {

    private final HorizontalLayout header;
    private final H2 title;
    private final Toolbar toolbar;

    private final WidgetCardLayout cardLayout;
    private final WidgetListLayout listLayout;

    private final Button changeViewButton;
    private final Component cardIcon = LineAwesomeIcon.TH_SOLID.create();
    private final Component listIcon = LineAwesomeIcon.LIST_SOLID.create();

    private final Button infoButton;
    private final Component infoIcon = LineAwesomeIcon.INFO_CIRCLE_SOLID.create();
    private final Dialog infoDialog;

    // true = card, false = list
    private boolean view = false;

    public WidgetLayout(String name) {
        setSizeFull();
        setMargin(true);
        title = new H2(name);
        toolbar = new Toolbar();
        header = new HorizontalLayout(title, toolbar);
        header.setWidthFull();
        header.expand(title);
        header.setAlignItems(Alignment.BASELINE);

        changeViewButton = toolbar.addButton(cardIcon, click -> changeView());

        infoButton = toolbar.addButton(infoIcon, click -> displayInfo());
        infoDialog = new Dialog("This is an information dialog");

        cardLayout = new WidgetCardLayout();
        listLayout = new WidgetListLayout();
        changeView();

        add(header, cardLayout, listLayout, infoDialog);
    }

    private void displayInfo() {
        infoDialog.open();
    }

    private void changeView() {
        view = !view;
        cardLayout.setVisible(view);
        listLayout.setVisible(!view);
        changeViewButton.setIcon(view ? listIcon : cardIcon);
    }

    public void setItems(List<Widget> widgets) {
        cardLayout.setItems(widgets);
        listLayout.setItems(widgets);
    }

    public void addOpenListener(ComponentEventListener<WidgetOpenEvent> listener) {
        listLayout.addOpenListener(listener);
        cardLayout.addOpenListener(listener);
    }

    public void addSaveListener(ComponentEventListener<WidgetSaveEvent> listener) {
        listLayout.addSaveListener(listener);
        cardLayout.addSaveListener(listener);
    }

    public void addDeleteListener(ComponentEventListener<WidgetDeleteEvent> listener) {
        listLayout.addDeleteListener(listener);
        cardLayout.addDeleteListener(listener);
    }
}
