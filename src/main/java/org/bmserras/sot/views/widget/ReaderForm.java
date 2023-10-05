package org.bmserras.sot.views.widget;

import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import org.bmserras.sot.data.domain.ValueReader;
import org.bmserras.sot.data.domain.ValueReaderType;
import org.bmserras.sot.events.widget.ReaderMaxEvent;
import org.bmserras.sot.events.widget.ReaderMinEvent;
import org.bmserras.sot.events.widget.ReaderNameEvent;
import org.bmserras.sot.events.widget.ReaderTypeEvent;

import java.util.Optional;

public class ReaderForm extends VerticalLayout {

    private final NumberField identifier = new NumberField("Identifier");
    private Select<ValueReaderType> type = new Select<>();
    private TextField name = new TextField("Name");
    private IntegerField min = new IntegerField("Minimum value");
    private IntegerField max = new IntegerField("Maximum value");

    private final Binder<ValueReader> binder = new Binder<>(ValueReader.class);

    public ReaderForm() {

        this.setPadding(false);
        this.setSpacing(false);

        binder.bind(identifier, reader -> (double) reader.getId(), null);
        binder.bind(name, ValueReader::getName, ValueReader::setName);
        binder.bind(min, ValueReader::getMin, ValueReader::setMin);
        binder.bind(max, ValueReader::getMax, ValueReader::setMax);
        binder.bind(type, ValueReader::getType, ValueReader::setType);

        type.setLabel("Type");
        type.setItemLabelGenerator(ValueReaderType::getType);
        type.setItems(ValueReaderType.values());
        type.addValueChangeListener(event -> {
            fireEvent(new ReaderTypeEvent(this, Optional.of(binder.getBean())));
        });

        type.addValueChangeListener(event -> {
            fireEvent(new ReaderTypeEvent(this, Optional.of(binder.getBean())));
        });

        name.addValueChangeListener(event -> {
            fireEvent(new ReaderNameEvent(this, Optional.of(binder.getBean())));
        });

        min.addValueChangeListener(event -> {
            fireEvent(new ReaderMinEvent(this, Optional.of(binder.getBean())));
        });

        max.addValueChangeListener(event -> {
            fireEvent(new ReaderMaxEvent(this, Optional.of(binder.getBean())));
        });

        add(identifier, type, name, min, max);
    }

    public void setReader(ValueReader reader) {
        binder.setBean(reader);
    }

    public void addReaderTypeListener(ComponentEventListener<ReaderTypeEvent> listener) {
        addListener(ReaderTypeEvent.class, listener);
    }

    public void addReaderNameListener(ComponentEventListener<ReaderNameEvent> listener) {
        addListener(ReaderNameEvent.class, listener);
    }

    public void addReaderMinListener(ComponentEventListener<ReaderMinEvent> listener) {
        addListener(ReaderMinEvent.class, listener);
    }

    public void addReaderMaxListener(ComponentEventListener<ReaderMaxEvent> listener) {
        addListener(ReaderMaxEvent.class, listener);
    }
}
