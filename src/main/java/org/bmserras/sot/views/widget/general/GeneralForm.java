package org.bmserras.sot.views.widget.general;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import org.bmserras.sot.data.domain.Widget;
import org.bmserras.sot.data.domain.readers.Gauge;

public class GeneralForm extends VerticalLayout {

    private final NumberField identifier = new NumberField("Identifier");
    private TextField name = new TextField("Name");
   
    private Binder<Widget> binder = new Binder<>(Widget.class);

    public GeneralForm() {
        this(new Widget());
    }

    public GeneralForm(Widget widget) {
        this.setPadding(false);
        this.setSpacing(false);

        binder.bind(identifier, w -> (double) w.getId(), null);
        binder.bind(name, Widget::getName, Widget::setName);

        setWidget(widget);

        add(identifier, name);
    }

    public void setWidget(Widget widget) {
        binder.setBean(widget);
    }

    public Widget getWidget() {
        return binder.getBean();
    }
}
