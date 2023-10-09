package org.bmserras.sot.views.widget.readers;

import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.bmserras.sot.data.domain.readers.ValueReader;
import org.bmserras.sot.events.widget.reader.ReaderSaveEvent;
import org.bmserras.sot.views.widget.readers.card.ReaderCardLayout;

import java.util.ArrayList;
import java.util.List;

public class ReadersTab extends VerticalLayout {

    private final ReaderCardLayout readerCardLayout = new ReaderCardLayout();

    public ReadersTab() {

        readerCardLayout.setItems(new ArrayList<>());
        add(readerCardLayout);
    }

    public List<ValueReader> getItems() {
        return readerCardLayout.getItems();
    }

    public void setItems(List<ValueReader> readers) {
        readerCardLayout.setItems(readers);
    }

    public void addSaveListener(ComponentEventListener<ReaderSaveEvent> listener) {
        readerCardLayout.addSaveListener(listener);
    }

}
