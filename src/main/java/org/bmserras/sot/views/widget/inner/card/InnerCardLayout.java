package org.bmserras.sot.views.widget.inner.card;

import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.bmserras.sot.data.domain.Widget;
import org.bmserras.sot.data.domain.WidgetImage;
import org.bmserras.sot.data.service.WidgetService;
import org.bmserras.sot.events.widget.inner.InnerDeleteEvent;
import org.bmserras.sot.events.widget.inner.InnerOpenEvent;
import org.bmserras.sot.events.widget.inner.InnerSaveEvent;
import org.bmserras.sot.icon.CardIcon;
import org.bmserras.sot.icon.CustomIcon;
import org.bmserras.sot.icon.IsosIcon;
import org.bmserras.sot.icon.LaIcon;
import org.bmserras.sot.views.widget.inner.InnerDialog;
import org.vaadin.lineawesome.LineAwesomeIcon;

import java.util.ArrayList;
import java.util.List;

public class InnerCardLayout extends VerticalLayout {

    private final List<ComponentEventListener<InnerOpenEvent>> openListeners;
    private final List<ComponentEventListener<InnerDeleteEvent>> deleteListeners;

    private final List<InnerExistingCard> existingCards;
    private final HorizontalLayout cards;
    private final InnerNewCard newCard;

    private InnerDialog innerDialog;

    private List<Widget> inners = new ArrayList<>();

    public InnerCardLayout(WidgetService widgetService) {
        setSizeFull();
        setPadding(false);

        newCard = new InnerNewCard(
                LineAwesomeIcon.PLUS_CIRCLE_SOLID.create(),
                "New inner widget",
                "Create new inner widget"
        );
        newCard.addMainListener(click -> {
            /*form.setProject(new Project());*/innerDialog = new InnerDialog(widgetService);
            innerDialog.addSaveListener(save -> {
                save.getInner().ifPresent(inners::add);
                setItems(inners);
                innerDialog.close();
                System.out.println("Saving inner widget: " + save.getInner().get());
            });
            innerDialog.open();
        });

        existingCards = new ArrayList<>();
        cards = new HorizontalLayout();
        cards.setWidthFull();
        cards.setHeight("40%");

        openListeners = new ArrayList<>();
        openListeners.add(click -> System.out.println(click.getInner().get()));
        deleteListeners = new ArrayList<>();

        add(cards);

        setItems(inners);
    }

    public List<Widget> getItems() {
        return inners;
    }

    public void setItems(List<Widget> inners) {
        this.inners = inners;
        existingCards.clear();
        cards.removeAll();
        cards.add(newCard);
        for (Widget inner : inners) {
            String type = inner.getImage().getType();
            CardIcon cardIcon = null;
            if (type.equals("icon/la")) {
                cardIcon = LaIcon.valueOf(inner.getImage().getIconName()).create();
            }
            else if (type.equals("icon/isos")) {
                cardIcon = IsosIcon.valueOf(inner.getImage().getIconName()).create();
            }
            else if (type.equals("icon/custom")) {
                cardIcon = CustomIcon.valueOf(inner.getImage().getIconName()).create();
            }
            InnerExistingCard existingCard = new InnerExistingCard(cardIcon,
                    inner.getName(), "Open inner", inner);
            existingCards.add(existingCard);
            cards.add(existingCard);
            openListeners.forEach(existingCard::addMainListener);
            openListeners.forEach(existingCard::addOpenListener);
            deleteListeners.forEach(existingCard::addDeleteListener);

            existingCard.addEditListener(event -> {

            });
        }
    }

    public void addOpenListener(ComponentEventListener<InnerOpenEvent> listener) {
        existingCards.forEach(card -> card.addOpenListener(listener));
        existingCards.forEach(card -> card.addMainListener(listener));
        openListeners.add(listener);
    }

    public void addSaveListener(ComponentEventListener<InnerSaveEvent> listener) {
        innerDialog.addSaveListener(listener);
    }

    public void addDeleteListener(ComponentEventListener<InnerDeleteEvent> listener) {
        existingCards.forEach(card -> card.addDeleteListener(listener));
        deleteListeners.add(listener);
    }
}
