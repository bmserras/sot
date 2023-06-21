package org.bmserras.sot.views.synoptic;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.bmserras.sot.data.entity.Synoptic;
import org.bmserras.sot.data.entity.Widget;
import org.bmserras.sot.data.service.SynopticService;
import org.bmserras.sot.data.service.WidgetService;
import org.bmserras.sot.views.MainLayout;

@PageTitle("Synoptic View")
@Route(value = "synoptic", layout = MainLayout.class)
public class SynopticView extends VerticalLayout implements HasUrlParameter<String> {

    private final TextField synopticName = new TextField();
    private final Button runSynoptic = new Button("Run");
    private final Button stopSynoptic = new Button("Stop");
    private final Button addWidget = new Button("Add");

    private final SynopticCanvas canvas = new SynopticCanvas();

    private final WidgetService widgetService;
    private final SynopticService synopticService;

    public SynopticView(WidgetService widgetService, SynopticService synopticService) {
        setSizeFull();

        System.out.println("CTOR");

        this.widgetService = widgetService;
        this.synopticService = synopticService;

        configureCanvas();

        add(getToolbar(), canvas);
    }

    private void configureCanvas() {
        canvas.setSizeFull();
    }

    private void populateCanvas() {
        Synoptic synoptic = synopticService.findSynopticByName(synopticName.getValue());
        synoptic.getWidgets().forEach(sw -> {
            Widget widget = sw.getWidget();
            int pos = sw.getPos();
            canvas.add(new Span(widget.getName()), pos);
        });
    }

    private HorizontalLayout getToolbar() {

        synopticName.setLabel("Synoptic");
        synopticName.setReadOnly(true);

        runSynoptic.addClickListener(click -> runSynoptic());
        stopSynoptic.addClickListener(click -> stopSynoptic());
        addWidget.addClickListener(click -> addWidget());

        HorizontalLayout toolbar = new HorizontalLayout(synopticName, runSynoptic, stopSynoptic, addWidget);
        toolbar.setAlignItems(Alignment.BASELINE);

        return toolbar;
    }

    private void addWidget() {

        Select<Widget> selectWidget = new Select<>("Widget", click -> {});
        selectWidget.setItemLabelGenerator(Widget::getName);
        selectWidget.setItems(widgetService.findAllWidgets());

        IntegerField position = new IntegerField("Position");
        Button addWidget = new Button("Add");

        Dialog addWidgetDialog = new Dialog("Add widget", new VerticalLayout(selectWidget, position, addWidget));

        addWidget.addClickListener(click -> {

            canvas.add(new Span(selectWidget.getValue().getName()), position.getValue());
            addWidgetDialog.close();

            Synoptic synoptic = synopticService.findSynopticByName(synopticName.getValue());
            Widget widget = widgetService.findWidgetByName(selectWidget.getValue().getName());
            synoptic.addWidget(widget, position.getValue());

            synopticService.saveSynoptic(synoptic);
        });

        addWidgetDialog.open();
    }

    private void stopSynoptic() {
    }

    private void runSynoptic() {
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, String parameter) {
        synopticName.setValue(parameter);
        populateCanvas();
    }
}
