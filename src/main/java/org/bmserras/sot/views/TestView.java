package org.bmserras.sot.views;

import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.bmserras.sot.components.RadarWidgetComponent;
import org.bmserras.sot.data.entity.RadarWidget;
import org.bmserras.sot.views.layout.MainLayout;

@PageTitle("Test View")
@Route(value = "test", layout = MainLayout.class)
public class TestView extends HorizontalLayout {

    /*private final RadarWidgetComponent radarWidgetComponent;
    private final RadarWidgetComponent radarWidgetComponent2;
    private final RadarWidgetComponent radarWidgetComponent3;
    private final RadarWidgetComponent radarWidgetComponent4;*/

    public TestView() {
        setSizeFull();

        /*RadarWidget radarWidget = new RadarWidget("Radar A1 - 8", "a", "b", "c");

        radarWidgetComponent = new RadarWidgetComponent(radarWidget);
        radarWidgetComponent.setHeight(50, Unit.PERCENTAGE);
        radarWidgetComponent.setWidth(25, Unit.PERCENTAGE);

        RadarWidget radarWidget2 = new RadarWidget("Radar IC19 - 6D", "a", "b", "c");

        radarWidgetComponent2 = new RadarWidgetComponent(radarWidget2);
        radarWidgetComponent2.setHeight(50, Unit.PERCENTAGE);
        radarWidgetComponent2.setWidth(25, Unit.PERCENTAGE);

        RadarWidget radarWidget3 = new RadarWidget("Radar IC19 - 7D", "a", "b", "c");

        radarWidgetComponent3 = new RadarWidgetComponent(radarWidget3);
        radarWidgetComponent3.setHeight(50, Unit.PERCENTAGE);
        radarWidgetComponent3.setWidth(25, Unit.PERCENTAGE);

        RadarWidget radarWidget4 = new RadarWidget("Radar A1 - 12", "a", "b", "c");

        radarWidgetComponent4 = new RadarWidgetComponent(radarWidget4);
        radarWidgetComponent4.setHeight(50, Unit.PERCENTAGE);
        radarWidgetComponent4.setWidth(25, Unit.PERCENTAGE);
        add(radarWidgetComponent, radarWidgetComponent2, radarWidgetComponent3, radarWidgetComponent4);

        radarWidgetComponent.setWarning(0);
        radarWidgetComponent2.setWarning(1);
        radarWidgetComponent3.setWarning(2);
        radarWidgetComponent4.setWarning(3);*/
    }
}
