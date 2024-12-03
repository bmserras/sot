package org.bmserras.sot.views.widget.writers.checkbox;

import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.checkbox.CheckboxGroupVariant;
import com.vaadin.flow.component.html.Div;
import org.bmserras.sot.data.domain.writers.CheckboxData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CheckboxComponent extends Div {

    private final CheckboxGroup<String> checkboxGroup = new CheckboxGroup<>();

    private final List<String> options = new ArrayList<>();

    private final CheckboxData checkboxData;

    public CheckboxComponent() {
        this(new CheckboxData(
                "Checkbox",
                Arrays.asList("Value"),
                "Vertical"
        ));
    }

    public CheckboxComponent(CheckboxData checkboxData) {
        this.checkboxData = checkboxData;
        checkboxData.getOptions().forEach(this::addOption);
        setOrientation(checkboxData.getOrientation());
        add(checkboxGroup);
    }

    public void setLabel(String label) {
        checkboxData.setName(label);
        checkboxGroup.setLabel(label);
    }

    public void setOption(String value) {
        options.set(options.size() - 1, value);
        checkboxGroup.setItems(options);
        checkboxData.setOptions(options);
    }

    public void addOption(String value) {
        options.add(value);
        checkboxGroup.setItems(options);
        checkboxData.setOptions(options);
    }

    public void setOrientation(String value) {
        checkboxGroup.removeThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);
        if (value.equals("Vertical")) {
            checkboxGroup.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);
        }
        checkboxData.setOrientation(value);
    }

    public CheckboxData getCheckboxData() {
        return checkboxData;
    }
}
