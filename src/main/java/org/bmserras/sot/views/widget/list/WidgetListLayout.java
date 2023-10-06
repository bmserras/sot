package org.bmserras.sot.views.widget.list;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.bmserras.sot.data.domain.Widget;
import org.bmserras.sot.events.widget.WidgetDeleteEvent;
import org.bmserras.sot.events.widget.WidgetOpenEvent;
import org.bmserras.sot.events.widget.WidgetSaveEvent;
import org.bmserras.sot.views.widget.components.WidgetForm;
import org.bmserras.sot.views.widget.components.WidgetToolbar;
import org.bmserras.sot.views.widget.list.WidgetContextMenu;

import java.util.List;
import java.util.Optional;

public class WidgetListLayout extends VerticalLayout {

    private final Grid<Widget> grid = new Grid<>(Widget.class);

    private final WidgetForm form = new WidgetForm();
    private final WidgetContextMenu contextMenu = new WidgetContextMenu(grid);
    private final WidgetToolbar toolbar = new WidgetToolbar();

    public WidgetListLayout() {
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
        grid.asSingleSelect().addValueChangeListener(event -> editWidget(event.getValue()));
    }

    private void configureForm() {
        form.setWidth("25em");
        form.addSaveListener(event -> closeEditor());
        form.addCloseListener(event -> closeEditor());
    }

    private void configureContextMenu() {
        contextMenu.addAddListener(event -> addWidget());
        contextMenu.addEditListener(event -> {
            Optional<Widget> widget = event.getWidget();
            widget.ifPresentOrElse(this::editWidget, () -> editWidget(null));
        });
    }

    private void configureToolbar() {
        toolbar.addAddListener(click -> addWidget());
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

    public void setItems(List<Widget> widgets) {
        grid.setItems(widgets);
    }

    public void editWidget(Widget widget) {
        if (widget == null) closeEditor();
        else {
            /*form.setWidget(widget);*/form.open();
            toolbar.setWidget(widget);
        }
    }

    private void closeEditor() {
        grid.asSingleSelect().clear();
        //form.setWidget(null);
        toolbar.setWidget(null);
    }

    private void addWidget() {
        grid.asSingleSelect().clear();
        editWidget(new Widget());
    }

    public void addOpenListener(ComponentEventListener<WidgetOpenEvent> listener) {
        toolbar.addOpenListener(listener);
        contextMenu.addOpenListener(listener);
    }

    public void addSaveListener(ComponentEventListener<WidgetSaveEvent> listener) {
        form.addSaveListener(listener);
    }

    public void addDeleteListener(ComponentEventListener<WidgetDeleteEvent> listener) {
        toolbar.addDeleteListener(listener);
        contextMenu.addDeleteListener(listener);
    }
}
