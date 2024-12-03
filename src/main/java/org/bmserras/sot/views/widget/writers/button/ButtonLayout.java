package org.bmserras.sot.views.widget.writers.button;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.bmserras.sot.data.domain.writers.Writer;
import org.bmserras.sot.views.widget.writers.WriterLayout;

public class ButtonLayout extends WriterLayout {

    ButtonComponent buttonComponent = new ButtonComponent();

    public ButtonLayout() {
        setSizeFull();

        ButtonContainer buttonContainer = new ButtonContainer(buttonComponent);
        ButtonForm buttonForm = new ButtonForm(buttonComponent);

        Div div = new Div(buttonContainer);
        setAlignSelf(Alignment.START, div);
        expand();
        add(new Div(buttonForm), new VerticalLayout(new H4("Preview"), div));
    }

    @Override
    public Writer getWriter() {
        return buttonComponent.getButtonData();
    }

}
