package org.bmserras.sot.views.widget.readers.card;

import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.bmserras.sot.data.domain.readers.ValueReader;
import org.bmserras.sot.events.widget.reader.ReaderDeleteEvent;
import org.bmserras.sot.events.widget.reader.ReaderOpenEvent;
import org.bmserras.sot.events.widget.reader.ReaderSaveEvent;
import org.bmserras.sot.views.widget.readers.ReaderDialog;
import org.vaadin.lineawesome.LineAwesomeIcon;

import java.util.ArrayList;
import java.util.List;

public class ReaderCardLayout extends VerticalLayout {

    private final List<ComponentEventListener<ReaderOpenEvent>> openListeners;
    private final List<ComponentEventListener<ReaderDeleteEvent>> deleteListeners;

    private final List<ReaderExistingCard> existingCards;
    private final HorizontalLayout cards;
    private final ReaderNewCard newCard;

    private ReaderDialog readerDialog;

    private final List<ValueReader> readers = new ArrayList<>();

    public ReaderCardLayout() {
        setSizeFull();
        setPadding(false);

        newCard = new ReaderNewCard(
                LineAwesomeIcon.PLUS_CIRCLE_SOLID.create(),
                "New reader",
                "Create new reader"
        );
        newCard.addMainListener(click -> {
            /*form.setProject(new Project());*/readerDialog = new ReaderDialog();
            readerDialog.addSaveListener(save -> {
                save.getReader().ifPresent(readers::add);
                setItems(readers);
                readerDialog.close();
            });
            readerDialog.open();
        });

        existingCards = new ArrayList<>();
        cards = new HorizontalLayout();
        cards.setWidthFull();
        cards.setHeight("40%");

        openListeners = new ArrayList<>();
        deleteListeners = new ArrayList<>();

        add(cards);

        setItems(readers);
    }

    public List<ValueReader> getItems() {
        return readers;
    }

    public void setItems(List<ValueReader> readers) {
        existingCards.clear();
        cards.removeAll();
        cards.add(newCard);
        for (ValueReader reader : readers) {
            ReaderExistingCard existingCard = new ReaderExistingCard(LineAwesomeIcon.GLASSES_SOLID.create(),
                    reader.getName(), "Open reader", reader);
            existingCards.add(existingCard);
            cards.add(existingCard);
            openListeners.forEach(existingCard::addMainListener);
            openListeners.forEach(existingCard::addOpenListener);
            deleteListeners.forEach(existingCard::addDeleteListener);

            existingCard.addEditListener(event -> {

            });
        }
    }

    public void addOpenListener(ComponentEventListener<ReaderOpenEvent> listener) {
        existingCards.forEach(card -> card.addOpenListener(listener));
        existingCards.forEach(card -> card.addMainListener(listener));
        openListeners.add(listener);
    }

    public void addSaveListener(ComponentEventListener<ReaderSaveEvent> listener) {
        readerDialog.addSaveListener(listener);
    }

    public void addDeleteListener(ComponentEventListener<ReaderDeleteEvent> listener) {
        existingCards.forEach(card -> card.addDeleteListener(listener));
        deleteListeners.add(listener);
    }
}
