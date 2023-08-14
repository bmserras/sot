package org.bmserras.sot.old.type;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import org.bmserras.sot.data.db.widgettype.WidgetType;

public class WidgetTypeForm extends FormLayout {

    private final NumberField identifier = new NumberField("Identifier");
    private final TextField name = new TextField("Name");
    private final ComboBox<SVGImage> svgImage = new ComboBox<>("Image");

    private final Button save = new Button("Save");
    private final Button delete = new Button("Delete");
    private final Button close = new Button("Cancel");

    private final Binder<WidgetType> binder = new Binder<>(WidgetType.class);

    public WidgetTypeForm() {
        setSizeFull();

        /*binder.bind(identifier, widgetType -> (double) widgetType.getIdentifier(), null);
        binder.bind(name, WidgetType::getName, WidgetType::setName);
        binder.bind(svgImage, WidgetType::getImage, WidgetType::setImage);*/

        Image radarImg = new Image("svg/radar.svg", "radar");
        radarImg.setHeight("20px");
        SVGImage radar = new SVGImage(" Radar", radarImg);

        Image fanImg = new Image("svg/fan-blades-icon.svg", "fan");
        fanImg.setHeight("20px");
        SVGImage fan = new SVGImage(" Fan", fanImg);

        svgImage.setItems(radar, fan);
        svgImage.setItemLabelGenerator(SVGImage::getName);
        svgImage.setWidth("20%");
        svgImage.setRenderer(new ComponentRenderer<>(svgImage -> {
            Div div = new Div();
            div.add(svgImage.getImage());
            div.add(new Text(svgImage.getName()));
            return div;
        }));

        ComboBox<String> protocol = new ComboBox<>("Protocol", "SNMP", "REST", "SOAP");

        NumberField minValue = new NumberField("Minimum value");
        NumberField maxValue = new NumberField("Maximum value");

        add(identifier, name, svgImage, protocol, minValue, maxValue);
    }

}
