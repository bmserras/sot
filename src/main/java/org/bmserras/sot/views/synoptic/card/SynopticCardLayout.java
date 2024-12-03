package org.bmserras.sot.views.synoptic.card;

import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.bmserras.sot.data.domain.Synoptic;
import org.bmserras.sot.events.synoptic.SynopticDeleteEvent;
import org.bmserras.sot.events.synoptic.SynopticExportEvent;
import org.bmserras.sot.events.synoptic.SynopticOpenEvent;
import org.bmserras.sot.events.synoptic.SynopticSaveEvent;
import org.bmserras.sot.views.project.components.ProjectDialog;
import org.bmserras.sot.views.synoptic.components.SynopticDialog;
import org.bmserras.sot.views.synoptic.components.SynopticForm;
import org.vaadin.lineawesome.LineAwesomeIcon;

import java.util.ArrayList;
import java.util.List;

public class SynopticCardLayout extends FlexLayout {

    private final List<ComponentEventListener<SynopticOpenEvent>> openListeners;
    private final List<ComponentEventListener<SynopticDeleteEvent>> deleteListeners;
    private final List<ComponentEventListener<SynopticExportEvent>> exportListeners;

    private final List<SynopticExistingCard> existingCards;
    private final SynopticNewCard newCard;

    private SynopticDialog dialog;
    private SynopticForm form;

    public SynopticCardLayout() {
        setSizeFull();

        this.setFlexDirection(FlexLayout.FlexDirection.ROW);
        this.setFlexWrap(FlexLayout.FlexWrap.WRAP);
        this.setJustifyContentMode(JustifyContentMode.CENTER);

        configureDialogForm();

        newCard = new SynopticNewCard(
                LineAwesomeIcon.PLUS_CIRCLE_SOLID.create(),
                "New Synoptic",
                "Create new synoptic"
        );
        newCard.addMainListener(click -> {
            form.setSynoptic(new Synoptic());
            dialog.setTitle("Create synoptic");
            dialog.setDeleteButtonVisible(false);
            dialog.open();
        });

        existingCards = new ArrayList<>();

        openListeners = new ArrayList<>();
        deleteListeners = new ArrayList<>();
        exportListeners = new ArrayList<>();

        add(newCard);
    }

    private void configureDialogForm() {
        form = new SynopticForm();
        form.setWidth("25em");
        dialog = new SynopticDialog(form, 50, 50);

        dialog.addSaveListener(click -> dialog.close());
        dialog.addDeleteListener(click -> dialog.close());
        dialog.addExportListener(click -> dialog.close());
    }

    public void setItems(List<Synoptic> synoptics) {
        existingCards.clear();
        removeAll();
        add(newCard);
        for (Synoptic synoptic : synoptics) {
            SynopticExistingCard existingCard = new SynopticExistingCard(LineAwesomeIcon.CHART_PIE_SOLID.create(),
                    synoptic.getName(), "Open synoptic", synoptic);
            existingCards.add(existingCard);
            add(existingCard);
            openListeners.forEach(existingCard::addMainListener);
            openListeners.forEach(existingCard::addOpenListener);
            deleteListeners.forEach(existingCard::addDeleteListener);
            exportListeners.forEach(existingCard::addExportListener);

            existingCard.addEditListener(event -> {
                form.setSynoptic(synoptic);
                dialog.setTitle("Edit synoptic");
                dialog.setDeleteButtonVisible(true);
                dialog.open();
            });
        }
    }

    public void addOpenListener(ComponentEventListener<SynopticOpenEvent> listener) {
        existingCards.forEach(card -> card.addOpenListener(listener));
        existingCards.forEach(card -> card.addMainListener(listener));
        openListeners.add(listener);
    }

    public void addSaveListener(ComponentEventListener<SynopticSaveEvent> listener) {
        dialog.addSaveListener(listener);
    }

    public void addDeleteListener(ComponentEventListener<SynopticDeleteEvent> listener) {
        existingCards.forEach(card -> card.addDeleteListener(listener));
        form.addDeleteListener(listener);
        deleteListeners.add(listener);
    }

    public void addExportListener(ComponentEventListener<SynopticExportEvent> listener) {
        existingCards.forEach(card -> card.addExportListener(listener));
        exportListeners.add(listener);
    }
}
