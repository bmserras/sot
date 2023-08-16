package org.bmserras.sot.views.synoptic.list;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.bmserras.sot.data.domain.Synoptic;
import org.bmserras.sot.events.synoptic.SynopticDeleteEvent;
import org.bmserras.sot.events.synoptic.SynopticOpenEvent;
import org.bmserras.sot.events.synoptic.SynopticSaveEvent;
import org.bmserras.sot.views.synoptic.components.SynopticForm;
import org.bmserras.sot.views.synoptic.components.SynopticToolbar;

import java.util.List;
import java.util.Optional;

public class SynopticListLayout extends VerticalLayout {

    private final Grid<Synoptic> grid = new Grid<>(Synoptic.class);

    private final SynopticForm form = new SynopticForm();
    private final SynopticContextMenu contextMenu = new SynopticContextMenu(grid);
    private final SynopticToolbar toolbar = new SynopticToolbar();

    public SynopticListLayout() {
        setSizeFull();

        configureGrid();
        configureForm();
        configureContextMenu();
        configureToolbar();

        add(getToolbar(), getContent());
        closeEditor();
    }

    private void configureGrid() {
        grid.setSizeFull();
        grid.setColumns("id", "name");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        grid.asSingleSelect().addValueChangeListener(event -> editSynoptic(event.getValue()));
    }

    private void configureForm() {
        form.setWidth("25em");
        form.addSaveListener(event -> closeEditor());
        form.addDeleteListener(event -> closeEditor());
        form.addCloseListener(event -> closeEditor());
    }

    private void configureContextMenu() {
        contextMenu.addAddListener(event -> addSynoptic());
        contextMenu.addEditListener(event -> {
            Optional<Synoptic> synoptic = event.getSynoptic();
            synoptic.ifPresentOrElse(this::editSynoptic, () -> editSynoptic(null));
        });
    }

    private void configureToolbar() {
        toolbar.addAddListener(click -> addSynoptic());
    }

    private Component getToolbar() {
        return new HorizontalLayout(toolbar);
    }

    private HorizontalLayout getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, form);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, form);
        content.setSizeFull();
        return content;
    }

    public void setItems(List<Synoptic> synoptics) {
        grid.setItems(synoptics);
    }

    public void editSynoptic(Synoptic synoptic) {
        if (synoptic == null) closeEditor();
        else {
            form.setSynoptic(synoptic);
            toolbar.setSynoptic(synoptic);
        }
    }

    private void closeEditor() {
        grid.asSingleSelect().clear();
        form.setSynoptic(null);
        toolbar.setSynoptic(null);
    }

    private void addSynoptic() {
        grid.asSingleSelect().clear();
        editSynoptic(new Synoptic());
    }

    public void addOpenListener(ComponentEventListener<SynopticOpenEvent> listener) {
        toolbar.addOpenListener(listener);
        contextMenu.addOpenListener(listener);
    }

    public void addSaveListener(ComponentEventListener<SynopticSaveEvent> listener) {
        form.addSaveListener(listener);
    }

    public void addDeleteListener(ComponentEventListener<SynopticDeleteEvent> listener) {
        toolbar.addDeleteListener(listener);
        contextMenu.addDeleteListener(listener);
        form.addDeleteListener(listener);
    }
}
