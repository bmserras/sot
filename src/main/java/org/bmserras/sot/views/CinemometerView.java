package org.bmserras.sot.views;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.bmserras.sot.views.layout.MainLayout;

@PageTitle("Cinemometer View")
@Route(value = "cinemometer", layout = MainLayout.class)
public class CinemometerView extends HorizontalLayout {

    public CinemometerView() {
        setSizeFull();


    }
}
