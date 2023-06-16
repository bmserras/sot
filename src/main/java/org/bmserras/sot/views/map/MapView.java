package org.bmserras.sot.views.map;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.map.Map;
import com.vaadin.flow.component.map.configuration.Coordinate;
import com.vaadin.flow.component.map.configuration.feature.MarkerFeature;
import com.vaadin.flow.component.map.configuration.style.Icon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.bmserras.sot.components.CabinWidget;
import org.bmserras.sot.views.MainLayout;
import org.bmserras.sot.views.helloworld.HelloWorldView;
import org.vaadin.lineawesome.LineAwesomeIcon;

@PageTitle("Map")
@Route(value = "map", layout = MainLayout.class)
public class MapView extends VerticalLayout {

    private Map map = new Map();
    private Button button = new Button("Click");

    public MapView() {

        Coordinate coordinate = new Coordinate(-8.029486, 39.727981);
        map.setCenter(coordinate);
        map.setZoom(6);

        Icon.Options redMarker = new Icon.Options();
        redMarker.setSrc("icons/map-marker-solid-red.svg");
        Icon redMarkerIcon = new Icon(redMarker);

        Icon.Options greenMarker = new Icon.Options();
        greenMarker.setSrc("icons/map-marker-solid-green.svg");
        Icon greenMarkerIcon = new Icon(greenMarker);

        Coordinate lisbonCoordinates = new Coordinate(-9.142685, 38.736946);
        Coordinate portoCoordinates = new Coordinate(-8.61099, 41.14961);

        MarkerFeature lisbon = new MarkerFeature(lisbonCoordinates, greenMarkerIcon);
        map.getFeatureLayer().addFeature(lisbon);

        MarkerFeature porto = new MarkerFeature(portoCoordinates, redMarkerIcon);
        map.getFeatureLayer().addFeature(porto);

        map.addFeatureClickListener(e -> {
            MarkerFeature feature = (MarkerFeature) e.getFeature();
            Coordinate coordinates = feature.getCoordinates();
            String info = "";
            info += String.format("Coordinates = { x: %s, y: %s }",
                    coordinates.getX(), coordinates.getY());
            System.out.println(info);

            Dialog dialog = new Dialog();
            dialog.add(new CabinWidget());
            add(dialog);
            dialog.open();
        });

        button.addClickListener(e -> {
            porto.setIcon(greenMarkerIcon);
        });

        add(map, button);
    }
}
