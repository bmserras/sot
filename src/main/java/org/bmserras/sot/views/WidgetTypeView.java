package org.bmserras.sot.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import org.bmserras.sot.data.entity.widgettype.WidgetType;
import org.bmserras.sot.data.entity.widgettype.WidgetTypeInstance;
import org.bmserras.sot.data.entity.widgettype.WidgetTypeProperty;
import org.bmserras.sot.data.repository.widgettype.WidgetTypeRepository;
import org.bmserras.sot.views.layout.MainLayout;

import java.util.HashMap;
import java.util.List;

@PageTitle("Widget Type View")
@Route(value = "widget-type", layout = MainLayout.class)
@PermitAll
public class WidgetTypeView extends VerticalLayout {

    private final WidgetTypeRepository widgetTypeRepository;

    private final Button readButton = new Button("Read");

    public WidgetTypeView(WidgetTypeRepository widgetTypeRepository) {
        setSizeFull();
        this.widgetTypeRepository = widgetTypeRepository;

        WidgetType fromDB = widgetTypeRepository.findByName("Radar");
        System.out.println(fromDB);

        List<WidgetTypeProperty> properties = fromDB.getProperties();

        HashMap<String, TextField> fields = new HashMap<>();

        WidgetTypeInstance widgetTypeInstance = new WidgetTypeInstance();

        for (WidgetTypeProperty property : properties) {
            TextField textField = new TextField(property.getProperty().getName());
            textField.addValueChangeListener(event -> {
                String value = event.getValue();
                widgetTypeInstance.putValue(property.getProperty().getName(), value);
            });
            fields.put(property.getProperty().getName(), textField);
            add(textField);
        }

        readButton.addClickListener(e -> {
            System.out.println(widgetTypeInstance);
        });

        add(readButton);
    }
}
