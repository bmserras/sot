package org.bmserras.sot.views.widgetinstance;

import com.vaadin.flow.component.ComponentEventListener;
import org.bmserras.sot.events.widgetinstance.WidgetInstanceDeleteEvent;
import org.bmserras.sot.events.widgetinstance.WidgetInstanceSaveEvent;
import org.bmserras.sot.views.components.CustomConfirmDialog;
import org.bmserras.sot.views.components.CustomDialog;

import java.util.Optional;

public class WidgetInstanceDialog extends CustomDialog {

    private final WidgetInstanceForm widgetInstanceForm;

    public WidgetInstanceDialog(String title, int widthAsPercentage, int heightAsPercentage, WidgetInstanceForm widgetInstanceForm) {
        super(title, widthAsPercentage, heightAsPercentage);
        this.widgetInstanceForm = widgetInstanceForm;
        addToContent(widgetInstanceForm);

        addSaveClickListener(click -> fireEvent(
                new WidgetInstanceSaveEvent(this, Optional.of(widgetInstanceForm.getWidgetInstance()))
        ));

        addDeleteClickListener(click -> {
            CustomConfirmDialog confirmDialog = new CustomConfirmDialog(
                    "Delete widgetInstance",
                    "Are you sure you want to delete this widgetInstance?",
                    "Yes",
                    true
            );
            confirmDialog.addConfirmListener(confirm -> fireEvent(new WidgetInstanceDeleteEvent(this,
                    Optional.of(widgetInstanceForm.getWidgetInstance()))));
            confirmDialog.open();
        });
    }

    public WidgetInstanceDialog(WidgetInstanceForm widgetInstanceForm) {
        super("Configure widget instance");
        this.widgetInstanceForm = widgetInstanceForm;
        addToContent(widgetInstanceForm);

        addSaveClickListener(click -> fireEvent(
                new WidgetInstanceSaveEvent(this, Optional.of(widgetInstanceForm.getWidgetInstance()))
        ));

        addDeleteClickListener(click -> {
            CustomConfirmDialog confirmDialog = new CustomConfirmDialog(
                    "Delete widgetInstance",
                    "Are you sure you want to delete this widgetInstance?",
                    "Yes",
                    true
            );
            confirmDialog.addConfirmListener(confirm -> fireEvent(new WidgetInstanceDeleteEvent(this,
                    Optional.of(widgetInstanceForm.getWidgetInstance()))));
            confirmDialog.open();
        });

    }

    public void addSaveListener(ComponentEventListener<WidgetInstanceSaveEvent> listener) {
        addListener(WidgetInstanceSaveEvent.class, listener);
    }

    public void addDeleteListener(ComponentEventListener<WidgetInstanceDeleteEvent> listener) {
        addListener(WidgetInstanceDeleteEvent.class, listener);
    }
}
