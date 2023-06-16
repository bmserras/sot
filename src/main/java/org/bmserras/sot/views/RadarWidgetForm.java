package org.bmserras.sot.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import org.bmserras.sot.data.entity.RadarWidget;
import org.bmserras.sot.data.entity.Widget;

import java.util.Arrays;
import java.util.List;

public class RadarWidgetForm extends WidgetForm {

    private TextField ipAddress;
    private TextField latitude;
    private TextField longitude;

    public RadarWidgetForm() {
    }

    @Override
    public List<Component> createOtherFields() {
        ipAddress = new TextField("IP Address");
        latitude = new TextField("Latitude");
        longitude = new TextField("Longitude");
        return Arrays.asList(ipAddress, latitude, longitude);
    }

    @Override
    public void bindOtherFields(Binder<Widget> binder) {
        binder.bind(ipAddress,
                widget -> convert(widget).getIpAddress(),
                (widget, ipAddress) -> convert(widget).setIpAddress(ipAddress)
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
