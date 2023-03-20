package org.bmserras.sot.views.helloworld;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.*;
import org.bmserras.sot.components.MyElement;
import org.bmserras.sot.components.MyTextField;
import org.bmserras.sot.views.MainLayout;

import java.util.List;
import java.util.Map;

@PageTitle("Hello World")
@Route(value = "hello", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class HelloWorldView extends HorizontalLayout implements HasUrlParameter<String> {

    private MyElement myElement;

    private MyTextField myTextField;

    public HelloWorldView() {

        myElement = new MyElement();
        add(myElement);

        myTextField = new MyTextField();
        add(myTextField);
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, @OptionalParameter String parameter) {

        Location location = beforeEvent.getLocation();
        QueryParameters queryParameters = location.getQueryParameters();

        Map<String, List<String>> parametersMap = queryParameters.getParameters();

        parametersMap.forEach((key, values) -> {
            System.out.print(key + " = " + values);
        });
    }

}
