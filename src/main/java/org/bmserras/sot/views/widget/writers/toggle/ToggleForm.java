package org.bmserras.sot.views.widget.writers.toggle;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class ToggleForm extends Scroller {

    public ToggleForm(ToggleComponent toggleComponent) {

        setScrollDirection(ScrollDirection.VERTICAL);
        setHeight("800px");

        getStyle().set("margin", "var(--lumo-space-xs)");
        getStyle().set("margin", "var(--lumo-space-xs)");

        TextField label = new TextField("Label");
        label.addValueChangeListener(e -> toggleComponent.setLabel(e.getValue()));
        label.setValue("Toggle");

        VerticalLayout customization = new VerticalLayout(new H4("Customization"), label);
        customization.setSpacing(false);

        setContent(new HorizontalLayout(customization));
    }

}
