package org.bmserras.sot.views.widget.writers.textfield;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import org.vaadin.addons.tatu.ColorPicker;
import org.vaadin.addons.tatu.ColorPickerVariant;

import java.util.Arrays;

public class TextFieldForm extends Scroller {

    public TextFieldForm(TextFieldComponent textFieldComponent) {

        setScrollDirection(ScrollDirection.VERTICAL);
        setHeight("800px");

        getStyle().set("margin", "var(--lumo-space-xs)");
        getStyle().set("margin", "var(--lumo-space-xs)");

        TextField label = new TextField("Label");
        label.addValueChangeListener(e -> textFieldComponent.setLabel(e.getValue()));
        label.setValue("Text Field");

        TextField placeholder = new TextField("Placeholder");
        placeholder.addValueChangeListener(e -> textFieldComponent.setPlaceholder(e.getValue()));
        placeholder.setValue("Placeholder");

        TextField suffix = new TextField("Suffix");
        suffix.addValueChangeListener(e -> textFieldComponent.setSuffix(e.getValue()));

        ComboBox<String> align = new ComboBox<>("Alignment");
        align.addValueChangeListener(e -> textFieldComponent.setAlignment(e.getValue()));
        align.setItems("Left", "Center", "Right");
        align.setValue("Left");

        ComboBox<String> size = new ComboBox<>("Size");
        size.addValueChangeListener(e -> textFieldComponent.setSize(e.getValue()));
        size.setItems("Small", "Normal");
        size.setValue("Normal");

        ColorPicker outlineColor = new ColorPicker();
        outlineColor.setPresets(Arrays.asList(new ColorPicker.ColorPreset("#ffffff", "White")));
        outlineColor.addThemeVariants(ColorPickerVariant.COMPACT);
        outlineColor.addValueChangeListener(e -> textFieldComponent.setOutlineColor(e.getValue()));
        outlineColor.setLabel("Outline color");

        VerticalLayout customization = new VerticalLayout(new H4("Customization"), label, placeholder, suffix, align, size/*, outlineColor*/);
        customization.setSpacing(false);

        setContent(new HorizontalLayout(customization));
    }

}
