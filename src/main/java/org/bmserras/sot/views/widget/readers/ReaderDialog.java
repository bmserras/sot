package org.bmserras.sot.views.widget.readers;

import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.select.Select;
import org.bmserras.sot.data.domain.readers.ReaderType;
import org.bmserras.sot.events.widget.reader.ReaderSaveEvent;
import org.bmserras.sot.views.components.CustomDialog;
import org.bmserras.sot.views.widget.readers.gauge.GaugeLayout;
import org.bmserras.sot.views.widget.readers.rotextfield.ROTextFieldLayout;
import org.bmserras.sot.views.widget.readers.solidgauge.SolidGaugeLayout;

import java.util.Optional;

public class ReaderDialog extends CustomDialog {

    private final Select<ReaderType> type = new Select<>();
    private final Div div = new Div();
    private ReaderLayout readerLayout;

    public ReaderDialog() {
        super("Configure reader", 60, 80);

        type.setItemLabelGenerator(ReaderType::getType);
        type.setItems(ReaderType.values());
        type.addValueChangeListener(event -> {
            switch (event.getValue()) {
                case GAUGE -> readerLayout = new GaugeLayout();
                case SOLID_GAUGE -> readerLayout = new SolidGaugeLayout();
                case RO_TEXT_FIELD -> readerLayout = new ROTextFieldLayout();
            }
            div.removeAll();
            div.add(readerLayout);
        });
        type.setValue(ReaderType.GAUGE);

        addToHeader(new Span("Type"), type);

        addToContent(div);

        addSaveClickListener(click -> {
            fireEvent(
                    new ReaderSaveEvent(this, Optional.of(readerLayout.getReader()))
            );
        });

        setDeleteButtonVisible(false);
    }

    public void addSaveListener(ComponentEventListener<ReaderSaveEvent> listener) {
        addListener(ReaderSaveEvent.class, listener);
    }
}
