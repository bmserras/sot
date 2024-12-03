package org.bmserras.sot.views.synoptic;

import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.accordion.AccordionPanel;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.details.DetailsVariant;
import com.vaadin.flow.component.dnd.DragSource;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import org.bmserras.sot.MockData;
import org.bmserras.sot.data.domain.User;
import org.bmserras.sot.data.domain.Widget;
import org.bmserras.sot.data.domain.WidgetInstance;
import org.bmserras.sot.data.service.SynopticService;
import org.bmserras.sot.data.service.UserService;
import org.bmserras.sot.data.service.WidgetService;
import org.bmserras.sot.security.AuthenticatedUser;
import org.bmserras.sot.views.layout.AppLayoutNavbar;
import org.bmserras.sot.views.project.ProjectsView;
import org.bmserras.sot.views.widgetinstance.WidgetInstanceCard;
import org.vaadin.lineawesome.LineAwesomeIcon;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@PageTitle("Synoptic View")
@Route(value = "synoptic", layout = AppLayoutNavbar.class)
@PermitAll
public class SynopticView extends HorizontalLayout implements HasUrlParameter<String> {

    private final Accordion list = new Accordion();
    private final VerticalLayout verticalLayout = new VerticalLayout();

    private Button back;
    private final TextField name;
    private final Button save = new Button("Save");

    private final UserService userService;
    private final SynopticService synopticService;
    private final WidgetService widgetService;

    private AuthenticatedUser authenticatedUser;

    private Canvas canvas;

    public SynopticView(AuthenticatedUser authenticatedUser, SynopticService synopticService, WidgetService widgetService, UserService userService) {
        this.authenticatedUser = authenticatedUser;
        this.userService = userService;
        this.synopticService = synopticService;
        this.widgetService = widgetService;
        setSizeFull();

        back = new Button(LineAwesomeIcon.ARROW_LEFT_SOLID.create(), click ->
                click.getSource().getUI().ifPresent(ui -> ui.navigate(ProjectsView.class))
        );
        name = new TextField();

        Optional<User> userOp = authenticatedUser.get();
        if (userOp.isEmpty()) return;

        HorizontalLayout horizontalLayout = new HorizontalLayout(back, new Span("Synoptic"), name, save);
        horizontalLayout.setAlignItems(Alignment.CENTER);
        horizontalLayout.setMargin(true);

        list.setSizeFull();

        VerticalLayout projectWidgetsLayout = new VerticalLayout();
        /*userOp.get().getWidgets().forEach(w -> {
            Span span = new Span(w.getName());
            projectWidgetsLayout.add(span);
            DragSource<Span> dragSource = DragSource.create(span);
            dragSource.setDragData(w.getId());
        });*/

        Widget cabinA = MockData.createCabinA();
        Span spanCabinA = new Span(cabinA.getName());
        projectWidgetsLayout.add(spanCabinA);
        DragSource<Span> dragSource = DragSource.create(spanCabinA);
        dragSource.setDragData(cabinA.getId());

        Widget cinemometerA = MockData.createCinemometerA();
        Span spanCinemometerA = new Span(cinemometerA.getName());
        projectWidgetsLayout.add(spanCinemometerA);
        DragSource<Span> dragSourceCinemometerA = DragSource.create(spanCinemometerA);
        dragSourceCinemometerA.setDragData(cinemometerA.getId());

        Widget lctA = MockData.createLctA();
        Span spanLctA = new Span(lctA.getName());
        projectWidgetsLayout.add(spanLctA);
        DragSource<Span> dragSourceLctA = DragSource.create(spanLctA);
        dragSourceLctA.setDragData(lctA.getId());

        AccordionPanel projectWidgetsPanel = list.add("Project widgets", projectWidgetsLayout);
        projectWidgetsPanel.addThemeVariants(DetailsVariant.FILLED);

        VerticalLayout globalWidgetsLayout = new VerticalLayout();
        /*widgetService.findAll().forEach(w -> {
            Span span = new Span(w.getName());
            globalWidgetsLayout.add(span);
            DragSource<Span> dragSource = DragSource.create(span);
            dragSource.setDragData(w.getId());
        });*/

        globalWidgetsLayout.add();

        AccordionPanel globalWidgetsPanel = list.add("Global widgets", globalWidgetsLayout);
        globalWidgetsPanel.addThemeVariants(DetailsVariant.FILLED);

        Scroller scroller = new Scroller(list, Scroller.ScrollDirection.VERTICAL);
        scroller.getStyle()
                .set("border-right", "1px solid var(--lumo-contrast-20pct)")
                .set("padding", "var(--lumo-space-m)");

        scroller.setWidth("20%");

        verticalLayout.add(horizontalLayout);
        verticalLayout.setMargin(false);
        verticalLayout.setPadding(false);
        verticalLayout.setSpacing(false);
        add(scroller, verticalLayout);
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, String parameter) {
        synopticService.findById(parameter).ifPresent(synoptic -> {
            name.setValue(synoptic.getName());
            Canvas canvas = new Canvas(widgetService, synoptic.getGrid().getRows(), synoptic.getGrid().getCols());
            verticalLayout.add(canvas);

            synoptic.getWidgetInstances().forEach(wi -> {
                int posX = wi.getPosX();
                int posY = wi.getPosY();
                canvas.getSpace(posX, posY).add(
                        new WidgetInstanceCard(wi)
                );
                canvas.getSpace(posX, posY).getStyle().set("outline", "0px solid black");
            });

            save.addClickListener(event -> {
                List<WidgetInstance> widgetInstances = new ArrayList<>();
                canvas.getSpaces().forEach(space -> space.getWidgetInstanceOp().ifPresent(widgetInstances::add));
                synoptic.setWidgetInstances(widgetInstances);
                synopticService.save(synoptic);
                Notification.show("Synoptic saved");
            });
        });
    }
}
