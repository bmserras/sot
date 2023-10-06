package org.bmserras.sot.views.synoptic.card;

import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.bmserras.sot.data.domain.Synoptic;
import org.bmserras.sot.events.synoptic.SynopticDeleteEvent;
import org.bmserras.sot.events.synoptic.SynopticOpenEvent;
import org.bmserras.sot.events.synoptic.SynopticSaveEvent;
import org.bmserras.sot.views.synoptic.components.SynopticForm;
import org.vaadin.lineawesome.LineAwesomeIcon;

import java.util.ArrayList;
import java.util.List;

public class SynopticCardLayout extends VerticalLayout {

    private final List<ComponentEventListener<SynopticOpenEvent>> openListeners;
    private final List<ComponentEventListener<SynopticDeleteEvent>> deleteListeners;

    private final List<SynopticExistingCard> existingCards;
    private final HorizontalLayout cards;
    private final SynopticNewCard newCard;

    private Dialog dialogForm;
    private SynopticForm form;

    public SynopticCardLayout() {
        setSizeFull();

        configureDialogForm();

        newCard = new SynopticNewCard(
                LineAwesomeIcon.PLUS_CIRCLE_SOLID.create(),
                "New Synoptic",
                "Create new synoptic"
        );
        newCard.addMainListener(click -> {
            form.setSynoptic(new Synoptic());
            dialogForm.open();
        });

        existingCards = new ArrayList<>();
        cards = new HorizontalLayout();
        cards.setWidthFull();
        cards.setHeight("40%");

        openListeners = new ArrayList<>();
        deleteListeners = new ArrayList<>();

        add(cards);
    }

    private void configureDialogForm() {
        form = new SynopticForm();
        form.setWidth("25em");
        dialogForm = new Dialog(form);

        form.addSaveListener(click -> dialogForm.close());
        form.addCloseListener(click -> dialogForm.close());
        form.addDeleteListener(click -> dialogForm.close());
    }

    public void setItems(List<Synoptic> synoptics) {
        existingCards.clear();
        cards.removeAll();
        cards.add(newCard);
        for (Synoptic synoptic : synoptics) {
            SynopticExistingCard existingCard = new SynopticExistingCard(LineAwesomeIcon.CHART_PIE_SOLID.create(),
                    synoptic.getName(), "Open synoptic", synoptic);
            existingCards.add(existingCard);
            cards.add(existingCard);
            openListeners.forEach(existingCard::addMainListener);
            openListeners.forEach(existingCard::addOpenListener);
            deleteListeners.forEach(existingCard::addDeleteListener);

            existingCard.addEditListener(event -> {
                form.setSynoptic(synoptic);
                dialogForm.open();
            });
        }
    }

    public void addOpenListener(ComponentEventListener<SynopticOpenEvent> listener) {
        existingCards.forEach(card -> card.addOpenListener(listener));
        existingCards.forEach(card -> card.addMainListener(listener));
        openListeners.add(listener);
    }

    public void addSaveListener(ComponentEventListener<SynopticSaveEvent> listener) {
        form.addSaveListener(listener);
    }

    public void addDeleteListener(ComponentEventListener<SynopticDeleteEvent> listener) {
        existingCards.forEach(card -> card.addDeleteListener(listener));
        form.addDeleteListener(listener);
        deleteListeners.add(listener);
    }
}
