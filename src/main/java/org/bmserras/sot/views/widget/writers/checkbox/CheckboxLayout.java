package org.bmserras.sot.views.widget.writers.checkbox;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.bmserras.sot.data.domain.writers.Writer;
import org.bmserras.sot.views.widget.writers.WriterLayout;

public class CheckboxLayout extends WriterLayout {

    CheckboxComponent checkboxComponent = new CheckboxComponent();

    public CheckboxLayout() {
        setSizeFull();

        CheckboxContainer checkboxContainer = new CheckboxContainer(checkboxComponent);
        CheckboxForm checkboxForm = new CheckboxForm(checkboxComponent);

        Div div = new Div(checkboxContainer);
        setAlignSelf(Alignment.START, div);
        expand();
        add(new Div(checkboxForm), new VerticalLayout(new H4("Preview"), div));
    }

    @Override
    public Writer getWriter() {
        return checkboxComponent.getCheckboxData();
    }

}
