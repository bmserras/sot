package org.bmserras.sot.test.cabinwidgetthread;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.bmserras.sot.views.layout.AppLayoutNavbar;

@PageTitle("CabinWidgetThread View")
@Route(value = "cabin-widget-thread", layout = AppLayoutNavbar.class)
public class CabinWidgetThreadView extends HorizontalLayout {

    private CabinWidgetThread cabinWidgetThread;

    public CabinWidgetThreadView() {

        setSizeFull();

        cabinWidgetThread = new CabinWidgetThread();
        cabinWidgetThread.setName("A1 km 12");
        cabinWidgetThread.start();

        add(cabinWidgetThread);
    }

}
