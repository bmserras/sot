package org.bmserras.sot.views.widget.readers;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import org.bmserras.sot.data.domain.readers.ValueReaderType;
import org.bmserras.sot.views.widget.readers.gauge.GaugeLayout;
import org.bmserras.sot.views.widget.readers.solidgauge.SolidGaugeLayout;

public class ReadersTab extends VerticalLayout {

    private Select<ValueReaderType> type = new Select<>();
    private Div div = new Div();

    public ReadersTab() {

        type.setLabel("Type");
        type.setItemLabelGenerator(ValueReaderType::getType);
        type.setItems(ValueReaderType.values());
        type.addValueChangeListener(event -> {
            div.removeAll();
            switch (event.getValue()) {
                case GAUGE -> div.add(new GaugeLayout());
                case SOLID_GAUGE -> div.add(new SolidGaugeLayout());
            }
        });

        add(type, div);
    }

}
