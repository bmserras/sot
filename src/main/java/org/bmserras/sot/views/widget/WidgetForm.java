package org.bmserras.sot.views.widget;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;
import org.bmserras.sot.data.entity.widget.Widget;

import java.util.List;

public abstract class WidgetForm extends FormLayout {

    private final NumberField identifier = new NumberField("Identifier");
    private final TextField name = new TextField("Name");

    private final Button save = new Button("Save");
    private final Button delete = new Button("Delete");
    private final Button close = new Button("Cancel");

    private final Binder<Widget> binder = new Binder<>(Widget.class);

    public WidgetForm() {

        binder.bind(identifier, widget -> (double) widget.getIdentifier(), null);
        binder.bind(name, Widget::getName, Widget::setName);

        add(identifier, name);

        add(createOtherFields());
        bindOtherFields(binder);

        add(createButtonsLayout());
    }

    public abstract void bindOtherFields(Binder<Widget> binder);

    public abstract List<Component> createOtherFields();

    public abstract Widget convert(Widget widget);

    public HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this, binder.getBean())));
        close.addClickListener(event -> fireEvent(new CloseEvent(this)));

        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));
        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {
        if (binder.isValid()) {
            fireEvent(new SaveEvent(this, binder.getBean()));
        }
    }

    public void setWidget(Widget widget) {
        binder.setBean(widget);
    }

    // Events
    public static abstract class WidgetFormEvent extends ComponentEvent<WidgetForm> {
        private final Widget widget;

        protected WidgetFormEvent(WidgetForm source, Widget widget) {
            super(source, false);
            this.widget = widget;
        }

        public Widget getWidget() {
            return widget;
        }
    }

    public static class SaveEvent extends WidgetFormEvent {
        SaveEvent(WidgetForm source, Widget widget) {
            super(source, widget);
        }
    }

    public static class DeleteEvent extends WidgetFormEvent {
        DeleteEvent(WidgetForm source, Widget widget) {
            super(source, widget);
        }

    }

    public static class CloseEvent extends WidgetFormEvent {
        CloseEvent(WidgetForm source) {
            super(source, null);
        }

    }

    public Registration addSaveListener(ComponentEventListener<SaveEvent> listener) {
        return addListener(SaveEvent.class, listener);
    }

    public Registration addDeleteListener(ComponentEventListener<DeleteEvent> listener) {
        return addListener(DeleteEvent.class, listener);
    }

    public Registration addCloseListener(ComponentEventListener<CloseEvent> listener) {
        return addListener(CloseEvent.class, listener);
    }

}
