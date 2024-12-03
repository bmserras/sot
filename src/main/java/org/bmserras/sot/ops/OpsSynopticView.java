package org.bmserras.sot.ops;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.*;
import jakarta.annotation.security.PermitAll;
import org.bmserras.sot.data.domain.Project;
import org.bmserras.sot.data.domain.Widget;
import org.bmserras.sot.data.domain.WidgetInstance;
import org.bmserras.sot.data.domain.readers.GaugeData;
import org.bmserras.sot.data.domain.readers.SolidGaugeData;
import org.bmserras.sot.data.service.SynopticService;
import org.bmserras.sot.data.service.WidgetService;
import org.bmserras.sot.views.layout.AppLayoutNavbar;
import org.bmserras.sot.views.synoptic.Canvas;
import org.bmserras.sot.views.widgetinstance.WidgetInstanceCard;
import org.vaadin.lineawesome.LineAwesomeIcon;

import java.util.List;
import java.util.Optional;

@PageTitle("Synoptic View (Operations)")
@Route(value = "ops/synoptic", layout = AppLayoutNavbar.class)
@PermitAll
public class OpsSynopticView extends VerticalLayout implements HasUrlParameter<String> {

    private Button back;
    private final TextField synopticName = new TextField();
    private final Button play = new Button("Play", LineAwesomeIcon.PLAY_SOLID.create());
    private final Button stop = new Button("Stop", LineAwesomeIcon.SQUARE_SOLID.create());

    private final WidgetService widgetService;

    public OpsSynopticView(WidgetService widgetService) {
        this.widgetService = widgetService;
        setSizeFull();

        Canvas canvas = new Canvas(widgetService, 3, 3, 4, 3);

        synopticName.setValue("SINCRO IC19");
        synopticName.setReadOnly(true);

        back = new Button(LineAwesomeIcon.ARROW_LEFT_SOLID.create());

        play.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS);
        play.addClickListener(e -> {
            stop.setEnabled(true);
            play.setEnabled(false);

            for (WidgetInstanceCard widgetInstanceCard : canvas.getWidgetInstanceCards()) {
                widgetInstanceCard.getStyle().set("border-color", "green");

                if (widgetInstanceCard.getWidgetInstance().getName().equals("LCT A")) {
                    widgetInstanceCard.getStyle().set("border-color", "#e3d434");
                } else if (widgetInstanceCard.getWidgetInstance().getName().equals("Cabin A")) {
                    widgetInstanceCard.getStyle().set("border-color", "green");
                } else if (widgetInstanceCard.getWidgetInstance().getName().equals("Cinemometer A")) {
                    widgetInstanceCard.getStyle().set("border-color", "red");
                }
            }

        });

        stop.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);
        stop.addClickListener(e -> {
            play.setEnabled(true);
            stop.setEnabled(false);

            for (WidgetInstanceCard widgetInstanceCard : canvas.getWidgetInstanceCards()) {
                widgetInstanceCard.getStyle().set("border-color", "#33ccff");
            }
        });
        stop.setEnabled(false);

        HorizontalLayout horizontalLayout = new HorizontalLayout(back, synopticName, play, stop);
        horizontalLayout.setAlignItems(Alignment.BASELINE);
        horizontalLayout.setMargin(true);

        setAlignSelf(Alignment.CENTER, horizontalLayout);



        add(horizontalLayout, canvas);
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, String parameter) {

    }
}
