package org.bmserras.sot.views.widget;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import jakarta.annotation.security.PermitAll;
import org.bmserras.sot.data.entity.widget.RadarWidget;
import org.bmserras.sot.data.entity.widget.VideoCameraWidget;
import org.bmserras.sot.data.entity.widget.Widget;
import org.bmserras.sot.data.service.WidgetService;
import org.bmserras.sot.views.layout.MainLayout;

@PageTitle("Widgets")
@Route(value = "widget-crud", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@PermitAll
public class WidgetListView extends VerticalLayout {

    Grid<Widget> grid = new Grid<>(Widget.class);
    TextField filterText = new TextField();
    WidgetForm form;
    WidgetService service;

    HorizontalLayout content;

    RadarWidgetForm radarWidgetForm;
    VideoCameraWidgetForm videoCameraWidgetForm;

    public WidgetListView(WidgetService service) {
        this.service = service;
        addClassName("list-view");
        setSizeFull();
        configureGrid();
        configureForms();

        content = getContent();

        add(getToolbar(), content);
        updateList();
        closeEditor();
    }

    private void configureGrid() {
        grid.addClassNames("widget-grid");
        grid.setSizeFull();
        grid.setColumns("identifier", "name");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(event -> {
            editWidget(event.getValue());
        });
    }

    private void configureForms(WidgetForm ...widgetForms) {

        /*for (WidgetForm widgetForm : widgetForms) {
            widgetForm = new RadarWidgetForm();
            widgetForm.setWidth("25em");
            widgetForm.addSaveListener(this::saveWidget);
            widgetForm.addDeleteListener(this::deleteWidget);
            widgetForm.addCloseListener(e -> closeEditor());
        }*/

        radarWidgetForm = new RadarWidgetForm();
        radarWidgetForm.setWidth("25em");
        radarWidgetForm.addSaveListener(this::saveWidget);
        radarWidgetForm.addDeleteListener(this::deleteWidget);
        radarWidgetForm.addCloseListener(e -> closeEditor());

        videoCameraWidgetForm = new VideoCameraWidgetForm();
        videoCameraWidgetForm.setWidth("25em");
        videoCameraWidgetForm.addSaveListener(this::saveWidget);
        videoCameraWidgetForm.addDeleteListener(this::deleteWidget);
        videoCameraWidgetForm.addCloseListener(e -> closeEditor());

        form = radarWidgetForm;
    }

    private void updateList() {
        grid.setItems(service.findAllWidgets(filterText.getValue()));
    }

    public void editWidget(Widget widget) {
        closeEditor();
        if (widget == null) {
            return;
        }
        if (widget instanceof RadarWidget) {
            form = radarWidgetForm;
        } else if (widget instanceof VideoCameraWidget) {
            form = videoCameraWidgetForm;
        }
        form.setWidget(widget);
        form.setVisible(true);
        content.remove(form);
        content.add(form);
        addClassName("editing");
    }

    private Component getToolbar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        MenuBar optionsMenu = new MenuBar();
        MenuItem addWidgetMenuItem = optionsMenu.addItem("Add widget");
        SubMenu addWidgetSubMenu = addWidgetMenuItem.getSubMenu();

        addWidgetSubMenu.addItem("Speed Radar", click -> {
            grid.asSingleSelect().clear();
            editWidget(new RadarWidget());
        });
        addWidgetSubMenu.addItem("Video Camera", click -> {
            grid.asSingleSelect().clear();
            editWidget(new VideoCameraWidget());
        });

        var toolbar = new HorizontalLayout(filterText, optionsMenu);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private HorizontalLayout getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, form);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, form);
        content.addClassNames("content");
        content.setSizeFull();
        return content;
    }

    private void saveWidget(WidgetForm.SaveEvent event) {
        service.saveWidget(event.getWidget());
        updateList();
        closeEditor();
    }

    private void deleteWidget(WidgetForm.DeleteEvent event) {
        service.deleteWidget(event.getWidget());
        updateList();
        closeEditor();
    }

    private void closeEditor() {
        form.setWidget(null);
        form.setVisible(false);
        removeClassName("editing");
    }

}
