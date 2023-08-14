package org.bmserras.sot.views.widgettype;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import org.bmserras.sot.views.layout.AppLayoutNavbar;

@PageTitle("Widget Types")
@Route(value = "widget-type-crud", layout = AppLayoutNavbar.class)
@PermitAll
public class WidgetTypeListView extends VerticalLayout {

    public WidgetTypeListView() {

        WidgetTypeForm widgetTypeForm = new WidgetTypeForm();
        widgetTypeForm.setWidth("50%");

        add(widgetTypeForm);
    }
}
