package org.bmserras.sot.views.widget.writers.checkbox;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class CheckboxForm extends Scroller {

    public CheckboxForm(CheckboxComponent checkboxComponent) {

        setScrollDirection(ScrollDirection.VERTICAL);
        setHeight("800px");

        getStyle().set("margin", "var(--lumo-space-xs)");
        getStyle().set("margin", "var(--lumo-space-xs)");

        TextField label = new TextField("Label");
        label.addValueChangeListener(e -> checkboxComponent.setLabel(e.getValue()));
        label.setValue("Checkbox");

        TextField option = new TextField("Option");
        option.setValue("Value");
        option.addValueChangeListener(e -> checkboxComponent.setOption(e.getValue()));

        Button add = new Button("Add new option");
        add.addClickListener(e -> {
            checkboxComponent.addOption("Value");
            option.setValue("Value");
        });

        ComboBox<String> orientation = new ComboBox<>("Orientation", "Vertical", "Horizontal");
        orientation.addValueChangeListener(e -> checkboxComponent.setOrientation(e.getValue()));
        orientation.setValue("Vertical");

        VerticalLayout customization = new VerticalLayout(new H4("Customization"), label, orientation, option, add);
        customization.setSpacing(false);

        setContent(new HorizontalLayout(customization));
    }

}
