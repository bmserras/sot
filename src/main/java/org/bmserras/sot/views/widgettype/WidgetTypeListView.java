package org.bmserras.sot.views.widgettype;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.bmserras.sot.views.layout.MainLayout;

@PageTitle("Widget Types")
@Route(value = "widget-type-crud", layout = MainLayout.class)
public class WidgetTypeListView extends VerticalLayout {

    public WidgetTypeListView() {

        WidgetTypeForm widgetTypeForm = new WidgetTypeForm();

        add(widgetTypeForm);
    }
}
