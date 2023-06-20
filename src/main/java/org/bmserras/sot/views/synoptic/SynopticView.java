package org.bmserras.sot.views.synoptic;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.accordion.AccordionPanel;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.details.DetailsVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.dnd.DragSource;
import com.vaadin.flow.component.dnd.DropTarget;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.dom.DomEvent;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.security.AuthenticationContext;
import elemental.json.JsonObject;
import jakarta.annotation.security.PermitAll;
import org.bmserras.sot.components.RadarWidgetComponent;
import org.bmserras.sot.components.WidgetComponent;
import org.bmserras.sot.data.entity.synoptic.Synoptic;
import org.bmserras.sot.data.entity.widget.RadarWidget;
import org.bmserras.sot.data.entity.widget.Widget;
import org.bmserras.sot.data.entity.widgettype.WidgetType;
import org.bmserras.sot.data.service.SynopticService;
import org.bmserras.sot.data.service.WidgetService;
import org.bmserras.sot.data.service.WidgetTypeService;
import org.bmserras.sot.icon.IsosIcon;
import org.bmserras.sot.icon.LaIcon;
import org.bmserras.sot.views.layout.AppLayoutNavbar;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

@PageTitle("Synoptic View")
@Route(value = "synoptic", layout = AppLayoutNavbar.class)
@PermitAll
public class SynopticView extends HorizontalLayout implements HasUrlParameter<String> {

    private final Accordion list = new Accordion();
    private final VerticalLayout verticalLayout = new VerticalLayout();

    private final TextField synopticName = new TextField();
    private final Button runSynoptic = new Button("Run", new Icon(VaadinIcon.PLAY));
    private final Button stopSynoptic = new Button("Stop", new Icon(VaadinIcon.STOP));
    private final Button addWidget = new Button("Add", new Icon(VaadinIcon.PLUS));

    private final SynopticCanvas canvas = new SynopticCanvas();

    private final WidgetService widgetService;
    private final SynopticService synopticService;
    private final WidgetTypeService widgetTypeService;

    private final transient AuthenticationContext authContext;

    private final HorizontalLayout horizontalLayout1 = new HorizontalLayout();

    public SynopticView(AuthenticationContext authContext, WidgetService widgetService,
                        SynopticService synopticService, WidgetTypeService widgetTypeService) {
        this.authContext = authContext;
        setSizeFull();

        this.widgetService = widgetService;
        this.synopticService = synopticService;
        this.widgetTypeService = widgetTypeService;

        configureCanvas();

        verticalLayout.add(getToolbar(), canvas);

        list.setSizeFull();

        VerticalLayout commonWidgetsLayout = new VerticalLayout();
        widgetTypeService.findAll().forEach(wt -> {

            Span icon = switch (wt.getImage().split("\\.")[0]) {
                case "la" -> LaIcon.valueOf(wt.getImage().split("\\.")[1]).create();
                case "isos" -> IsosIcon.valueOf(wt.getImage().split("\\.")[1]).create();
                default -> new Span();
            };

            HorizontalLayout horizontalLayout =
                    new HorizontalLayout(icon,
                    new Span(wt.getName()));
            commonWidgetsLayout.add(horizontalLayout);
            DragSource<HorizontalLayout> dragSource = DragSource.create(horizontalLayout);
            dragSource.setDragData(wt.getIdentifier());
            dragSource.addDragStartListener(event -> {
                horizontalLayout1.addClassName("highlight");
            });
            dragSource.addDragEndListener(event -> {
                horizontalLayout1.removeClassName("highlight");
            });

            dragSource.getElement().addEventListener("drag", this::onDrag)
                    .addEventData("event.offsetX")
                    .addEventData("event.offsetY");

        });

        AccordionPanel commonWidgetsPanel = list.add("Common widgets", commonWidgetsLayout);
        commonWidgetsPanel.addThemeVariants(DetailsVariant.FILLED);

        VerticalLayout specificWidgetsLayout = new VerticalLayout();

        AccordionPanel specificWidgetsPanel = list.add("Specific widgets", specificWidgetsLayout);
        specificWidgetsPanel.addThemeVariants(DetailsVariant.FILLED);

        Scroller scroller = new Scroller(list, Scroller.ScrollDirection.VERTICAL);
        scroller.getStyle()
                .set("border-right", "1px solid var(--lumo-contrast-20pct)")
                .set("padding", "var(--lumo-space-m)");

        scroller.setWidth("20%");

        add(scroller, verticalLayout);
    }

    private void onDrag(DomEvent domEvent) {
        JsonObject eventData = domEvent.getEventData();
        x = eventData.getNumber("event.offsetX");
        y = eventData.getNumber("event.offsetY");

        System.out.println("XX = " + x + ", YY = " + y);
    }

