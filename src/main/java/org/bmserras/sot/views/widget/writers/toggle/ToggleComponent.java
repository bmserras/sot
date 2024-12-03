package org.bmserras.sot.views.widget.writers.toggle;

import com.vaadin.componentfactory.ToggleButton;
import com.vaadin.flow.component.html.Div;
import org.bmserras.sot.data.domain.writers.ToggleData;

public class ToggleComponent extends Div {

    private final ToggleButton toggle = new ToggleButton();

    private final ToggleData toggleData;

    public ToggleComponent() {
        this(new ToggleData(
                "Default toggle"
        ));
    }

    public ToggleComponent(ToggleData toggleData) {
        this.toggleData = toggleData;
        setLabel(toggleData.getName());
        add(toggle);
    }

    public void setLabel(String label) {
        toggleData.setName(label);
        toggle.setLabel(label);
    }

    public ToggleData getToggleData() {
        return toggleData;
    }
}
