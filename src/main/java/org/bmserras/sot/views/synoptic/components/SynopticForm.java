package org.bmserras.sot.views.synoptic.components;

import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import org.bmserras.sot.data.domain.Synoptic;
import org.bmserras.sot.events.synoptic.SynopticCloseEvent;
import org.bmserras.sot.events.synoptic.SynopticDeleteEvent;
import org.bmserras.sot.events.synoptic.SynopticSaveEvent;

import java.util.Optional;

public class SynopticForm extends FormLayout {

    private final NumberField identifier = new NumberField("Identifier");
    private final TextField name = new TextField("Name");

    private final Button save = new Button("Save");
    private final Button delete = new Button("Delete");
    private final Button close = new Button("Cancel");

    private final Binder<Synoptic> binder = new Binder<>(Synoptic.class);

    public SynopticForm() {
        binder.bind(identifier, synoptic -> (double) synoptic.getId(), null);
        binder.bind(name, Synoptic::getName, Synoptic::setName);

        add(identifier, name, createButtonsLayout());
    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        delete.addClickShortcut(Key.DELETE);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> {

            ConfirmDialog confirmDialog = new ConfirmDialog();
            confirmDialog.setHeader("Delete synoptic");
            confirmDialog.setText("Are you sure you want to delete this synoptic?");

            confirmDialog.setCancelable(true);
            confirmDialog.setConfirmText("Yes");
            confirmDialog.addConfirmListener(confirm -> fireEvent(new SynopticDeleteEvent(this, Optional.of(binder.getBean()))));

            confirmDialog.open();
        });
        close.addClickListener(event -> fireEvent(new SynopticCloseEvent(this)));

        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));
        HorizontalLayout horizontalLayout = new HorizontalLayout(save, delete, close);
        horizontalLayout.expand(save, delete, close);
        return horizontalLayout;
    }

    private void validateAndSave() {
        if (binder.isValid()) {
            fireEvent(new SynopticSaveEvent(this, Optional.of(binder.getBean())));
        }
    }

    public void setSynoptic(Synoptic synoptic) {
        binder.setBean(synoptic);
        this.setVisible(synoptic != null);
    }

    public void addSaveListener(ComponentEventListener<SynopticSaveEvent> listener) {
        addListener(SynopticSaveEvent.class, listener);
    }

    public void addDeleteListener(ComponentEventListener<SynopticDeleteEvent> listener) {
        addListener(SynopticDeleteEvent.class, listener);
    }

    public void addCloseListener(ComponentEventListener<SynopticCloseEvent> listener) {
        addListener(SynopticCloseEvent.class, listener);
    }
}
