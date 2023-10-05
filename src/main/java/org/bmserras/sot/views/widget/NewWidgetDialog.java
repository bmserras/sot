package org.bmserras.sot.views.widget;

import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.dialog.Dialog;
import org.bmserras.sot.views.widget.WidgetForm;

public class NewWidgetDialog extends Dialog {

    private WidgetForm widgetForm = new WidgetForm();

    public NewWidgetDialog() {

        setWidth(50, Unit.PERCENTAGE);
        setHeight(85, Unit.PERCENTAGE);

        add(widgetForm);
    }
}
