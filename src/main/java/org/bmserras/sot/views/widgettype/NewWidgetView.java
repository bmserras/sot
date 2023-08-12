package org.bmserras.sot.views.widgettype;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import org.bmserras.sot.data.entity.widgettype.Gauge;
import org.bmserras.sot.data.entity.widgettype.WidgetType;
import org.bmserras.sot.data.entity.widgettype.WidgetTypeEntity;
import org.bmserras.sot.data.service.WidgetTypeService;
import org.bmserras.sot.icon.CardIcon;
import org.bmserras.sot.icon.IsosIcon;
import org.bmserras.sot.icon.LaIcon;
import org.bmserras.sot.views.layout.AppLayoutNavbar;

import java.util.ArrayList;
import java.util.List;

@PageTitle("New Widget")
@Route(value = "new-widget", layout = AppLayoutNavbar.class)
@PermitAll
public class NewWidgetView extends VerticalLayout {

    private final List<String> properties;
    private final List<String> gauges;

    private final TextField name = new TextField("Name");
    private final Select<CardIcon> icon = new Select<>();

    private final VerticalLayout propertiesLayout = new VerticalLayout();
    private final Button addProperty = new Button("Add property");

    private final VerticalLayout gaugesLayout = new VerticalLayout();
    private final Button addGauge = new Button("Add gauge");

    private final Button create = new Button("Create");

    private final Binder<WidgetTypeEntity> binder = new Binder<>(WidgetTypeEntity.class);

    private final WidgetTypeService service;

    public NewWidgetView(WidgetTypeService service) {
        this.service = service;
        setSizeFull();

        /*SVGWrapper styleSvg = new SVGWrapper(
                "<svg height=\"100\" width=\"100\">\n" +
                        "  <circle cx=\"50\" cy=\"50\" r=\"40\" stroke=\"black\" stroke-width=\"3\" fill=\"red\" />\n" +
                        "  Sorry, your browser does not support inline SVG.  \n" +
                        "</svg> "
        );
        add(styleSvg);*/

        icon.setLabel("Choose icon");
        icon.setRenderer(new ComponentRenderer<>(cardIcon -> {
            FlexLayout wrapper = new FlexLayout();
            wrapper.setAlignItems(Alignment.CENTER);
            Div text = new Div();
            text.setText(cardIcon.getVisualName());
            wrapper.add(cardIcon, text);
            return wrapper;
        }));
        icon.setItems(
                LaIcon.PHONE_SOLID.create(),
                LaIcon.LAPTOP_SOLID.create(),
                LaIcon.LIGHTBULB.create(),
                IsosIcon.ISYSTEM.create(),
                IsosIcon.CES.create(),
                IsosIcon.SERVICE.create()
        );

        binder.bind(name, WidgetTypeEntity::getName, WidgetTypeEntity::setName);
        binder.bind(icon, WidgetTypeEntity::getIcon, WidgetTypeEntity::setIcon);

        create.addClickListener(click -> {
            WidgetTypeEntity bean = binder.getBean();

            String prefix = switch (bean.getIcon().getPath()) {
                case "line-awesome/svg/" -> "la.";
                case "icons/isos/" -> "isos.";
                default -> "unknown.";
            };

            WidgetType widgetType = new WidgetType(bean.getName(), prefix + bean.getIcon().getName());
            service.save(widgetType);
            click.getSource().getUI().ifPresent(ui -> ui.navigate("widgets"));
        });

        properties = new ArrayList<>();
        propertiesLayout.add(addProperty);

        addProperty.addClickListener(click -> {

            TextField property = new TextField("Name");
            Button add = new Button("Add");

            Dialog dialog = new Dialog("Add property", property, add);
            dialog.open();

            add.addClickListener(event -> {
                properties.add(property.getValue());
                dialog.close();
                updatePropertiesLayout();
            });
        });

        gauges = new ArrayList<>();
        gaugesLayout.add(addGauge);

        addGauge.addClickListener(click -> {

            GaugeForm gaugeForm = new GaugeForm();
            gaugeForm.setGauge(new Gauge());
            Button add = new Button("Add");

            Dialog dialog = new Dialog("Add gauge", gaugeForm, add);
            dialog.open();

            add.addClickListener(event -> {
                Gauge gauge = gaugeForm.getGauge();
                System.out.println(gauge);
                gauges.add(gauge.getName());
                dialog.close();
                updateGaugesLayout();
            });
        });

        add(
                name,
                icon,
                create,
                new H3("Properties"),
                propertiesLayout,
                new H3("Graphs"),
                gaugesLayout,
                new H3("Buttons"),
                new H3("Widgets")
        );

        binder.setBean(new WidgetTypeEntity());
    }

    private void updatePropertiesLayout() {
        propertiesLayout.removeAll();
        properties.forEach(prop -> propertiesLayout.add(new Span(prop)));
        propertiesLayout.add(addProperty);
    }

    private void updateGaugesLayout() {
        gaugesLayout.removeAll();
        gauges.forEach(gauge -> gaugesLayout.add(new Span(gauge)));
        gaugesLayout.add(addGauge);
    }
}
