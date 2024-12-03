package org.bmserras.sot.views.auth;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.bmserras.sot.security.AuthenticatedUser;

@Route("register")
@PageTitle("Register")
@AnonymousAllowed
public class RegisterView extends VerticalLayout {

    private final AuthenticatedUser authenticatedUser;

    private final Image logo = new Image("icons/logo-variation-sot-workbench.svg", "Synoptics of Things");
    //private final Image logo = new Image("icons/logo-variation-sot-operations.svg", "Synoptics of Things");
    private final VerticalLayout register = new VerticalLayout();
    private final RouterLink login = new RouterLink("Login", LoginView.class);

    private final TextField username = new TextField("Username");
    private final PasswordField password1 = new PasswordField("Password");
    private final PasswordField password2 = new PasswordField("Confirm password");
    private final Button registerButton = new Button("Register");

    public RegisterView(AuthenticatedUser authenticatedUser) {
        this.authenticatedUser = authenticatedUser;

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);

        logo.setWidth("30%");


        username.setWidthFull();
        username.setRequired(true);
        username.setErrorMessage("Username is required");

        password1.setWidthFull();
        password1.setRequired(true);
        password1.setErrorMessage("Password is required");

        password2.setWidthFull();
        password2.setRequired(true);
        password2.setErrorMessage("Please confirm password");
        registerButton.addClickListener(clickEvent -> register(
                username.getValue(),
                password1.getValue(),
                password2.getValue()
        ));
        registerButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        registerButton.setWidthFull();
        registerButton.addClassName("register-button");
        registerButton.addClickShortcut(Key.ENTER);

        register.add(username, password1, password2, registerButton);
        register.setSizeFull();
        register.setJustifyContentMode(JustifyContentMode.CENTER);
        register.setAlignItems(Alignment.CENTER);
        register.setPadding(false);
        register.setSpacing(false);

        H2 title = new H2("Register");
        title.addClassName("register-title");

        VerticalLayout verticalLayout = new VerticalLayout(title, register);
        verticalLayout.setAlignSelf(Alignment.START, title);
        verticalLayout.setWidth("25%");
        verticalLayout.addClassName("register-form");
        verticalLayout.setSpacing(false);

        add(logo, verticalLayout, login);
    }

    private void register(String username, String password1, String password2) {
        if (username.trim().isEmpty()) {
            this.username.setInvalid(true);
        } else if (password1.isEmpty()) {
            this.password1.setInvalid(true);
        } else if (!password1.equals(password2)) {
            this.password2.setInvalid(true);
        } else {

            ConfirmDialog confirmDialog = new ConfirmDialog();
            confirmDialog.setHeader("Are you sure you want to register?");
            confirmDialog.setText("If so, you will be redirected to the login page.");
            confirmDialog.setCancelable(true);
            confirmDialog.setConfirmText("Register");
            confirmDialog.addConfirmListener(confirmEvent -> {
                try {
                    authenticatedUser.register(username, password1);
                    UI.getCurrent().navigate(LoginView.class);
                }
                catch (Exception e) {
                    ConfirmDialog errorDialog = new ConfirmDialog();
                    errorDialog.setHeader("That username is already taken.");
                    errorDialog.setText("Please choose another username.");
                    errorDialog.setCancelable(false);
                    errorDialog.setConfirmText("Ok");
                    errorDialog.open();
                }
            });
            confirmDialog.open();
        }
    }
}
