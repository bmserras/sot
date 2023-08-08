package org.bmserras.sot.views.synoptic;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.security.AuthenticationContext;
import jakarta.annotation.security.PermitAll;
import org.bmserras.sot.components.RadarWidgetComponent;
import org.bmserras.sot.components.WidgetComponent;
import org.bmserras.sot.data.entity.widget.RadarWidget;
import org.bmserras.sot.data.entity.synoptic.Synoptic;
import org.bmserras.sot.data.entity.widget.Widget;
import org.bmserras.sot.data.service.SynopticService;
import org.bmserras.sot.data.service.WidgetService;
import org.bmserras.sot.views.layout.MainLayout;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

@PageTitle("Synoptic View")
@Route(value = "synoptic", layout = MainLayout.class)
@PermitAll
public class SynopticView extends VerticalLayout implements HasUrlParameter<String> {

    private final TextField synopticName = new TextField();
    private final Button runSynoptic = new Button("Run", new Icon(VaadinIcon.PLAY));
    private final Button stopSynoptic = new Button("Stop", new Icon(VaadinIcon.STOP));
    private final Button addWidget = new Button("Add", new Icon(VaadinIcon.PLUS));

    private final SynopticCanvas canvas = new SynopticCanvas();

    private final WidgetService widgetService;
    private final SynopticService synopticService;

    private final transient AuthenticationContext authContext;

    public SynopticView(AuthenticationContext authContext, WidgetService widgetService, SynopticService synopticService) {
        this.authContext = authContext;
        setSizeFull();

        this.widgetService = widgetService;
        this.synopticService = synopticService;

        configureCanvas();

        add(getToolbar(), canvas);
    }

    private void configureCanvas() {
        canvas.setSizeFull();
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
        synopticName.setValue(parameter);
        populateCanvas();

        authContext.getAuthenticatedUser(UserDetails.class).map(user -> {
            if (!user.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")))
                runSynoptic();
            return null;
        });
    }
}
