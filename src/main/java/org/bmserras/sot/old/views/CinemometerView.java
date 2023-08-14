package org.bmserras.sot.old.views;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import org.bmserras.sot.views.layout.AppLayoutNavbar;

@PageTitle("Cinemometer View")
@Route(value = "cinemometer", layout = AppLayoutNavbar.class)
@PermitAll
public class CinemometerView extends HorizontalLayout {

    public CinemometerView() {
        setSizeFull();


    }
}
