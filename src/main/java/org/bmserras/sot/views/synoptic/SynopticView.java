package org.bmserras.sot.views.synoptic;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.accordion.AccordionPanel;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.details.DetailsVariant;
import com.vaadin.flow.component.dnd.DragSource;
import com.vaadin.flow.component.dnd.DropTarget;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.dom.DomEvent;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import elemental.json.JsonObject;
import jakarta.annotation.security.PermitAll;
import org.bmserras.sot.data.domain.Widget;
import org.bmserras.sot.data.domain.readers.Gauge;
import org.bmserras.sot.data.domain.readers.SolidGauge;
import org.bmserras.sot.data.service.SynopticService;
import org.bmserras.sot.data.service.WidgetService;
import org.bmserras.sot.events.synoptic.WidgetDropEvent;
import org.bmserras.sot.views.layout.AppLayoutNavbar;
import org.bmserras.sot.views.widget.readers.gauge.GaugeComponent;
import org.bmserras.sot.views.widget.readers.solidgauge.SolidGaugeComponent;

import java.util.Optional;

@PageTitle("Synoptic View")
@Route(value = "synoptic", layout = AppLayoutNavbar.class)
@PermitAll
public class SynopticView extends HorizontalLayout implements HasUrlParameter<String> {

    private final Accordion list = new Accordion();
    private final VerticalLayout verticalLayout = new VerticalLayout();

    private final TextField synopticName = new TextField();

    private final SynopticCanvas canvas = new SynopticCanvas();

    private final SynopticService synopticService;
    private final WidgetService widgetService;

    public SynopticView(SynopticService synopticService, WidgetService widgetService) {
        this.synopticService = synopticService;
        this.widgetService = widgetService;
        setSizeFull();

        configureCanvas();

        verticalLayout.add(canvas);

        list.setSizeFull();

        VerticalLayout commonWidgetsLayout = new VerticalLayout();
        widgetService.findAll().forEach(w -> {
            Span span = new Span(w.getName());
            commonWidgetsLayout.add(span);
            DragSource<Span> dragSource = DragSource.create(span);
            dragSource.setDragData(w.getId());
        });

        AccordionPanel commonWidgetsPanel = list.add("Global widgets", commonWidgetsLayout);
        commonWidgetsPanel.addThemeVariants(DetailsVariant.FILLED);

        VerticalLayout specificWidgetsLayout = new VerticalLayout();

        AccordionPanel specificWidgetsPanel = list.add("Project widgets", specificWidgetsLayout);
        specificWidgetsPanel.addThemeVariants(DetailsVariant.FILLED);

        Scroller scroller = new Scroller(list, Scroller.ScrollDirection.VERTICAL);
        scroller.getStyle()
                .set("border-right", "1px solid var(--lumo-contrast-20pct)")
                .set("padding", "var(--lumo-space-m)");

        scroller.setWidth("20%");

        add(scroller, verticalLayout);
    }

    private double x;
    private double y;

    private void configureCanvas() {
        canvas.setSizeFull();
        DropTarget<SynopticCanvas> dropTarget = DropTarget.create(canvas);

        dropTarget.getElement().addEventListener("drop", domEvent -> {
                    JsonObject eventData = domEvent.getEventData();
                    x = eventData.getNumber("event.offsetX");
                    y = eventData.getNumber("event.offsetY");
                    System.out.println("ON DROP X = " + x + ", Y = " + y);
                    //fireEvent(new WidgetDropEvent(this, Optional.of(), x, y));
                })
                .addEventData("event.offsetX")
                .addEventData("event.offsetY");

        dropTarget.addDropListener(event -> {
            System.out.println("X = " + x + ", Y = " + y);
            event.getDragData().ifPresent(data -> {
                Optional<Widget> byId = widgetService.findById(data.toString());
                Widget widget = byId.get();
                System.out.println(widget);
                Span span = new Span(byId.get().getName());
                ContextMenu contextMenu = new ContextMenu(span);
                widget.getReaders().forEach(reader -> {
                    if (reader instanceof SolidGauge solidGauge) {
                        contextMenu.add(new SolidGaugeComponent(solidGauge));
                    }
                    else if (reader instanceof Gauge gauge) {
                        contextMenu.add(new GaugeComponent(gauge));
                    }
                });
                canvas.add(span, (int) x, (int) y);
            });
        });
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, String parameter) {
        synopticService.findById(parameter).ifPresent(synoptic -> synopticName.setValue(synoptic.getName()));
    }
}
