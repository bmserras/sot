package org.bmserras.sot.views.widget;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import jakarta.annotation.security.PermitAll;
import jakarta.xml.bind.JAXBException;
import mdeos.isos.isystem0lib.ISystem0ZK;
import mdeos.isos.model.Service;
import org.apache.zookeeper.KeeperException;
import org.bmserras.sot.data.entity.widget.RadarWidget;
import org.bmserras.sot.data.entity.widget.VideoCameraWidget;
import org.bmserras.sot.data.entity.widget.Widget;
import org.bmserras.sot.data.entity.zabbix.ZabbixConfig;
import org.bmserras.sot.data.service.WidgetService;
import org.bmserras.sot.views.layout.MainLayout;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

@PageTitle("Widgets")
@Route(value = "widget-crud", layout = MainLayout.class)
@PermitAll
public class WidgetListView extends VerticalLayout {

    Grid<Widget> grid = new Grid<>(Widget.class);
    TextField filterText = new TextField();
    WidgetForm form;
    WidgetService service;

    HorizontalLayout content;

    RadarWidgetForm radarWidgetForm;
    VideoCameraWidgetForm videoCameraWidgetForm;

    Button importButton = new Button("Import");

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

    private void configureForms(WidgetForm... widgetForms) {

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

        importButton.addClickListener(click -> {

            Dialog dialog = new Dialog();

            VerticalLayout verticalLayout = new VerticalLayout();

            RadioButtonGroup<String> radioGroup = new RadioButtonGroup<>();
            radioGroup.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
            radioGroup.setLabel("Import from...");
            radioGroup.setItems("ISoS - ISystem0", "Database");

            Button button = new Button("Import");
            button.addClickListener(clickEvent -> {
                try {
                    ISystem0ZK iSystem0ZK = new ISystem0ZK();
                    Service service1 = iSystem0ZK.getService("/TFM/SIGET/Radar A1 12 C/Cabin");
                    Service service2 = iSystem0ZK.getService("/TFM/SIGET/Radar IC19 6 D/Cabin");
                    Service service3 = iSystem0ZK.getService("/TFM/SIGET/Radar IC19 7 D/Cabin");

                    HashMap<String, String> props1 = new HashMap<>();
                    HashMap<String, String> props2 = new HashMap<>();
                    HashMap<String, String> props3 = new HashMap<>();

                    service1.getPropList().getPropList().forEach(prop -> props1.put(prop.getName(), prop.getValue()));
                    service2.getPropList().getPropList().forEach(prop -> props2.put(prop.getName(), prop.getValue()));
                    service3.getPropList().getPropList().forEach(prop -> props3.put(prop.getName(), prop.getValue()));


                    RadarWidget radarWidget1 = new RadarWidget(
                            service1.getName(),
                            props1.get("ip"),
                            Integer.parseInt(props1.get("port")),
                            Double.parseDouble(props1.get("lat")),
                            Double.parseDouble(props1.get("long"))
                    );
                    radarWidget1.setZabbixConfig(new ZabbixConfig(
                            Integer.parseInt(props1.get("zabbix-host-id")),
                            Integer.parseInt(props1.get("zabbix-item-id-temperature")),
                            Integer.parseInt(props1.get("zabbix-item-id-voltage")),
                            Integer.parseInt(props1.get("zabbix-item-id-battery"))
                    ));
                    service.saveWidget(radarWidget1);

                    RadarWidget radarWidget2 = new RadarWidget(
                            service2.getName(),
                            props2.get("ip"),
                            Integer.parseInt(props2.get("port")),
                            Double.parseDouble(props2.get("lat")),
                            Double.parseDouble(props2.get("long"))
                    );
                    radarWidget2.setZabbixConfig(new ZabbixConfig(
                            Integer.parseInt(props2.get("zabbix-host-id")),
                            Integer.parseInt(props2.get("zabbix-item-id-temperature")),
                            Integer.parseInt(props2.get("zabbix-item-id-voltage")),
                            Integer.parseInt(props2.get("zabbix-item-id-battery"))
                    ));
                    service.saveWidget(radarWidget2);

                    RadarWidget radarWidget3 = new RadarWidget(
                            service3.getName(), props3.get("ip"),
                            Integer.parseInt(props3.get("port")),
                            Double.parseDouble(props3.get("lat")),
                            Double.parseDouble(props3.get("long"))
                    );
                    radarWidget3.setZabbixConfig(new ZabbixConfig(
                            Integer.parseInt(props3.get("zabbix-host-id")),
                            Integer.parseInt(props3.get("zabbix-item-id-temperature")),
                            Integer.parseInt(props3.get("zabbix-item-id-voltage")),
                            Integer.parseInt(props3.get("zabbix-item-id-battery"))
                    ));
                    service.saveWidget(radarWidget3);
                    updateList();
                    closeEditor();
                    dialog.close();

                } catch (UnsupportedEncodingException | KeeperException | InterruptedException | JAXBException e) {
                    throw new RuntimeException(e);
                }
            });
            verticalLayout.add(radioGroup, button);
            dialog.add(verticalLayout);

            dialog.setWidth("30%");

            dialog.open();
        });

        HorizontalLayout toolbar = new HorizontalLayout(filterText, optionsMenu, importButton);
        toolbar.setAlignItems(Alignment.BASELINE);
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
