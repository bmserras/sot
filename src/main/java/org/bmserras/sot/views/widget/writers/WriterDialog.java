package org.bmserras.sot.views.widget.writers;

import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.select.Select;
import org.bmserras.sot.data.domain.writers.WriterType;
import org.bmserras.sot.events.widget.writer.WriterSaveEvent;
import org.bmserras.sot.views.components.CustomDialog;
import org.bmserras.sot.views.widget.writers.button.ButtonLayout;
import org.bmserras.sot.views.widget.writers.checkbox.CheckboxLayout;
import org.bmserras.sot.views.widget.writers.textfield.TextFieldLayout;
import org.bmserras.sot.views.widget.writers.toggle.ToggleLayout;

import java.util.Optional;

public class WriterDialog extends CustomDialog {

    private final Select<WriterType> type = new Select<>();
    private final Div div = new Div();
    private WriterLayout writerLayout;

    public WriterDialog() {
        super("Configure writer", 50, 80);

        type.setItemLabelGenerator(WriterType::getType);
        type.setItems(WriterType.values());
        type.addValueChangeListener(event -> {
            switch (event.getValue()) {
                case BUTTON -> writerLayout = new ButtonLayout();
                case TOGGLE -> writerLayout = new ToggleLayout();
                case TEXT_FIELD -> writerLayout = new TextFieldLayout();
                case CHECKBOX -> writerLayout = new CheckboxLayout();
            }
            div.removeAll();
            div.add(writerLayout);
        });
        type.setValue(WriterType.BUTTON);

        addToHeader(new Span("Type"), type);

        addToContent(div);

        addSaveClickListener(click -> {
            fireEvent(
                    new WriterSaveEvent(this, Optional.of(writerLayout.getWriter()))
            );
        });

        setDeleteButtonVisible(false);
    }

    public void addSaveListener(ComponentEventListener<WriterSaveEvent> listener) {
        addListener(WriterSaveEvent.class, listener);
    }
}
