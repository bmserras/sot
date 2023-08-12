package org.bmserras.sot.views.widgettype;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import org.bmserras.sot.data.service.WidgetTypeService;
import org.bmserras.sot.icon.IsosIcon;
import org.bmserras.sot.icon.LaIcon;
import org.bmserras.sot.views.card.ExistingCard;
import org.bmserras.sot.views.card.NewCard;
import org.bmserras.sot.views.layout.AppLayoutNavbar;
import org.vaadin.lineawesome.LineAwesomeIcon;

@PageTitle("Widgets")
@Route(value = "widgets", layout = AppLayoutNavbar.class)
@PermitAll
public class WidgetsView extends VerticalLayout {

    private final WidgetTypeService service;

    public WidgetsView(WidgetTypeService service) {
        this.service = service;
        setSizeFull();

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setWidthFull();
        horizontalLayout.setHeight("40%");

        NewCard newWidgetType = new NewCard(LineAwesomeIcon.PLUS_CIRCLE_SOLID.create(), "New Widget", "Create new widget");
        newWidgetType.addMainButtonClickListener(mainButtonClick -> {
            mainButtonClick.getSource().getUI().ifPresent(ui -> ui.navigate("new-widget"));
        });

        horizontalLayout.add(newWidgetType);
        service.findAll().forEach(wt -> {

            Span icon = switch (wt.getImage().split("\\.")[0]) {
                case "la" -> LaIcon.valueOf(wt.getImage().split("\\.")[1]).create();
                case "isos" -> IsosIcon.valueOf(wt.getImage().split("\\.")[1]).create();
                default -> new Span();
            };

            ExistingCard ec = new ExistingCard(icon, wt.getName(), "Open widget");
            horizontalLayout.add(ec);
        });

        add(new H2("My Widgets"), horizontalLayout);
    }
}
