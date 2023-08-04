package org.bmserras.sot.views;

import com.vaadin.flow.component.Svg;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import org.bmserras.sot.SVGWrapper;
import org.bmserras.sot.views.layout.MainLayout;

@PageTitle("Test View")
@Route(value = "test", layout = MainLayout.class)
@PermitAll
public class TestView extends HorizontalLayout {

    /*private final RadarWidgetComponent radarWidgetComponent;
    private final RadarWidgetComponent radarWidgetComponent2;
    private final RadarWidgetComponent radarWidgetComponent3;
    private final RadarWidgetComponent radarWidgetComponent4;*/

    public TestView() {
        setSizeFull();

        setMargin(true);

        // e. g. load svg code from file, classpath, ...
        SVGWrapper styleSvg = new SVGWrapper(
                "<svg height=\"100\" width=\"100\">\n" +
                        "  <circle cx=\"50\" cy=\"50\" r=\"40\" stroke=\"black\" stroke-width=\"3\" fill=\"red\" />\n" +
                        "  Sorry, your browser does not support inline SVG.  \n" +
                        "</svg> "
        );

        // class 'styled-svg' overrides stroke and fill, see showsvg.css
        styleSvg.addClassName("styled-svg");
        styleSvg.getStyle().set("stroke", "red").set("fill", "green").set("width", "20").set("height", "20");

        add(styleSvg);

        Svg svg = new Svg("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                "<!-- Created with Inkscape (http://www.inkscape.org/) -->\n" +
                "\n" +
                "<svg\n" +
                "   width=\"100mm\"\n" +
                "   height=\"160mm\"\n" +
                "   viewBox=\"0 0 100 160\"\n" +
                "   version=\"1.1\"\n" +
                "   id=\"svg659\"\n" +
                "   xmlns=\"http://www.w3.org/2000/svg\"\n" +
                "   xmlns:svg=\"http://www.w3.org/2000/svg\">\n" +
                "  <g\n" +
                "     id=\"Layer1\"\n" +
                "     transform=\"translate(-60.459947,-64.118949)\">\n" +
                "    <g\n" +
                "       id=\"Radar\"\n" +
                "       transform=\"matrix(0.3972782,0,0,0.39506173,-291.17462,-293.64583)\">\n" +
                "      <g\n" +
                "         id=\"Cinemometer\"\n" +
                "         transform=\"translate(120.46553,35.592088)\">\n" +
                "        <path\n" +
                "           class=\"cls-11\"\n" +
                "           d=\"M 969,1067.72 H 806.47 V 965.09 L 846.8,965 v -15.87 h 81.81 V 965 l 40.4,0.11 z M 815.2,1059 h 145.08 v -85.21 l -40.39,-0.11 v -15.83 h -64.37 v 15.83 l -40.32,0.11 z\"\n" +
                "           id=\"camera\"\n" +
                "           style=\"fill:#f98e00\" />\n" +
                "        <path\n" +
                "           class=\"cls-11\"\n" +
                "           d=\"m 889.21,1048.82 a 37.67,37.67 0 1 1 37.67,-37.67 37.71,37.71 0 0 1 -37.67,37.67 z m 0,-66.61 a 28.95,28.95 0 1 0 28.95,28.94 29,29 0 0 0 -28.95,-28.94 z\"\n" +
                "           id=\"lens\"\n" +
                "           style=\"fill:#f98e00\" />\n" +
                "        <path\n" +
                "           class=\"cls-11\"\n" +
                "           d=\"m 886,1031.91 v -8.72 a 14.64,14.64 0 0 0 14.62,-14.63 h 8.73 A 23.38,23.38 0 0 1 886,1031.91 Z\"\n" +
                "           id=\"reflection\"\n" +
                "           style=\"fill:#f98e00\" />\n" +
                "      </g>\n" +
                "      <g\n" +
                "         id=\"Cabin\"\n" +
                "         transform=\"translate(120.46553,35.592088)\"\n" +
                "         style=\"display:inline\">\n" +
                "        <path\n" +
                "           class=\"cls-10\"\n" +
                "           d=\"M 1002.21,1275 H 778.79 a 12.3,12.3 0 0 1 -12.28,-12.29 V 918.42 a 12.3,12.3 0 0 1 12.28,-12.28 h 223.42 a 12.3,12.3 0 0 1 12.28,12.28 v 344.29 A 12.3,12.3 0 0 1 1002.21,1275 Z M 778.79,917.35 a 1.07,1.07 0 0 0 -1.07,1.07 v 344.29 a 1.06,1.06 0 0 0 1.07,1.07 h 223.42 a 1.06,1.06 0 0 0 1.07,-1.07 V 918.42 a 1.07,1.07 0 0 0 -1.07,-1.07 z\"\n" +
                "           id=\"door\"\n" +
                "           style=\"fill:#131e56\" />\n" +
                "        <path\n" +
                "           class=\"cls-10\"\n" +
                "           d=\"M 1009.93,917.35 H 771.07 a 6.41,6.41 0 0 1 -4.63,-10.86 L 801.75,870 h 177.5 l 35.31,36.49 a 6.41,6.41 0 0 1 -4.63,10.86 z M 782.39,906.14 H 998.61 L 974.5,881.22 h -168 z\"\n" +
                "           id=\"head\"\n" +
                "           style=\"fill:#131e56\" />\n" +
                "        <rect\n" +
                "           class=\"cls-10\"\n" +
                "           x=\"970.88\"\n" +
                "           y=\"1119.23\"\n" +
                "           width=\"17.450001\"\n" +
                "           height=\"79.75\"\n" +
                "           rx=\"7\"\n" +
                "           id=\"handle\"\n" +
                "           style=\"fill:#131e56\" />\n" +
                "      </g>\n" +
                "    </g>\n" +
                "  </g>\n" +
                "</svg>");

        svg.getStyle().set("width", "100px");
        //add(svg);

        Image image = new Image("icons/fan-blades-icon.svg", "fan");
        image.setWidth("200px");
        add(image);

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
