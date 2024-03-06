package org.bmserras.sot.views.widget.components;

import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.component.tabs.TabSheetVariant;
import com.vaadin.flow.theme.lumo.LumoIcon;
import org.bmserras.sot.data.domain.Widget;
import org.bmserras.sot.data.domain.readers.ValueReader;
import org.bmserras.sot.events.widget.WidgetCloseEvent;
import org.bmserras.sot.events.widget.WidgetSaveEvent;
import org.bmserras.sot.views.components.CustomDialog;
import org.bmserras.sot.views.widget.general.GeneralForm;
import org.bmserras.sot.views.widget.properties.PropertiesForm;
import org.bmserras.sot.views.widget.readers.ReadersTab;
import org.bmserras.sot.views.widget.writers.WritersForm;

import java.util.List;
import java.util.Optional;

public class WidgetForm extends CustomDialog {

    private final TabSheet tabSheet = new TabSheet();

    private final GeneralForm generalForm = new GeneralForm();
    private final ReadersTab readersTab = new ReadersTab();
    private final WritersForm writersForm = new WritersForm();
    private final PropertiesForm propertiesForm = new PropertiesForm();
    private final PropertiesForm widgetsForm = new PropertiesForm();

    public WidgetForm() {
        super("Configure widget", 55, 85);

        tabSheet.setSizeFull();
        tabSheet.addThemeVariants(TabSheetVariant.LUMO_TABS_CENTERED);
        tabSheet.addThemeVariants(TabSheetVariant.LUMO_TABS_EQUAL_WIDTH_TABS);

        tabSheet.add("General", generalForm);
        tabSheet.add("Readers", readersTab);
        tabSheet.add("Writers", writersForm);
        tabSheet.add("Properties", propertiesForm);
        tabSheet.add("Widgets", widgetsForm);

        add(tabSheet);

        addSaveClickListener(click -> {
            List<ValueReader> items = readersTab.getItems();
            System.out.println(items);
            Widget widget = new Widget(
                    generalForm.getWidget().getId(),
                    generalForm.getWidget().getName(),
                    readersTab.getItems(),
                    null,
                    null
            );
            fireEvent(new WidgetSaveEvent(this, Optional.of(widget)));
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
        generalForm.setWidget(widget);
        readersTab.setItems(widget.getReaders());
    }
}
