package org.bmserras.sot.views.widgetinstance;

import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import org.bmserras.sot.data.domain.WidgetInstance;
import org.bmserras.sot.events.widgetinstance.WidgetInstanceCloseEvent;
import org.bmserras.sot.events.widgetinstance.WidgetInstanceDeleteEvent;
import org.bmserras.sot.events.widgetinstance.WidgetInstanceSaveEvent;
import org.bmserras.sot.views.components.CustomConfirmDialog;

import java.util.Optional;

public class WidgetInstanceForm extends FormLayout {

    private final NumberField identifier = new NumberField("Identifier");
    private final TextField name = new TextField("Name");

    private final Button save = new Button("Save");
    private final Button delete = new Button("Delete");
    private final Button close = new Button("Cancel");

    private final Binder<WidgetInstance> binder = new Binder<>(WidgetInstance.class);

    public WidgetInstanceForm(boolean withButtons) {
        name.setPlaceholder("Blank widget");

        binder.bind(identifier, widgetInstance -> (double) widgetInstance.getId(), null);
        binder.bind(name, WidgetInstance::getName, WidgetInstance::setName);

        add(identifier, name);
        if (withButtons) add(createButtonsLayout());
    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        delete.addClickShortcut(Key.DELETE);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> {

            CustomConfirmDialog confirmDialog = new CustomConfirmDialog(
                    "Delete project",
                    "Are you sure you want to delete this widget instance?",
                    "Yes",
                    true
            );
            confirmDialog.addConfirmListener(confirm -> fireEvent(new WidgetInstanceDeleteEvent(this, Optional.of(binder.getBean()))));

            confirmDialog.open();
        });
        close.addClickListener(event -> fireEvent(new WidgetInstanceCloseEvent(this)));

        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));
        HorizontalLayout horizontalLayout = new HorizontalLayout(save, delete, close);
        horizontalLayout.expand(save, delete, close);
        return horizontalLayout;
    }

    private void validateAndSave() {
        if (binder.isValid()) {
            fireEvent(new WidgetInstanceSaveEvent(this, Optional.of(binder.getBean())));
        }
    }

    public WidgetInstance getWidgetInstance() {
        return binder.getBean();
    }

    public void setWidgetInstance(WidgetInstance widgetInstance) {
        binder.setBean(widgetInstance);
        this.setVisible(widgetInstance != null);
    }

    public void addSaveListener(ComponentEventListener<WidgetInstanceSaveEvent> listener) {
        addListener(WidgetInstanceSaveEvent.class, listener);
    }

    public void addDeleteListener(ComponentEventListener<WidgetInstanceDeleteEvent> listener) {
        addListener(WidgetInstanceDeleteEvent.class, listener);
    }

    public void addCloseListener(ComponentEventListener<WidgetInstanceCloseEvent> listener) {
        addListener(WidgetInstanceCloseEvent.class, listener);
    }
}
