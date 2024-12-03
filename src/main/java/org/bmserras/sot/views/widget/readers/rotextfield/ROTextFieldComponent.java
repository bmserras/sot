package org.bmserras.sot.views.widget.readers.rotextfield;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.textfield.TextField;
import org.bmserras.sot.data.domain.readers.ROTextFieldData;

public class ROTextFieldComponent extends Div {

    private final TextField textField = new TextField();

    private final ROTextFieldData roTextFieldData;

    public ROTextFieldComponent() {
        this(new ROTextFieldData(
                "RO Text Field",
                "unit",
                0,
                100,
                100
        ));
    }

    public ROTextFieldComponent(ROTextFieldData roTextFieldData) {
        this.roTextFieldData = roTextFieldData;
        textField.setReadOnly(true);
        textField.setLabel(roTextFieldData.getName());
        textField.setValue(String.valueOf(roTextFieldData.getValue()));

        /*getStyle().set("border-style", "solid");
        getStyle().set("border-width", "4px");
        getStyle().set("border-color", "black");
        getStyle().set("margin", "5px");
        getStyle().set("padding", "10px");*/

        add(textField);
    }

    public void setLabel(String value) {
        textField.setLabel(value);
        roTextFieldData.setName(value);
    }

    public ROTextFieldData getROTextFieldData() {
        return roTextFieldData;
    }

    public void setValue(String value) {
        textField.setValue(value);
        roTextFieldData.setValue(Integer.parseInt(value));
    }
}
