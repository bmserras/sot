package org.bmserras.sot.views.widget.readers.rotextfield;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.bmserras.sot.data.domain.readers.Reader;
import org.bmserras.sot.views.widget.readers.ReaderLayout;

public class ROTextFieldLayout extends ReaderLayout {

    ROTextFieldComponent roTextFieldComponent = new ROTextFieldComponent();

    public ROTextFieldLayout() {
        setSizeFull();

        ROTextFieldContainer roTextFieldContainer = new ROTextFieldContainer(roTextFieldComponent);
        ROTextFieldForm roTextFieldForm = new ROTextFieldForm(roTextFieldComponent);

        Div div = new Div(roTextFieldContainer);
        setAlignSelf(Alignment.START, div);
        expand();
        add(new Div(roTextFieldForm), new VerticalLayout(new H4("Preview"), div));
    }

    @Override
    public Reader getReader() {
        return roTextFieldComponent.getROTextFieldData();
    }
}
