package org.bmserras.sot.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
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
import org.bmserras.sot.data.entity.RadarWidget;
import org.bmserras.sot.data.entity.VideoCameraWidget;
import org.bmserras.sot.data.entity.Widget;
import org.bmserras.sot.data.service.WidgetService;

@PageTitle("List View")
@Route(value = "list", layout = MainLayout.class)
public class ListView extends VerticalLayout {

    Grid<Widget> grid = new Grid<>(Widget.class);
    TextField filterText = new TextField();
    WidgetForm form;
    WidgetService service;

    HorizontalLayout content;

    RadarWidgetForm radarWidgetForm;
    VideoCameraWidgetForm videoCameraWidgetForm;

    public ListView(WidgetService service) {
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
        grid.setColumns("identifier", "name", "type");
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

        addWidgetSubMenu.addItem("Speed Radar", click -> editWidget(new RadarWidget()));
        addWidgetSubMenu.addItem("Video Camera", click -> editWidget(new VideoCameraWidget()));

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
