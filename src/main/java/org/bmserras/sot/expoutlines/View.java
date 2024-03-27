package org.bmserras.sot.expoutlines;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import org.bmserras.sot.views.layout.AppLayoutNavbar;

@PageTitle("Experimental View")
@Route(value = "view", layout = AppLayoutNavbar.class)
@PermitAll
public class View extends VerticalLayout {

    int cols = 5;
    int rows = 2;

    public View() {

        setSizeFull();

        Grid grid = new Grid(3, 2, 2, 3);
        add(grid);



    }
}
