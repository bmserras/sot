package org.bmserras.sot.views;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import org.bmserras.sot.components.card.ExistingCard;
import org.bmserras.sot.components.card.NewCard;
import org.bmserras.sot.views.layout.AppLayoutNavbar;
import org.vaadin.lineawesome.LineAwesomeIcon;

@PageTitle("Widgets")
@Route(value = "widgets", layout = AppLayoutNavbar.class)
@PermitAll
public class WidgetsView extends VerticalLayout {

    public WidgetsView() {

        setSizeFull();

        ExistingCard project1 = new ExistingCard(LineAwesomeIcon.WALLET_SOLID.create(), "Widget 1");
        ExistingCard project2 = new ExistingCard(LineAwesomeIcon.PHONE_SOLID.create(), "Widget 2");
        ExistingCard project3 = new ExistingCard(LineAwesomeIcon.LAPTOP_SOLID.create(), "Widget 3");
        NewCard newProject = new NewCard(LineAwesomeIcon.PLUS_CIRCLE_SOLID.create(), "New Widget");

        HorizontalLayout horizontalLayout = new HorizontalLayout(project1, project2, project3, newProject);
        horizontalLayout.setWidthFull();
        horizontalLayout.setHeight("40%");

        add(new H2("My Widgets"), horizontalLayout);

    }
}
