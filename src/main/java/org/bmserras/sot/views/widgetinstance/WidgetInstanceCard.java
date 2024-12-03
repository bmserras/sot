package org.bmserras.sot.views.widgetinstance;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.StreamResource;
import org.bmserras.sot.data.domain.WidgetInstance;
import org.bmserras.sot.icon.CardIcon;
import org.bmserras.sot.icon.CustomIcon;
import org.bmserras.sot.icon.IsosIcon;
import org.bmserras.sot.icon.LaIcon;
import org.bmserras.sot.views.components.CustomDialog;
import org.bmserras.sot.views.widget.card.WidgetExistingCard;
import org.vaadin.lineawesome.LineAwesomeIcon;

import java.io.ByteArrayInputStream;

public class WidgetInstanceCard extends VerticalLayout {

    private final Button mainButton;

    private final HorizontalLayout container;
    private final Span title;
    private final Button optionsButton;

    private final WidgetInstance widgetInstance;

    public WidgetInstanceCard(WidgetInstance widgetInstance) {
        this.widgetInstance = widgetInstance;
        addClassName("widget-instance-card");

        setSizeFull();

        String type = widgetInstance.getWidget().getImage().getType();
        if (type.equals("icon/la")) {
            CardIcon cardIcon = LaIcon.valueOf(widgetInstance.getWidget().getImage().getIconName()).create();
            this.mainButton = new Button(cardIcon);
        }
        else if (type.equals("icon/isos")) {
            CardIcon cardIcon = IsosIcon.valueOf(widgetInstance.getWidget().getImage().getIconName()).create();
            this.mainButton = new Button(cardIcon);
        }
        else if (type.equals("icon/custom")) {
            CardIcon cardIcon = CustomIcon.valueOf(widgetInstance.getWidget().getImage().getIconName()).create();
            this.mainButton = new Button(cardIcon);
        }
        else {
            byte[] data = widgetInstance.getWidget().getImage().getData();
            Image image = new Image(new StreamResource("image", () -> new ByteArrayInputStream(data)), "image");
            image.setWidth("24px");
            image.setHeight("24px");
            this.mainButton = new Button(image);
        }

        this.mainButton.addClassName("main-button");

        this.title = new Span(widgetInstance.getName());
        this.title.addClassName("title");

        this.optionsButton = new Button(LineAwesomeIcon.ELLIPSIS_V_SOLID.create());
        this.optionsButton.addClassName("options-button");
        this.optionsButton.setTooltipText("Options");

        this.container = new HorizontalLayout(this.title, optionsButton);
        this.container.addClassName("container");

        this.mainButton.addClickListener(click -> {

            WidgetInstanceMaximized widgetInstanceMaximized = new WidgetInstanceMaximized(widgetInstance);

            CustomDialog dialog = new CustomDialog(60, 80);
            dialog.setTitle("Configure widget");
            dialog.add(widgetInstanceMaximized);
            dialog.open();
            dialog.setDeleteButtonVisible(false);

        });

        getStyle().set("border-width", widgetInstance.getWidget().getBorderWidth() + "px");
        getStyle().set("border-style", widgetInstance.getWidget().getBorderStyle());
        getStyle().set("border-color", "#33ccff");

        /*if (widgetInstance.getName().equals("Cabin (LCT A)"))
            getStyle().set("border-color", "green");
        else if (widgetInstance.getName().equals("Cinemometer (LCT A)"))
            getStyle().set("border-color", "red");
        if (widgetInstance.getName().equals("Cabin A"))
            getStyle().set("border-color", "green");
        else if (widgetInstance.getName().equals("Cinemometer A"))
            getStyle().set("border-color", "red");
        else if (widgetInstance.getName().equals("LCT A")) {
            getStyle().set("border-color", "#e3d434");
        }*/

        add(mainButton, container);

    }

    public WidgetInstance getWidgetInstance() {
        return widgetInstance;
    }
}
