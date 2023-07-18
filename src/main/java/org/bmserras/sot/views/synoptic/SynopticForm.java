package org.bmserras.sot.views.synoptic;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;
import org.bmserras.sot.data.entity.synoptic.Synoptic;
import org.bmserras.sot.data.entity.widget.Widget;

import java.util.List;

public class SynopticForm extends FormLayout {

    private final NumberField identifier = new NumberField("Identifier");
    private final TextField name = new TextField("Name");
    //private final MultiSelectComboBox<Widget> widgets = new MultiSelectComboBox<>("Widgets");

    private final Button save = new Button("Save");
    private final Button delete = new Button("Delete");
    private final Button close = new Button("Cancel");

    private final Binder<Synoptic> binder = new Binder<>(Synoptic.class);

    public SynopticForm() {
        addClassName("synoptic-form");

        binder.bind(identifier, synoptic -> (double) synoptic.getIdentifier(), null);
        binder.bind(name, Synoptic::getName, Synoptic::setName);

        add(identifier, name, /*widgets, */createButtonsLayout());
    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(event -> validateAndSave()); // <1>
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this, binder.getBean()))); // <2>
        close.addClickListener(event -> fireEvent(new CloseEvent(this))); // <3>

        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid())); // <4>
        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {
        if (binder.isValid()) {
            fireEvent(new SaveEvent(this, binder.getBean())); // <6>
        }
    }

    public void setSynoptic(Synoptic synoptic) {
        binder.setBean(synoptic);
    }

    // Events
    public static abstract class SynopticFormEvent extends ComponentEvent<SynopticForm> {
        private Synoptic synoptic;

        protected SynopticFormEvent(SynopticForm source, Synoptic synoptic) {
            super(source, false);
            this.synoptic = synoptic;
        }

        public Synoptic getSynoptic() {
            return synoptic;
        }
    }

    public static class SaveEvent extends SynopticFormEvent {
        SaveEvent(SynopticForm source, Synoptic synoptic) {
            super(source, synoptic);
        }
    }

    public static class DeleteEvent extends SynopticFormEvent {
        DeleteEvent(SynopticForm source, Synoptic synoptic) {
            super(source, synoptic);
        }

    }

    public static class CloseEvent extends SynopticFormEvent {
        CloseEvent(SynopticForm source) {
            super(source, null);
        }
    }

    public Registration addDeleteListener(ComponentEventListener<DeleteEvent> listener) {
        return addListener(DeleteEvent.class, listener);
    }

    public Registration addSaveListener(ComponentEventListener<SaveEvent> listener) {
        return addListener(SaveEvent.class, listener);
    }

    public Registration addCloseListener(ComponentEventListener<CloseEvent> listener) {
        return addListener(CloseEvent.class, listener);
    }
}
