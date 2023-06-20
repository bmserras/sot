package org.bmserras.sot.views.synoptic;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.contextmenu.GridContextMenu;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.bmserras.sot.data.entity.Synoptic;
import org.bmserras.sot.data.service.SynopticService;
import org.bmserras.sot.data.service.WidgetService;
import org.bmserras.sot.views.MainLayout;

import java.util.function.Consumer;

@PageTitle("Synoptic CRUD")
@Route(value = "synoptic-crud", layout = MainLayout.class)
public class SynopticListView extends VerticalLayout {

    Grid<Synoptic> grid = new Grid<>(Synoptic.class);
    SynopticContextMenu contextMenu = new SynopticContextMenu(grid);

    TextField filterText = new TextField();
    SynopticForm form;
    SynopticService synopticService;
    WidgetService widgetService;


    public SynopticListView(SynopticService synopticService, WidgetService widgetService) {
        this.synopticService = synopticService;
        this.widgetService = widgetService;
        addClassName("list-view");
        setSizeFull();
        configureGrid();
        configureForm();

        add(getToolbar(), getContent());
        updateList();
        closeEditor();
    }

    private HorizontalLayout getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, form);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, form);
        content.addClassNames("content");
        content.setSizeFull();
        return content;
    }

    private void configureGrid() {
        grid.addClassNames("synoptic-grid");
        grid.setSizeFull();
        grid.setColumns("identifier", "name");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(event -> editSynoptic(event.getValue()));
    }

    private void configureForm() {
        form = new SynopticForm(widgetService.findAllWidgets());
        form.setWidth("25em");
        form.addSaveListener(this::saveSynoptic);
        form.addDeleteListener(this::deleteSynoptic);
        form.addCloseListener(e -> closeEditor());
    }

    private void updateList() {
        grid.setItems(synopticService.findAllSynoptics(filterText.getValue()));
    }

    public void editSynoptic(Synoptic synoptic) {
        if (synoptic == null) {
            closeEditor();
        } else {
            form.setSynoptic(synoptic);
            form.setVisible(true);
            addClassName("editing");
        }
    }

    private Component getToolbar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addSynopticButton = new Button("Add synoptic");
        addSynopticButton.addClickListener(click -> addSynoptic());

        var toolbar = new HorizontalLayout(filterText, addSynopticButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void addSynoptic() {
        grid.asSingleSelect().clear();
        editSynoptic(new Synoptic());
    }

    private void closeEditor() {
        form.setSynoptic(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void saveSynoptic(SynopticForm.SaveEvent event) {
        synopticService.saveSynoptic(event.getSynoptic());
        updateList();
        closeEditor();
    }

    private void deleteSynoptic(SynopticForm.DeleteEvent event) {
        synopticService.deleteSynoptic(event.getSynoptic());
        updateList();
        closeEditor();
    }

    private static class SynopticContextMenu extends GridContextMenu<Synoptic> {

        public SynopticContextMenu(Grid<Synoptic> target) {
            super(target);

            addItem("Open", click -> getUI().ifPresent(ui ->
                    click.getItem().ifPresent(synoptic -> ui.navigate("synoptic/" + synoptic.getName()))));
            addItem("Edit");
            addItem("Delete");


        }
    }

}
