package org.bmserras.sot.views.widgettype;

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
import com.vaadin.flow.data.renderer.LitRenderer;
import com.vaadin.flow.data.renderer.Renderer;
import org.bmserras.sot.data.entity.widgettype.WidgetType;

public class WidgetTypeForm extends FormLayout {

    private final NumberField identifier = new NumberField("Identifier");
    private final TextField name = new TextField("Name");
    private final ComboBox<SVGImage> svgImage = new ComboBox<>("Image");

    private final Button save = new Button("Save");
    private final Button delete = new Button("Delete");
    private final Button close = new Button("Cancel");

    private final Binder<WidgetType> binder = new Binder<>(WidgetType.class);

    public WidgetTypeForm() {

        /*binder.bind(identifier, widgetType -> (double) widgetType.getIdentifier(), null);
        binder.bind(name, WidgetType::getName, WidgetType::setName);
        binder.bind(svgImage, WidgetType::getImage, WidgetType::setImage);*/

        Image radarImg = new Image("svg/radar.svg", "radar");
        radarImg.setHeight("20px");
        /*SVGImage radar = new SVGImage("Radar", "<svg viewBox=\"0 0 100 100\" xmlns=\"http://www.w3" +
                ".org/2000/svg\">\n" +
                "  <circle cx=\"50\" cy=\"50\" r=\"50\" />\n" +
                "</svg>");*/
        SVGImage radar = new SVGImage("Radar", radarImg);

        Image fanImg = new Image("svg/fan-blades-icon.svg", "fan");
        fanImg.setHeight("20px");
        /*SVGImage fan = new SVGImage("Fan", "<svg viewBox=\"0 0 220 100\" xmlns=\"http://www.w3" +
                ".org/2000/svg\">\n" +
                "  <!-- Simple rectangle -->\n" +
                "  <rect width=\"100\" height=\"100\" />\n" +
                "</svg>");*/
        SVGImage fan = new SVGImage("Fan", fanImg);

        svgImage.setItems(radar, fan);
        svgImage.setItemLabelGenerator(SVGImage::getName);
        //svgImage.setRenderer(createRenderer());
        svgImage.setRenderer(new ComponentRenderer<>(svgImage -> {
            Div div = new Div();

            div.add(svgImage.getImage());
            div.add(new Text(svgImage.getName()));

            return div;
        }));

        add(svgImage);
    }

    private Renderer<SVGImage> createRenderer() {

        StringBuilder tpl = new StringBuilder();
        tpl.append("<div style=\"display: flex;\">");

        tpl.append(
                "${item.pictureUrl}");

        tpl.append("  <div>");
        tpl.append("    ${item.firstName}");
        tpl.append("  </div>");

        tpl.append("</div>");

        return LitRenderer.<SVGImage> of(tpl.toString())
                .withProperty("pictureUrl", SVGImage::getSvg)
                .withProperty("firstName", SVGImage::getName);

    }
}
