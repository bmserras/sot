package org.bmserras.sot.views.widget.readers;

import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.select.Select;
import org.bmserras.sot.data.domain.readers.ValueReaderType;
import org.bmserras.sot.events.widget.reader.ReaderSaveEvent;
import org.bmserras.sot.views.components.CustomDialog;
import org.bmserras.sot.views.widget.readers.gauge.GaugeLayout;
import org.bmserras.sot.views.widget.readers.solidgauge.SolidGaugeLayout;

import java.util.Optional;

public class ReaderDialog extends CustomDialog {

    private final Select<ValueReaderType> type = new Select<>();
    private final Div div = new Div();
    private ValueReaderLayout valueReaderLayout;

    public ReaderDialog() {
        super("Configure reader", 50, 80);

        type.setItemLabelGenerator(ValueReaderType::getType);
        type.setItems(ValueReaderType.values());
        type.addValueChangeListener(event -> {
            switch (event.getValue()) {
                case GAUGE -> valueReaderLayout = new GaugeLayout();
                case SOLID_GAUGE -> valueReaderLayout = new SolidGaugeLayout();
            }
            div.removeAll();
            div.add(valueReaderLayout);
        });
        type.setValue(ValueReaderType.GAUGE);

        addToHeader(new Span("Type"), type);

        addToContent(div);

        addSaveClickListener(click -> {
            fireEvent(
                    new ReaderSaveEvent(this, Optional.of(valueReaderLayout.getValueReader()))
            );
        });

        setDeleteButtonVisible(false);
    }

    public void addSaveListener(ComponentEventListener<ReaderSaveEvent> listener) {
        addListener(ReaderSaveEvent.class, listener);
    }
}
