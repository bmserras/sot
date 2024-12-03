package org.bmserras.sot.views.widget.writers.card;

import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.bmserras.sot.data.domain.writers.Writer;
import org.bmserras.sot.events.widget.writer.WriterDeleteEvent;
import org.bmserras.sot.events.widget.writer.WriterOpenEvent;
import org.bmserras.sot.events.widget.writer.WriterSaveEvent;
import org.bmserras.sot.views.widget.writers.WriterDialog;
import org.vaadin.lineawesome.LineAwesomeIcon;

import java.util.ArrayList;
import java.util.List;

public class WriterCardLayout extends VerticalLayout {

    private final List<ComponentEventListener<WriterOpenEvent>> openListeners;
    private final List<ComponentEventListener<WriterDeleteEvent>> deleteListeners;

    private final List<WriterExistingCard> existingCards;
    private final HorizontalLayout cards;
    private final WriterNewCard newCard;

    private WriterDialog writerDialog;

    private List<Writer> writers = new ArrayList<>();

    public WriterCardLayout() {
        setSizeFull();
        setPadding(false);

        newCard = new WriterNewCard(
                LineAwesomeIcon.PLUS_CIRCLE_SOLID.create(),
                "New writer",
                "Create new writer"
        );
        newCard.addMainListener(click -> {
            /*form.setProject(new Project());*/writerDialog = new WriterDialog();
            writerDialog.addSaveListener(save -> {
                save.getWriter().ifPresent(writers::add);
                setItems(writers);
                writerDialog.close();
                System.out.println("Saving writer: " + save.getWriter().get());
            });
            writerDialog.open();
        });

        existingCards = new ArrayList<>();
        cards = new HorizontalLayout();
        cards.setWidthFull();
        cards.setHeight("40%");

        openListeners = new ArrayList<>();
        openListeners.add(click -> System.out.println(click.getWriter().get()));
        deleteListeners = new ArrayList<>();

        add(cards);

        setItems(writers);
    }

    public List<Writer> getItems() {
        return writers;
    }

    public void setItems(List<Writer> writers) {
        this.writers = writers;
        existingCards.clear();
        cards.removeAll();
        cards.add(newCard);
        for (Writer writer : writers) {
            WriterExistingCard existingCard = new WriterExistingCard(LineAwesomeIcon.PENCIL_ALT_SOLID.create(),
                    writer.getName(), "Open writer", writer);
            existingCards.add(existingCard);
            cards.add(existingCard);
            openListeners.forEach(existingCard::addMainListener);
            openListeners.forEach(existingCard::addOpenListener);
            deleteListeners.forEach(existingCard::addDeleteListener);

            existingCard.addEditListener(event -> {

            });
        }
    }

    public void addOpenListener(ComponentEventListener<WriterOpenEvent> listener) {
        existingCards.forEach(card -> card.addOpenListener(listener));
        existingCards.forEach(card -> card.addMainListener(listener));
        openListeners.add(listener);
    }

    public void addSaveListener(ComponentEventListener<WriterSaveEvent> listener) {
        writerDialog.addSaveListener(listener);
    }

    public void addDeleteListener(ComponentEventListener<WriterDeleteEvent> listener) {
        existingCards.forEach(card -> card.addDeleteListener(listener));
        deleteListeners.add(listener);
    }
}
