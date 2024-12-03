package org.bmserras.sot.views.widget.writers.toggle;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.bmserras.sot.data.domain.writers.Writer;
import org.bmserras.sot.views.widget.writers.WriterLayout;

public class ToggleLayout extends WriterLayout {

    ToggleComponent toggleComponent = new ToggleComponent();

    public ToggleLayout() {
        setSizeFull();

        ToggleContainer toggleContainer = new ToggleContainer(toggleComponent);
        ToggleForm toggleForm = new ToggleForm(toggleComponent);

        Div div = new Div(toggleContainer);
        setAlignSelf(Alignment.START, div);
        expand();
        add(new Div(toggleForm), new VerticalLayout(new H4("Preview"), div));
    }

    @Override
    public Writer getWriter() {
        return toggleComponent.getToggleData();
    }

}