    private void configureCanvas() {

        horizontalLayout1.setHeight("100px");
        horizontalLayout1.setWidth("100px");
        horizontalLayout1.getStyle().set("border", "1px solid var(--lumo-contrast-20pct)");

        DropTarget<HorizontalLayout> dropTarget2 = DropTarget.create(horizontalLayout1);

        canvas.setSizeFull();
        DropTarget<SynopticCanvas> dropTarget = DropTarget.create(canvas);
        dropTarget.addDropListener(event -> {

            UI.getCurrent().getPage().executeJs("return JSON.parse('{ " +
                    "\"x\":'+window.dragDropX+', " +
                    "\"y\":'+window.dragDropY+' " +
                    "}')").then(jsonValue -> {

                int newX = 422;
                int newY = 113;

                String xstr = jsonValue.toString().split(":")[1];
                //int x = Integer.parseInt(xstr.split(",")[0]);

                String ystr = jsonValue.toString().split(":")[2];
                //int y = Integer.parseInt(ystr.split("}")[0]);

                System.out.println("X = " + x + ", Y = " + y);

                event.getDragData().ifPresent(data -> {
                    Optional<WidgetType> byId = widgetTypeService.findById(data.toString());
                    System.out.println(byId.get().getName());

                    Span icon = switch (byId.get().getImage().split("\\.")[0]) {
                        case "la" -> LaIcon.valueOf(byId.get().getImage().split("\\.")[1]).create();
                        case "isos" -> IsosIcon.valueOf(byId.get().getImage().split("\\.")[1]).create();
                        default -> new Span();
                    };

                    HorizontalLayout horizontalLayout =
                            new HorizontalLayout(icon, new Span(byId.get().getName()));

                    canvas.add(new Span(horizontalLayout), (int) x, (int) y);
                });


            });

        });

        dropTarget.getElement().addEventListener("drop", this::onDrop)
                .addEventData("event.offsetX")
                .addEventData("event.offsetY");



        canvas.add(horizontalLayout1, 300, 300);
    }

    private double x;
    private double y;

    private void onDrop(DomEvent event) {

        JsonObject eventData = event.getEventData();
        x = eventData.getNumber("event.offsetX");
        y = eventData.getNumber("event.offsetY");

        System.out.println("X = " + x + ", Y = " + y);
    }

    private void populateCanvas() {
        Optional<Synoptic> synoptic = synopticService.findByName(synopticName.getValue());
        synoptic.get().getWidgets().forEach(sw -> {
            Widget widget = sw.getWidget();
            int pos = sw.getPos();

            if (widget instanceof RadarWidget) {
                RadarWidgetComponent radarWidgetComponent = new RadarWidgetComponent(widgetService, (RadarWidget) widget);
                radarWidgetComponent.addRemoveWidgetListener(event -> {
                    synoptic.get().removeWidget(widget, pos);
                    synopticService.save(synoptic.get());
                    canvas.remove(radarWidgetComponent);
                });
                canvas.add(radarWidgetComponent, pos);
            }
            else {
                System.out.println("WHAT???");
            }

        });
    }

    private HorizontalLayout getToolbar() {
        synopticName.setReadOnly(true);

        runSynoptic.addClickListener(click -> runSynoptic());

        stopSynoptic.addClickListener(click -> stopSynoptic());
        stopSynoptic.setEnabled(false);

        addWidget.addClickListener(click -> addWidget());

        HorizontalLayout toolbar = authContext.getAuthenticatedUser(UserDetails.class).map(user -> {
            if (user.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")))
                return new HorizontalLayout(synopticName, runSynoptic, stopSynoptic, addWidget);
            return new HorizontalLayout(synopticName);
        }).orElseThrow((() -> new RuntimeException("User should be logged in!")));

        toolbar.setAlignItems(Alignment.BASELINE);

        return toolbar;
    }

    private void addWidget() {

        Select<Widget> selectWidget = new Select<>("Widget", click -> {});
        selectWidget.setItemLabelGenerator(Widget::getName);
        selectWidget.setItems(widgetService.findAll(""));

        IntegerField position = new IntegerField("Position");
        Button addWidget = new Button("Add");

        Dialog addWidgetDialog = new Dialog("Add widget", new VerticalLayout(selectWidget, position, addWidget));

        addWidget.addClickListener(click -> {

            Optional<Synoptic> synoptic = synopticService.findByName(synopticName.getValue());
            Optional<Widget> widget = widgetService.findByName(selectWidget.getValue().getName());
            synoptic.get().addWidget(widget.get(), position.getValue());
            synopticService.save(synoptic.get());

            if (widget.get() instanceof RadarWidget) {
                canvas.add(new RadarWidgetComponent(widgetService, (RadarWidget) widget.get()), position.getValue());
            }
            else {
                System.out.println("WHAT???");
            }

            addWidgetDialog.close();
        });

        addWidgetDialog.open();
    }

    private void stopSynoptic() {
        canvas.getChildren().forEach(component -> {
            WidgetComponent wc = (WidgetComponent) component;
            wc.stop();
        });
        stopSynoptic.setEnabled(false);
        runSynoptic.setEnabled(true);
        addWidget.setEnabled(true);
    }

    private void runSynoptic() {
        canvas.getChildren().forEach(component -> {
            WidgetComponent wc = (WidgetComponent) component;
            wc.run();
        });
        runSynoptic.setEnabled(false);
        stopSynoptic.setEnabled(true);
        addWidget.setEnabled(false);
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, String parameter) {
        synopticService.findById(parameter).ifPresent(synoptic -> synopticName.setValue(synoptic.getName()));
        populateCanvas();

        /*authContext.getAuthenticatedUser(UserDetails.class).map(user -> {
            if (!user.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")))
                runSynoptic();
            return null;
        });*/
    }
}
