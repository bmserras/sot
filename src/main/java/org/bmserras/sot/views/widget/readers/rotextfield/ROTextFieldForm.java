package org.bmserras.sot.views.widget.readers.rotextfield;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;

public class ROTextFieldForm extends Scroller {

    public ROTextFieldForm(ROTextFieldComponent roTextFieldComponent) {

        setScrollDirection(ScrollDirection.VERTICAL);
        setHeight("800px");

        getStyle().set("margin", "var(--lumo-space-xs)");

        TextField label = new TextField("Label");
        label.addValueChangeListener(e -> roTextFieldComponent.setLabel(e.getValue()));
        label.setValue("RO Text Field");

        VerticalLayout customization = new VerticalLayout(new H4("Customization"), label);
        customization.setSpacing(false);

        IntegerField minStatus = new IntegerField("Minimum value");
        IntegerField maxStatus = new IntegerField("Maximum value");
        IntegerField marginStatus = new IntegerField("Margin");
        Checkbox propagateStatus = new Checkbox("Propagate");
        VerticalLayout status = new VerticalLayout(new H4("Status"), minStatus, maxStatus, marginStatus, propagateStatus);
        status.setSpacing(false);

        setContent(new HorizontalLayout(customization, status));

    }
}
