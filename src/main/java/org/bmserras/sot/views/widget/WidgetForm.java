package org.bmserras.sot.views.widget;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.component.tabs.TabSheetVariant;

public class WidgetForm extends VerticalLayout {

    private final TabSheet tabSheet = new TabSheet();

    public WidgetForm() {
        setSizeFull();

        tabSheet.setSizeFull();
        tabSheet.addThemeVariants(TabSheetVariant.LUMO_TABS_CENTERED);
        tabSheet.addThemeVariants(TabSheetVariant.LUMO_TABS_EQUAL_WIDTH_TABS);

        tabSheet.add("General", new GeneralForm());
        tabSheet.add("Readers", new ReadersTab());
        tabSheet.add("Writers", new WritersForm());
        tabSheet.add("Properties", new PropertiesForm());
        tabSheet.add("Widgets", new PropertiesForm());

        add(tabSheet);
    }
}
