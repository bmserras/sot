package org.bmserras.sot.views.synoptic.components;

import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import org.bmserras.sot.data.domain.Grid;
import org.bmserras.sot.data.domain.Synoptic;
import org.bmserras.sot.events.synoptic.SynopticCloseEvent;
import org.bmserras.sot.events.synoptic.SynopticDeleteEvent;
import org.bmserras.sot.events.synoptic.SynopticSaveEvent;
import org.bmserras.sot.views.components.CustomDialog;
import org.vaadin.lineawesome.LineAwesomeIcon;

import java.util.Optional;

public class SynopticForm extends FormLayout {

    private final NumberField identifier = new NumberField("Identifier");
    private final TextField name = new TextField("Name");
    private final ComboBox<Grid> grid = new ComboBox<>("Grid", Grid.values());

    private final Button save = new Button("Save");
    private final Button delete = new Button("Delete");
    private final Button close = new Button("Cancel");

    private final Binder<Synoptic> binder = new Binder<>(Synoptic.class);

    public SynopticForm() {
        binder.bind(identifier, synoptic -> (double) synoptic.getId(), null);
        binder.bind(name, Synoptic::getName, Synoptic::setName);
        binder.bind(grid, Synoptic::getGrid, Synoptic::setGrid);

        Button button = new Button("Import synoptic", LineAwesomeIcon.FILE_IMPORT_SOLID.create());
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        button.addClickListener(click -> {
            fireEvent(new SynopticSaveEvent(this, Optional.of(new Synoptic(123L, "SINCRO IC19"))));
        });

        add(identifier, name, grid, new Span("\nOR\n"), button);
    }

    private void validateAndSave() {
        if (binder.isValid()) {
            fireEvent(new SynopticSaveEvent(this, Optional.of(binder.getBean())));
        }
    }

    public Synoptic getSynoptic() {
        return binder.getBean();
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
