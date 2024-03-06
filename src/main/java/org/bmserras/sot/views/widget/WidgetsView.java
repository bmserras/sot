package org.bmserras.sot.views.widget;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import org.bmserras.sot.data.domain.User;
import org.bmserras.sot.data.domain.Widget;
import org.bmserras.sot.data.service.UserService;
import org.bmserras.sot.data.service.WidgetService;
import org.bmserras.sot.security.AuthenticatedUser;
import org.bmserras.sot.views.layout.AppLayoutNavbar;

import java.util.List;
import java.util.Optional;

@PageTitle("Widgets")
@Route(value = "widgets", layout = AppLayoutNavbar.class)
@PermitAll
public class WidgetsView extends VerticalLayout {

    private AuthenticatedUser authenticatedUser;

    private UserService userService;
    private WidgetService widgetService;

    private WidgetLayout layout;

    public WidgetsView(AuthenticatedUser authenticatedUser, UserService userService, WidgetService widgetService) {
        this.authenticatedUser = authenticatedUser;
        this.userService = userService;
        this.widgetService = widgetService;
        setSizeFull();

        Optional<User> userOp = authenticatedUser.get();
        if (userOp.isEmpty()) return;
        User user = userOp.get();
        List<Widget> widgets = user.getWidgets();

        layout = new WidgetLayout("My Widgets", 40);
        layout.setItems(widgets);
        for (Widget widget : widgets) {
            System.out.println(widget);
        }

        layout.addOpenListener(click -> {
            Optional<Widget> widget = click.getWidget();
            System.out.println(widget.get());
            // open widget form
        });
        layout.addSaveListener(click -> {
            Optional<Widget> widget = click.getWidget();
            widget.ifPresent(w -> {
                if (w.getName().equals("")) w.setName("Blank Widget");
                if (!user.getWidgets().contains(w)) {
                    user.addWidget(w);
                    userService.save(user);
                }
                else widgetService.save(w);
                layout.setItems(user.getWidgets());
            });
        });
        layout.addDeleteListener(click -> {
            Optional<Widget> widget = click.getWidget();
            widget.ifPresent(p -> {
                user.removeWidget(p);
                userService.save(user);
                widgetService.delete(p);
                layout.setItems(user.getWidgets());
            });
        });
        add(layout);
    }
}
