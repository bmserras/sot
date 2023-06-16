package org.bmserras.sot.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import org.bmserras.sot.data.entity.VideoCameraWidget;
import org.bmserras.sot.data.entity.Widget;

import java.util.Arrays;
import java.util.List;

public class VideoCameraWidgetForm extends WidgetForm {

    private TextField username;
    private TextField password;

    public VideoCameraWidgetForm() {
    }

    @Override
    public List<Component> createOtherFields() {
        username = new TextField("Username");
        password = new TextField("Password");
        return Arrays.asList(username, password);
    }

    @Override
    public void bindOtherFields(Binder<Widget> binder) {
        binder.bind(username,
                widget -> convert(widget).getUsername(),
                (widget, username) -> convert(widget).setUsername(username)
        );
        binder.bind(password,
                widget -> convert(widget).getPassword(),
                (widget, password) -> convert(widget).setPassword(password)
        );
    }

    @Override
    public VideoCameraWidget convert(Widget widget) {
        return (VideoCameraWidget) widget;
    }

}
