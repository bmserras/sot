package org.bmserras.sot;

import org.bmserras.sot.data.domain.Widget;
import org.bmserras.sot.data.domain.WidgetImage;
import org.bmserras.sot.data.domain.WidgetInstance;
import org.bmserras.sot.data.domain.readers.GaugeData;
import org.bmserras.sot.data.domain.readers.ROTextFieldData;
import org.bmserras.sot.data.domain.readers.SolidGaugeData;
import org.bmserras.sot.data.domain.writers.ButtonData;
import org.bmserras.sot.data.domain.writers.ToggleData;
import org.bmserras.sot.icon.CustomIcon;
import org.bmserras.sot.views.widgetinstance.WidgetInstanceCard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MockData {

    public static Widget createCabinA() {
        GaugeData temperature = new GaugeData(
                "Temperature",
                "Celsius",
                0,
                100,
                400,
                400,
                10,
                true,
                "black",
                10,
                false,
                5,
                "dashed"
        );
        ROTextFieldData battery = new ROTextFieldData("Battery", "%", 0, 100, 90);

        ButtonData door = new ButtonData("Open door", "Normal", "Primary", "Success");
        ToggleData fan = new ToggleData("Fan");

        Widget cabin = new Widget(
                1L,
                "Cabin",
                List.of(temperature, battery),
                List.of(door, fan),
                new ArrayList<>(),
                5,
                "dashed",
                new WidgetImage(CustomIcon.CABIN.name(), "custom")
        );
        return cabin;
    }

    public static WidgetInstanceCard createCabinAInstance() {
        WidgetInstance cabinInstance = new WidgetInstance("Cabin A", MockData.createCabinA());
        WidgetInstanceCard widgetInstanceCard = new WidgetInstanceCard(cabinInstance);

        //widgetInstanceCard.getStyle().set("border-width", cabinInstance.getWidget().getBorderWidth() + "px");
        //widgetInstanceCard.getStyle().set("border-style", cabinInstance.getWidget().getBorderStyle());

        return widgetInstanceCard;
    }

    public static Widget createCinemometerA() {
        SolidGaugeData storage = new SolidGaugeData(
                "Storage",
                "GB",
                0,
                512,
                400,
                400,
                5,
                "black"
        );
        ROTextFieldData voltage = new ROTextFieldData("Voltage", "V", 0, 300, 220);

        ButtonData picture = new ButtonData("Take picture", "Normal", "Primary", "Success");
        ToggleData onOff = new ToggleData("On/Off");

        Widget cinemometer = new Widget(
                2L,
                "Cinemometer",
                List.of(storage, voltage),
                List.of(picture, onOff),
                new ArrayList<>(),
                5,
                "dashed",
                new WidgetImage(CustomIcon.CINEMOMETER.name(), "custom")
        );
        return cinemometer;
    }

    public static WidgetInstanceCard createCinAInstance() {
        WidgetInstance cinemometerInstance = new WidgetInstance("Cinemometer A", MockData.createCinemometerA());
        WidgetInstanceCard widgetInstanceCard = new WidgetInstanceCard(cinemometerInstance);

        widgetInstanceCard.getStyle().set("border-width", cinemometerInstance.getWidget().getBorderWidth() + "px");
        widgetInstanceCard.getStyle().set("border-style", cinemometerInstance.getWidget().getBorderStyle());

        return widgetInstanceCard;
    }

    public static Widget createLctA() {
        Widget lct = new Widget(
                3L,
                "LCT",
                new ArrayList<>(),
                new ArrayList<>(),
                Arrays.asList(MockData.createCabinA(), MockData.createCinemometerA()),
                10,
                "solid",
                new WidgetImage(CustomIcon.LCT.name(), "custom")
        );
        return lct;
    }

    public static WidgetInstanceCard createLctAInstance() {
        WidgetInstance lctInstance = new WidgetInstance("LCT A", MockData.createLctA());
        WidgetInstanceCard widgetInstanceCard = new WidgetInstanceCard(lctInstance);

        return widgetInstanceCard;
    }

}
