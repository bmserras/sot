package org.bmserras.sot.views;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import org.bmserras.sot.views.card.ExistingCard;
import org.bmserras.sot.views.card.NewCard;
import org.bmserras.sot.views.layout.AppLayoutNavbar;
import org.vaadin.lineawesome.LineAwesomeIcon;

@PageTitle("Widgets")
@Route(value = "widgets", layout = AppLayoutNavbar.class)
@PermitAll
public class WidgetsView extends VerticalLayout {

    public WidgetsView() {

        setSizeFull();

        NewCard newProject = new NewCard(LineAwesomeIcon.PLUS_CIRCLE_SOLID.create(), "New Widget", "Create new widget");
        ExistingCard widget1 = new ExistingCard(LineAwesomeIcon.WALLET_SOLID.create(), "Widget 1", "Open widget");
        ExistingCard widget2 = new ExistingCard(LineAwesomeIcon.PHONE_SOLID.create(), "Widget 2", "Open widget");
        ExistingCard widget3 = new ExistingCard(LineAwesomeIcon.LAPTOP_SOLID.create(), "Widget 3", "Open widget");

        HorizontalLayout horizontalLayout = new HorizontalLayout(newProject, widget1, widget2, widget3);
        horizontalLayout.setWidthFull();
        horizontalLayout.setHeight("40%");

        add(new H2("My Widgets"), horizontalLayout);

    }
}
