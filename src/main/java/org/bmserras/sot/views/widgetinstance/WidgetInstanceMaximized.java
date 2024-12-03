package org.bmserras.sot.views.widgetinstance;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.component.tabs.TabSheetVariant;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import org.bmserras.sot.data.domain.Widget;
import org.bmserras.sot.data.domain.WidgetInstance;
import org.bmserras.sot.data.domain.readers.GaugeData;
import org.bmserras.sot.data.domain.readers.ROTextFieldData;
import org.bmserras.sot.data.domain.readers.Reader;
import org.bmserras.sot.data.domain.readers.SolidGaugeData;
import org.bmserras.sot.data.domain.writers.*;
import org.bmserras.sot.views.widget.readers.gauge.GaugeChart;
import org.bmserras.sot.views.widget.readers.gauge.GaugeContainer;
import org.bmserras.sot.views.widget.readers.rotextfield.ROTextFieldComponent;
import org.bmserras.sot.views.widget.readers.rotextfield.ROTextFieldContainer;
import org.bmserras.sot.views.widget.readers.solidgauge.SolidGaugeChart;
import org.bmserras.sot.views.widget.readers.solidgauge.SolidGaugeContainer;
import org.bmserras.sot.views.widget.writers.button.ButtonComponent;
import org.bmserras.sot.views.widget.writers.button.ButtonContainer;
import org.bmserras.sot.views.widget.writers.checkbox.CheckboxComponent;
import org.bmserras.sot.views.widget.writers.textfield.TextFieldComponent;
import org.bmserras.sot.views.widget.writers.toggle.ToggleComponent;
import org.bmserras.sot.views.widget.writers.toggle.ToggleContainer;
import org.vaadin.lineawesome.LineAwesomeIcon;

public class WidgetInstanceMaximized extends VerticalLayout {

    private final TabSheet tabSheet = new TabSheet();

