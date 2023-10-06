package org.bmserras.sot.views.widget;

import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.component.tabs.TabSheetVariant;
import com.vaadin.flow.theme.lumo.LumoIcon;
import org.bmserras.sot.events.widget.WidgetCloseEvent;
import org.bmserras.sot.events.widget.WidgetSaveEvent;
import org.bmserras.sot.views.widget.general.GeneralForm;
import org.bmserras.sot.views.widget.properties.PropertiesForm;
import org.bmserras.sot.views.widget.readers.ReadersTab;
import org.bmserras.sot.views.widget.writers.WritersForm;

public class WidgetForm extends Dialog {

    private final TabSheet tabSheet = new TabSheet();

    private final Button closeButton;
    private final Button saveButton;
    private final Button cancelButton;

    public WidgetForm() {
        setWidth("55%");
        setHeight("85%");

        setHeaderTitle("Configure widget");

        closeButton = new Button(LumoIcon.CROSS.create(), click -> close());
        closeButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        getHeader().add(closeButton);

        tabSheet.setSizeFull();
        tabSheet.addThemeVariants(TabSheetVariant.LUMO_TABS_CENTERED);
        tabSheet.addThemeVariants(TabSheetVariant.LUMO_TABS_EQUAL_WIDTH_TABS);

        tabSheet.add("General", new GeneralForm());
        tabSheet.add("Readers", new ReadersTab());
        tabSheet.add("Writers", new WritersForm());
        tabSheet.add("Properties", new PropertiesForm());
        tabSheet.add("Widgets", new PropertiesForm());

        add(tabSheet);

        saveButton = new Button("Save");
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        cancelButton = new Button("Cancel", click -> close());

        getFooter().add(saveButton, cancelButton);
    }

    public void addSaveListener(ComponentEventListener<WidgetSaveEvent> listener) {
        addListener(WidgetSaveEvent.class, listener);
    }

    public void addCloseListener(ComponentEventListener<WidgetCloseEvent> listener) {
        addListener(WidgetCloseEvent.class, listener);
    }
}
