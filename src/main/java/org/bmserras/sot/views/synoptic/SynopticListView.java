package org.bmserras.sot.views.synoptic;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.contextmenu.GridContextMenu;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import org.bmserras.sot.data.entity.synoptic.Synoptic;
import org.bmserras.sot.data.service.SynopticService;
import org.bmserras.sot.events.SynopticEvent;
import org.bmserras.sot.views.layout.MainLayout;
import org.vaadin.lineawesome.LineAwesomeIcon;

import java.util.Optional;

@PageTitle("Synoptics")
@Route(value = "synoptic-crud", layout = MainLayout.class)
@PermitAll
public class SynopticListView extends VerticalLayout {

    LineAwesomeIcon addIcon = LineAwesomeIcon.PLUS_CIRCLE_SOLID;
    LineAwesomeIcon openIcon = LineAwesomeIcon.EXTERNAL_LINK_ALT_SOLID;
    LineAwesomeIcon mapIcon = LineAwesomeIcon.MAP_MARKED_ALT_SOLID;
    LineAwesomeIcon removeIcon = LineAwesomeIcon.TRASH_SOLID;

    Grid<Synoptic> grid = new Grid<>(Synoptic.class);
    SynopticContextMenu contextMenu = new SynopticContextMenu(grid);

    TextField filterText = new TextField();
    Button addSynopticButton = new Button(addIcon.create());

    Optional<Synoptic> selectedSynoptic = Optional.empty();
    SynopticForm form;
    SynopticToolbar toolbar;
    SynopticService synopticService;

    public SynopticListView(SynopticService synopticService) {
        this.synopticService = synopticService;

        setSizeFull();
        configureGrid();
        configureForm();

        add(getToolbar(), getContent());
        updateList();
        closeEditor();
    }

    private void configureGrid() {
        grid.setSizeFull();
        grid.setColumns("identifier", "name");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        grid.asSingleSelect().addValueChangeListener(event -> {
            selectedSynoptic = Optional.ofNullable(event.getValue());
            editSynoptic(selectedSynoptic);
        });
    }

    private void configureForm() {
        form = new SynopticForm();
        form.setWidth("25em");
        form.addSaveListener(event -> saveSynoptic(event.getSynoptic()));
        form.addDeleteListener(event -> deleteSynoptic(event.getSynoptic()));
        form.addCloseListener(event -> closeEditor());
    }

    private Component getToolbar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        addSynopticButton.addClickListener(click -> addSynoptic());

        toolbar = new SynopticToolbar();
        toolbar.addOpenListener(event -> openSynoptic(event.getSynoptic()));
        toolbar.addMapListener(event -> openMap(event.getSynoptic()));
        toolbar.addRemoveListener(event -> removeSynoptic(event.getSynoptic()));

        return new HorizontalLayout(filterText, addSynopticButton, toolbar/*openButton, mapButton, removeButton*/);
    }

    private HorizontalLayout getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, form);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, form);
        content.setSizeFull();
        return content;
    }

    private void updateList() {
        grid.setItems(synopticService.findAllSynoptics(filterText.getValue()));
    }

    public void editSynoptic(Optional<Synoptic> synoptic) {
        if (synoptic.isEmpty()) {
            closeEditor();
        }
        else {
            form.setSynoptic(synoptic.get());
            form.setVisible(true);

            toolbar.setSynoptic(synoptic.get());
            toolbar.setVisible(!synoptic.get().equals(new Synoptic()));
        }
    }

    private void addSynoptic() {
        grid.asSingleSelect().clear();
        editSynoptic(Optional.of(new Synoptic()));
    }

    private void openSynoptic(Optional<Synoptic> synoptic) {
        synoptic.ifPresent(syn -> getUI().ifPresent(ui -> ui.navigate("synoptic/" + syn.getName())));
    }

    private void openMap(Optional<Synoptic> synoptic) {
        synoptic.ifPresent(syn -> getUI().ifPresent(ui -> ui.navigate("map/" + syn.getName())));
    }

    private void removeSynoptic(Optional<Synoptic> synoptic) {
        synoptic.ifPresent(syn -> {
            synopticService.deleteSynoptic(syn);
            updateList();
            closeEditor();
        });
    }

    private void closeEditor() {
        form.setSynoptic(null);
        form.setVisible(false);

        toolbar.setSynoptic(null);
        toolbar.setVisible(false);
    }

    private void saveSynoptic(Optional<Synoptic> synoptic) {
        synoptic.ifPresent(syn -> {
            synopticService.saveSynoptic(syn);
            updateList();
            closeEditor();
        });
    }

    private void deleteSynoptic(Optional<Synoptic> synoptic) {
        synoptic.ifPresent(syn -> {
            synopticService.deleteSynoptic(syn);
            updateList();
            closeEditor();
        });
    }

    private class SynopticContextMenu extends GridContextMenu<Synoptic> {

        public SynopticContextMenu(Grid<Synoptic> target) {
            super(target);

            addItem(new HorizontalLayout(openIcon.create(), new Span("Open")), click -> openSynoptic(click.getItem()));
            addItem(new HorizontalLayout(mapIcon.create(), new Span("Map")), click -> openMap(click.getItem()));
            addItem(new HorizontalLayout(removeIcon.create(), new Span("Remove")), click -> removeSynoptic(click.getItem()));
        }
    }

}
