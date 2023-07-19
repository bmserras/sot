package org.bmserras.sot.views.widget;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import org.bmserras.sot.data.entity.widget.RadarWidget;
import org.bmserras.sot.data.entity.widget.Widget;

import java.util.Arrays;
import java.util.List;

public class RadarWidgetForm extends WidgetForm {

    private TextField ipAddress;
    private NumberField port;
    private NumberField latitude;
    private NumberField longitude;

    public RadarWidgetForm() {
    }

    @Override
    public List<Component> createOtherFields() {
        ipAddress = new TextField("IP");
        port = new NumberField("Port");
        latitude = new NumberField("Latitude");
        longitude = new NumberField("Longitude");
        return Arrays.asList(ipAddress, port, latitude, longitude);
    }

    @Override
    public void bindOtherFields(Binder<Widget> binder) {
        binder.bind(ipAddress,
                widget -> convert(widget).getIpAddress(),
                (widget, ipAddress) -> convert(widget).setIpAddress(ipAddress)
        );
        binder.bind(port,
                widget -> Double.parseDouble("" + convert(widget).getPort()),
                (widget, port) -> convert(widget).setPort(port.intValue())
        );
        binder.bind(latitude,
                widget -> convert(widget).getLatitude(),
                (widget, latitude) -> convert(widget).setLatitude(latitude)
        );
        binder.bind(longitude,
                widget -> convert(widget).getLongitude(),
                (widget, longitude) -> convert(widget).setLongitude(longitude)
        );
    }

    @Override
    public RadarWidget convert(Widget widget) {
        return (RadarWidget) widget;
    }

}