    public WidgetInstanceMaximized(WidgetInstance widgetInstance) {
        setSizeFull();

        tabSheet.setSizeFull();
        tabSheet.addThemeVariants(TabSheetVariant.LUMO_TABS_CENTERED);
        tabSheet.addThemeVariants(TabSheetVariant.LUMO_TABS_EQUAL_WIDTH_TABS);

        HorizontalLayout readers = new HorizontalLayout();
        for (Reader reader : widgetInstance.getWidget().getReaders()) {
            if (reader instanceof GaugeData) {
                GaugeChart gaugeChart = new GaugeChart((GaugeData) reader);
                gaugeChart.setValue(70);
                readers.add(new GaugeContainer(gaugeChart));
            }
            else if (reader instanceof SolidGaugeData) {
                SolidGaugeChart solidGaugeChart = new SolidGaugeChart((SolidGaugeData) reader);
                readers.add(new SolidGaugeContainer(solidGaugeChart));
            }
            else if (reader instanceof ROTextFieldData) {
                ROTextFieldComponent roTextFieldComponent = new ROTextFieldComponent((ROTextFieldData) reader);
                readers.add(new ROTextFieldContainer(roTextFieldComponent));
            }
        }

        HorizontalLayout writers = new HorizontalLayout();
        for (Writer writer : widgetInstance.getWidget().getWriters()) {
            if (writer instanceof ButtonData) {
                ButtonComponent buttonComponent = new ButtonComponent((ButtonData) writer);
                writers.add(new ButtonContainer(buttonComponent));
            }
            else if (writer instanceof ToggleData) {
                ToggleComponent toggleComponent = new ToggleComponent((ToggleData) writer);
                writers.add(new ToggleContainer(toggleComponent));
            }
            else if (writer instanceof CheckboxData) {
                CheckboxComponent checkboxComponent = new CheckboxComponent((CheckboxData) writer);
                writers.add(checkboxComponent);
            }
            else if (writer instanceof TextFieldData) {
                TextFieldComponent textFieldComponent = new TextFieldComponent((TextFieldData) writer);
                writers.add(textFieldComponent);
            }
        }

        HorizontalLayout widgets = new HorizontalLayout();
        widgets.setSizeFull();
        for (Widget widget : widgetInstance.getWidget().getWidgets()) {
            WidgetInstanceCard widgetInstanceCard = new WidgetInstanceCard(new WidgetInstance(widget.getName() + " (" + widgetInstance.getName() + ")", widget));
            widgetInstanceCard.setHeight("300px");
            widgetInstanceCard.setWidth("300px");
            widgets.add(widgetInstanceCard);
        }

        tabSheet.add("Readers", readers);
        tabSheet.add("Writers", writers);
        tabSheet.add("Widgets", widgets);

        /*VerticalLayout connections = new VerticalLayout();
        connections.setWidthFull();
        VerticalLayout tags = new VerticalLayout();

        // TAGS
        VerticalLayout tagFields = new VerticalLayout();
        HorizontalLayout tag1 = new HorizontalLayout(new TextField("Name"), new TextField("Value"));
        tagFields.add(tag1);
        Button addTag = new Button("Add tag", LineAwesomeIcon.PLUS_SOLID.create());
        addTag.addClickListener(e -> {
            HorizontalLayout tag = new HorizontalLayout(new TextField("Name"), new TextField("Value"));
            tagFields.add(tag);
        });
        tags.add(tagFields, addTag);

        // CONNECTIONS
        ComboBox<String> component = new ComboBox<>("Component");
        component.setItems("Temperature", "Battery", "Open door", "Fan");
        VerticalLayout componentFields = new VerticalLayout();
        componentFields.setMargin(false);
        componentFields.setPadding(false);
        componentFields.setWidthFull();
        component.addValueChangeListener(e -> {
            if (e.getValue().equals("Temperature")) {
                ComboBox<String> protocol = new ComboBox<>("Protocol", "HTTP (REST)", "SNMP");
                connections.remove(componentFields);
                componentFields.removeAll();
                componentFields.add(protocol);
                protocol.addValueChangeListener(ev -> {
                    if (ev.getValue().equals("HTTP (REST)")) {
                        TextField url = new TextField("URL");
                        url.setWidth("50%");
                        TextField port = new TextField("Port");
                        port.setWidth("50%");
                        TextField path = new TextField("Path");
                        path.setWidth("50%");
                        TextField username = new TextField("Username");
                        username.setWidth("50%");
                        PasswordField password = new PasswordField("Password");
                        password.setWidth("50%");
                        componentFields.removeAll();
                        componentFields.add(protocol, url, port, path, username, password);
                    }
                    else if (ev.getValue().equals("SNMP")) {
                        TextField ip = new TextField("IP");
                        ip.setWidth("50%");
                        TextField portS = new TextField("Port");
                        portS.setWidth("50%");
                        TextField oid = new TextField("OID");
                        oid.setWidth("50%");
                        TextField usernameS = new TextField("Username");
                        usernameS.setWidth("50%");
                        PasswordField passwordS = new PasswordField("Password");
                        passwordS.setWidth("50%");
                        componentFields.removeAll();
                        componentFields.add(protocol, ip, portS, oid, usernameS, passwordS);
                    }
                });
                connections.add(componentFields);
            }
            else if (e.getValue().equals("Battery")) {
                ComboBox<String> protocol = new ComboBox<>("Protocol", "HTTP (REST)", "SNMP");
                connections.remove(componentFields);
                componentFields.removeAll();
                componentFields.add(protocol);
                protocol.addValueChangeListener(ev -> {
                    if (ev.getValue().equals("HTTP (REST)")) {
                        TextField url = new TextField("URL");
                        url.setWidth("50%");
                        TextField port = new TextField("Port");
                        port.setWidth("50%");
                        TextField path = new TextField("Path");
                        path.setWidth("50%");
                        TextField username = new TextField("Username");
                        username.setWidth("50%");
                        PasswordField password = new PasswordField("Password");
                        password.setWidth("50%");
                        componentFields.removeAll();
                        componentFields.add(protocol, url, port, path, username, password);
                    }
                    else if (ev.getValue().equals("SNMP")) {
                        TextField ip = new TextField("IP");
                        ip.setWidth("50%");
                        TextField portS = new TextField("Port");
                        portS.setWidth("50%");
                        TextField oid = new TextField("OID");
                        oid.setWidth("50%");
                        TextField usernameS = new TextField("Username");
                        usernameS.setWidth("50%");
                        PasswordField passwordS = new PasswordField("Password");
                        passwordS.setWidth("50%");
                        componentFields.removeAll();
                        componentFields.add(protocol, ip, portS, oid, usernameS, passwordS);
                    }
                });
                connections.add(componentFields);
            }
            else if (e.getValue().equals("Open door")) {
                ComboBox<String> protocol = new ComboBox<>("Protocol", "HTTP (REST)", "SNMP");
                connections.remove(componentFields);
                componentFields.removeAll();
                componentFields.add(protocol);
                protocol.addValueChangeListener(ev -> {
                    if (ev.getValue().equals("HTTP (REST)")) {
                        TextField url = new TextField("URL");
                        url.setWidth("50%");
                        TextField port = new TextField("Port");
                        port.setWidth("50%");
                        TextField path = new TextField("Path");
                        path.setWidth("50%");
                        TextField username = new TextField("Username");
                        username.setWidth("50%");
                        PasswordField password = new PasswordField("Password");
                        password.setWidth("50%");
                        componentFields.removeAll();
                        componentFields.add(protocol, url, port, path, username, password);
                    }
                    else if (ev.getValue().equals("SNMP")) {
                        TextField ip = new TextField("IP");
                        ip.setWidth("50%");
                        TextField portS = new TextField("Port");
                        portS.setWidth("50%");
                        TextField oid = new TextField("OID");
                        oid.setWidth("50%");
                        TextField usernameS = new TextField("Username");
                        usernameS.setWidth("50%");
                        PasswordField passwordS = new PasswordField("Password");
                        passwordS.setWidth("50%");
                        componentFields.removeAll();
                        componentFields.add(protocol, ip, portS, oid, usernameS, passwordS);
                    }
                });
                connections.add(componentFields);
            }
            else if (e.getValue().equals("Fan")) {
                ComboBox<String> protocol = new ComboBox<>("Protocol", "HTTP (REST)", "SNMP");
                connections.remove(componentFields);
                componentFields.removeAll();
                componentFields.add(protocol);
                protocol.addValueChangeListener(ev -> {
                    if (ev.getValue().equals("HTTP (REST)")) {
                        TextField url = new TextField("URL");
                        url.setWidth("50%");
                        TextField port = new TextField("Port");
                        port.setWidth("50%");
                        TextField path = new TextField("Path");
                        path.setWidth("50%");
                        TextField username = new TextField("Username");
                        username.setWidth("50%");
                        PasswordField password = new PasswordField("Password");
                        password.setWidth("50%");
                        componentFields.removeAll();
                        componentFields.add(protocol, url, port, path, username, password);
                    }
                    else if (ev.getValue().equals("SNMP")) {
                        TextField ip = new TextField("IP");
                        ip.setWidth("50%");
                        TextField portS = new TextField("Port");
                        portS.setWidth("50%");
                        TextField oid = new TextField("OID");
                        oid.setWidth("50%");
                        TextField usernameS = new TextField("Username");
                        usernameS.setWidth("50%");
                        PasswordField passwordS = new PasswordField("Password");
                        passwordS.setWidth("50%");
                        componentFields.removeAll();
                        componentFields.add(protocol, ip, portS, oid, usernameS, passwordS);
                    }
                });
                connections.add(componentFields);
            }
        });
        connections.add(component);

        tabSheet.add("Connections", connections);
        tabSheet.add("Tags", tags);*/

        add(tabSheet);
    }

}
