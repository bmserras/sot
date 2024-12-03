package org.bmserras.sot.views.widget.writers.textfield;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import org.bmserras.sot.data.domain.writers.TextFieldData;

public class TextFieldComponent extends Div {

    private final TextField textField = new TextField();

    private final TextFieldData textFieldData;

    public TextFieldComponent() {
        this(new TextFieldData(
                "Default text field",
                "Placeholder",
                "Small",
                "Left",
                "Suffix"
        ));
    }

    public TextFieldComponent(TextFieldData textFieldData) {
        this.textFieldData = textFieldData;
        setLabel(textFieldData.getName());
        setPlaceholder(textFieldData.getPlaceholder());
        setSize(textFieldData.getSize());
        setAlignment(textFieldData.getAlignment());
        setSuffix(textFieldData.getSuffix());
        add(textField);
    }

    public void setLabel(String label) {
        textFieldData.setName(label);
        textField.setLabel(label);
    }

    public void setPlaceholder(String placeholder) {
        textFieldData.setPlaceholder(placeholder);
        textField.setPlaceholder(placeholder);
    }

    public void setSize(String size) {
        textFieldData.setSize(size);
        textField.removeThemeVariants(TextFieldVariant.LUMO_SMALL);
        if (size.equals("Small")) {
            textField.addThemeVariants(TextFieldVariant.LUMO_SMALL);
        }
    }

    public void setAlignment(String value) {
        textFieldData.setAlignment(value);
        textField.removeThemeVariants(TextFieldVariant.LUMO_ALIGN_CENTER, TextFieldVariant.LUMO_ALIGN_RIGHT);
        switch (value) {
            case "Center" -> textField.addThemeVariants(TextFieldVariant.LUMO_ALIGN_CENTER);
            case "Right" -> textField.addThemeVariants(TextFieldVariant.LUMO_ALIGN_RIGHT);
        }
    }

    public void setSuffix(String value) {
        textFieldData.setSuffix(value);
        textField.setSuffixComponent(new Span(value));
    }

    public void setOutlineColor(String value) {
        System.out.println(value);
    }

    public TextFieldData getTextFieldData() {
        return textFieldData;
    }
}
