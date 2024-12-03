package org.bmserras.sot.views.widget.card;

import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.theme.lumo.LumoUtility;
import org.bmserras.sot.data.domain.Widget;
import org.bmserras.sot.data.service.WidgetService;
import org.bmserras.sot.events.widget.WidgetDeleteEvent;
import org.bmserras.sot.events.widget.WidgetOpenEvent;
import org.bmserras.sot.events.widget.WidgetSaveEvent;
import org.bmserras.sot.icon.*;
import org.bmserras.sot.views.widget.components.WidgetForm;
import org.vaadin.lineawesome.LineAwesomeIcon;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

public class WidgetCardLayout extends FlexLayout {

    private final List<ComponentEventListener<WidgetOpenEvent>> openListeners;
    private final List<ComponentEventListener<WidgetDeleteEvent>> deleteListeners;

    private final List<WidgetExistingCard> existingCards;
    private final WidgetNewCard newCard;

    private WidgetForm form;

    public WidgetCardLayout(WidgetService widgetService) {
        setSizeFull();

        this.setFlexDirection(FlexDirection.ROW);
        this.setFlexWrap(FlexWrap.WRAP);
        this.setJustifyContentMode(JustifyContentMode.CENTER);

        form = new WidgetForm(widgetService);

        form.addSaveListener(click -> form.close());
        form.addCloseListener(click -> form.close());

        newCard = new WidgetNewCard(
                LineAwesomeIcon.PLUS_SQUARE_SOLID.create(),
                "New Widget",
                "Create new widget"
        );
        newCard.addMainListener(click -> {
            form.setWidget(new Widget());
            form.open();
        });

        existingCards = new ArrayList<>();

        openListeners = new ArrayList<>();
        deleteListeners = new ArrayList<>();

        add(newCard);
    }

    public void setItems(List<Widget> widgets) {
        existingCards.clear();
        removeAll();
        add(newCard);
        for (Widget widget : widgets) {
            WidgetExistingCard existingCard;
            String type = widget.getImage().getType();
            if (type.equals("icon/la")) {
                CardIcon cardIcon = LaIcon.valueOf(widget.getImage().getIconName()).create();
                existingCard = new WidgetExistingCard(cardIcon, widget.getName(), "Open widget", widget);
            }
            else if (type.equals("icon/isos")) {
                CardIcon cardIcon = IsosIcon.valueOf(widget.getImage().getIconName()).create();
                existingCard = new WidgetExistingCard(cardIcon, widget.getName(), "Open widget", widget);
            }
            else if (type.equals("icon/custom")) {
                CardIcon cardIcon = CustomIcon.valueOf(widget.getImage().getIconName()).create();
                existingCard = new WidgetExistingCard(cardIcon, widget.getName(), "Open widget", widget);
            }
            else {
                byte[] data = widget.getImage().getData();
                Image image = new Image(new StreamResource("image", () -> new ByteArrayInputStream(data)), "image");


                if (widget.getName().equals("Cabin A")) {
                    image.setWidth("1px");
                    image.setHeight("1px");
                }
                else if (widget.getName().equals("Cinemometer A")) {
                    image.setWidth("1px");
                    image.setHeight("1px");
                }
                else {
                    //image.setWidth("100px");
                    //image.setHeight("87px");
                    image.setWidth("10%");
                    image.setHeight("10%");
                }
                existingCard = new WidgetExistingCard(image, widget.getName(), "Open widget", widget);
            }
            existingCards.add(existingCard);
            add(existingCard);
            openListeners.forEach(existingCard::addMainListener);
            openListeners.forEach(existingCard::addOpenListener);
            deleteListeners.forEach(existingCard::addDeleteListener);

            existingCard.addEditListener(event -> {
                form.setWidget(widget);
                form.open();
            });
        }
    }

    public void addOpenListener(ComponentEventListener<WidgetOpenEvent> listener) {
        existingCards.forEach(card -> card.addOpenListener(listener));
        existingCards.forEach(card -> card.addMainListener(listener));
        openListeners.add(listener);
    }

    public void addSaveListener(ComponentEventListener<WidgetSaveEvent> listener) {
        form.addSaveListener(listener);
    }

    public void addDeleteListener(ComponentEventListener<WidgetDeleteEvent> listener) {
        existingCards.forEach(card -> card.addDeleteListener(listener));
        deleteListeners.add(listener);
    }
}
