package org.bmserras.sot.views.widget.writers.textfield;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.bmserras.sot.data.domain.writers.Writer;
import org.bmserras.sot.views.widget.writers.WriterLayout;
import org.bmserras.sot.views.widget.writers.toggle.ToggleComponent;

public class TextFieldLayout extends WriterLayout {

    TextFieldComponent textFieldComponent = new TextFieldComponent();

    public TextFieldLayout() {
        setSizeFull();

        TextFieldContainer textFieldContainer = new TextFieldContainer(textFieldComponent);
        TextFieldForm textFieldForm = new TextFieldForm(textFieldComponent);

        Div div = new Div(textFieldContainer);
        setAlignSelf(Alignment.START, div);
        expand();
        add(new Div(textFieldForm), new VerticalLayout(new H4("Preview"), div));
    }

    @Override
    public Writer getWriter() {
        return textFieldComponent.getTextFieldData();
    }

}
