package org.bmserras.sot.views.widget;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import org.bmserras.sot.data.domain.ValueReader;
import org.bmserras.sot.data.domain.ValueReaderType;

public class ReadersTab extends HorizontalLayout {

    private ReaderForm readerForm = new ReaderForm();
    private Preview preview = new Preview();
    private GaugeWidget gaugeWidget;

    public ReadersTab() {

        ValueReader valueReader = new ValueReader();
        readerForm.setReader(valueReader);

        final IReader[] iReader = {new GaugeWidget(
                valueReader.getName(),
                getRandomNumber(valueReader.getMin(), valueReader.getMax()),
                valueReader.getMin(),
                valueReader.getMax(),
                300,
                300
        )};
        preview.add(iReader[0].getComponent());

        readerForm.addReaderTypeListener(event -> event.getReader().ifPresent(reader -> {
            preview.removeAll();
            if (reader.getType() == ValueReaderType.GAUGE) {
                iReader[0] = new GaugeWidget(
                        valueReader.getName(),
                        getRandomNumber(valueReader.getMin(), valueReader.getMax()),
                        valueReader.getMin(),
                        valueReader.getMax(),
                        300,
                        300
                );
            } else if (reader.getType() == ValueReaderType.SOLID_GAUGE) {
                iReader[0] = new SolidGaugeWidget(
                        valueReader.getName(),
                        getRandomNumber(valueReader.getMin(), valueReader.getMax()),
                        valueReader.getMin(),
                        valueReader.getMax(),
                        300,
                        300
                );
            } else if (reader.getType() == ValueReaderType.TEXT_FIELD) {
                iReader[0] = new TextFieldWidget();
            } else if (reader.getType() == ValueReaderType.NUMBER_FIELD) {
                iReader[0] = new NumberFieldWidget();
            }
            preview.add(iReader[0].getComponent());
        }));
        readerForm.addReaderNameListener(event -> event.getReader().ifPresent(reader -> {
            iReader[0].setName(reader.getName());
        }));
        readerForm.addReaderMinListener(event -> event.getReader().ifPresent(reader -> {
            iReader[0].setMin(reader.getMin());
            iReader[0].setValue(getRandomNumber(reader.getMin(), reader.getMax()));
        }));
        readerForm.addReaderMaxListener(event -> event.getReader().ifPresent(reader -> {
            iReader[0].setMax(reader.getMax());
            iReader[0].setValue(getRandomNumber(reader.getMin(), reader.getMax()));
        }));
        add(readerForm, preview);
    }

    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

}
