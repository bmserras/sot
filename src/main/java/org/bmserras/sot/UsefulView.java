package org.bmserras.sot;

import com.vaadin.componentfactory.ToggleButton;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import org.bmserras.sot.data.domain.readers.GaugeData;
import org.bmserras.sot.views.widget.readers.gauge.GaugeChart;
import org.bmserras.sot.views.layout.AppLayoutNavbar;
import org.vaadin.lineawesome.LineAwesomeIcon;

@PageTitle("Test")
@Route(value = "useful", layout = AppLayoutNavbar.class)
@PermitAll
public class UsefulView extends VerticalLayout {

    public UsefulView() {

        /*HorizontalLayout tag1 = new HorizontalLayout(new TextField("Name"), new TextField("Value"));
        HorizontalLayout tag2 = new HorizontalLayout(new TextField("Name"), new TextField("Value"));
        HorizontalLayout tag3 = new HorizontalLayout(new TextField("Name"), new TextField("Value"));
        HorizontalLayout tag4 = new HorizontalLayout(new TextField("Name"), new TextField("Value"));

        ComboBox<String> protocol1 = new ComboBox<>("Protocol", "HTTP (REST)", "SNMP");
        protocol1.setValue("HTTP (REST)");
        TextField url = new TextField("URL");
        TextField port = new TextField("Port");
        TextField path = new TextField("Path");
        TextField username = new TextField("Username");
        PasswordField password = new PasswordField("Password");

        ComboBox<String> protocol2 = new ComboBox<>("Protocol", "HTTP (REST)", "SNMP");
        protocol2.setValue("SNMP");
        TextField ip = new TextField("IP");
        TextField portS = new TextField("Port");
        TextField oid = new TextField("OID");
        TextField usernameS = new TextField("Username");
        TextField passwordS = new TextField("Password");


        //add(new HorizontalLayout(new H4("Tags")), tag1, tag2, tag3, tag4, new Button("Add tag", LineAwesomeIcon.PLUS_SOLID.create()));

        add(new HorizontalLayout(), new H4("Connection"), protocol1, new HorizontalLayout(url, port, path), new HorizontalLayout(username, password));*/




    }

}
