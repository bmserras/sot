package org.bmserras.sot.views.widget.writers.button;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class ButtonForm extends Scroller {

    public ButtonForm(ButtonComponent buttonComponent) {

        setScrollDirection(ScrollDirection.VERTICAL);
        setHeight("800px");

        getStyle().set("margin", "var(--lumo-space-xs)");

        TextField label = new TextField("Label");
        label.addValueChangeListener(e -> buttonComponent.setLabel(e.getValue()));
        label.setValue("Button");

        ComboBox<String> mainVariant = new ComboBox<>("Main variant");
        mainVariant.setItems("Primary", "Secondary", "Tertiary");
        mainVariant.addValueChangeListener(e -> buttonComponent.setMainVariant(e.getValue()));
        mainVariant.setValue("Secondary");

        ComboBox<String> secondaryVariant = new ComboBox<>("Secondary variant");
        secondaryVariant.setItems("Success", "Error", "None");
        secondaryVariant.addValueChangeListener(e -> buttonComponent.setSecondaryVariant(e.getValue()));
        secondaryVariant.setValue("None");

        ComboBox<String> size = new ComboBox<>("Size");
        size.addValueChangeListener(e -> buttonComponent.setSize(e.getValue()));
        size.setItems("Small", "Normal", "Large");
        size.setValue("Normal");

        VerticalLayout customization = new VerticalLayout(new H4("Customization"), label, mainVariant, secondaryVariant, size);
        customization.setSpacing(false);

        setContent(new HorizontalLayout(customization));
    }

}
