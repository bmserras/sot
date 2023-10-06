package org.bmserras.sot.views.widget;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import org.bmserras.sot.data.domain.Widget;
import org.bmserras.sot.security.AuthenticatedUser;
import org.bmserras.sot.views.layout.AppLayoutNavbar;

import java.util.ArrayList;
import java.util.List;

@PageTitle("Widgets")
@Route(value = "widgets", layout = AppLayoutNavbar.class)
@PermitAll
public class WidgetsView extends VerticalLayout {

    private AuthenticatedUser authenticatedUser;

    private WidgetLayout layout;

    public WidgetsView(AuthenticatedUser authenticatedUser) {
        this.authenticatedUser = authenticatedUser;
        setSizeFull();

        List<Widget> widgets = new ArrayList<>();

        layout = new WidgetLayout();
        layout.setItems(widgets);

        layout.addOpenListener(click -> {

        });
        layout.addSaveListener(click -> {
            click.getWidget().ifPresent(widget -> {
                widgets.add(widget);
                layout.setItems(widgets);
            });
        });
        layout.addDeleteListener(click -> {
            click.getWidget().ifPresent(widget -> {
                widgets.add(widget);
                layout.setItems(widgets);
            });
        });
        add(layout);
    }
}
