package org.bmserras.sot.views.widget.components;

import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.component.tabs.TabSheetVariant;
import org.bmserras.sot.data.domain.Widget;
import org.bmserras.sot.data.service.WidgetService;
import org.bmserras.sot.events.widget.WidgetCloseEvent;
import org.bmserras.sot.events.widget.WidgetSaveEvent;
import org.bmserras.sot.views.components.CustomDialog;
import org.bmserras.sot.views.widget.general.GeneralForm;
import org.bmserras.sot.views.widget.inner.card.InnerCardLayout;
import org.bmserras.sot.views.widget.readers.card.ReaderCardLayout;
import org.bmserras.sot.views.widget.writers.card.WriterCardLayout;

import java.util.ArrayList;
import java.util.Optional;

public class WidgetForm extends CustomDialog {

    private final TabSheet tabSheet = new TabSheet();

    private final GeneralForm generalForm = new GeneralForm();
    private final ReaderCardLayout readerCardLayout = new ReaderCardLayout();
    private final WriterCardLayout writerCardLayout = new WriterCardLayout();
    private final InnerCardLayout innerCardLayout;

    private Widget widget;

    public WidgetForm(WidgetService widgetService) {
        super("Configure widget", 65, 85);

        tabSheet.setSizeFull();
        tabSheet.addThemeVariants(TabSheetVariant.LUMO_TABS_CENTERED);
        tabSheet.addThemeVariants(TabSheetVariant.LUMO_TABS_EQUAL_WIDTH_TABS);

        innerCardLayout = new InnerCardLayout(widgetService);

        tabSheet.add("General", generalForm);
        tabSheet.add("Readers", readerCardLayout);
        tabSheet.add("Writers", writerCardLayout);
        tabSheet.add("Widgets", innerCardLayout);

        add(tabSheet);

        addSaveClickListener(click -> {
            Widget widget = new Widget(
                    generalForm.getWidget().getId(),
                    generalForm.getWidget().getName(),
                    readerCardLayout.getItems(),
                    writerCardLayout.getItems(),
                    innerCardLayout.getItems(),
                    generalForm.getWidget().getBorderWidth(),
                    generalForm.getWidget().getBorderStyle(),
                    generalForm.getWidget().getImage()
            );
            fireEvent(new WidgetSaveEvent(this, Optional.of(widget)));
            System.out.println("Saving widget: " + widget);
        });

        setDeleteButtonVisible(false);
    }

    public void addSaveListener(ComponentEventListener<WidgetSaveEvent> listener) {
        addListener(WidgetSaveEvent.class, listener);
    }

    public void addCloseListener(ComponentEventListener<WidgetCloseEvent> listener) {
        addListener(WidgetCloseEvent.class, listener);
    }

    public void setWidget(Widget widget) {
        this.widget = widget;
        System.out.println("Configure widget: " + widget);

        generalForm.setWidget(widget);
        readerCardLayout.setItems(widget.getReaders());

        tabSheet.setSelectedIndex(0);
    }
}
