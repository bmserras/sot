package org.bmserras.sot.views.auth;

import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import com.vaadin.flow.router.internal.RouteUtil;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.bmserras.sot.security.AuthenticatedUser;

@Route("login")
@PageTitle("Login")
@AnonymousAllowed
public class LoginView extends VerticalLayout implements BeforeEnterObserver {

    private final AuthenticatedUser authenticatedUser;

    private final Image logo = new Image("icons/logo-variation-sot.svg", "Synoptics of Things");
    private final LoginForm login = new LoginForm();
    private final RouterLink register = new RouterLink("Register", RegisterView.class);

    public LoginView(AuthenticatedUser authenticatedUser) {
        this.authenticatedUser = authenticatedUser;

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);

        logo.setWidth("30%");

        login.setAction(RouteUtil.getRoutePath(VaadinService.getCurrent().getContext(), getClass()));
        login.setForgotPasswordButtonVisible(false);

        add(logo, login, register);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        if (authenticatedUser.get().isPresent()) {
            // Already logged in
            event.forwardTo("");
        }
        login.setError(event.getLocation().getQueryParameters().getParameters().containsKey("error"));
    }
}
