package org.bmserras.sot.views.components;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import org.vaadin.lineawesome.LineAwesomeIcon;

import java.util.ArrayList;
import java.util.List;

public class CustomDialog extends Dialog {

    private final List<Component> headerComponents = new ArrayList<>();

    private final Button close = new Button();
    private final Button save = new Button();
    private final Button delete = new Button();
    private final Button cancel = new Button();

    public CustomDialog(String title, int widthAsPercentage, int heightAsPercentage) {
        this(title);
        setWidth(widthAsPercentage, Unit.PERCENTAGE);
        setHeight(heightAsPercentage, Unit.PERCENTAGE);
    }

    public CustomDialog(String title) {
        setHeaderTitle(title);
        addClassName("dialog");
        addToHeader(configureCloseButton());

        addToFooter(
                configureSaveButton(),
                configureDeleteButton(),
                configureCancelButton()
        );
    }

    public CustomDialog(String title, int widthAsPercentage, int heightAsPercentage, boolean showButtons) {
        setHeaderTitle(title);
        setWidth(widthAsPercentage, Unit.PERCENTAGE);
        setHeight(heightAsPercentage, Unit.PERCENTAGE);
        addClassName("dialog");
        addToHeader(configureCloseButton());
    }

    public CustomDialog(int widthAsPercentage, int heightAsPercentage) {
        this("Default title", widthAsPercentage, heightAsPercentage);
    }

    private Button configureCloseButton() {
        close.setIcon(LineAwesomeIcon.TIMES_SOLID.create());
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        close.addClickListener(click -> close());
        return close;
    }

    private Button configureSaveButton() {
        save.setText("Save");
        save.setIcon(LineAwesomeIcon.SAVE.create());
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        return save;
    }

    private Button configureDeleteButton() {
        delete.setText("Delete");
        delete.setIcon(LineAwesomeIcon.TRASH_SOLID.create());
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        delete.addClickShortcut(Key.DELETE);
        return delete;
    }

    private Button configureCancelButton() {
        cancel.setText("Cancel");
        cancel.setIcon(LineAwesomeIcon.TIMES_SOLID.create());
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        cancel.addClickListener(click -> close());
        return cancel;
    }

    public void addToHeader(Component... component) {
        getHeader().removeAll();
        headerComponents.addAll(List.of(component));
        for (int i = headerComponents.size() - 1; i >= 0; i--) getHeader().add(component);
        getHeader().add(close);
    }

    public void addToContent(Component... component) {
        add(component);
    }

    public void addToFooter(Component... components) {
        getFooter().add(components);
    }

    protected void addSaveClickListener(ComponentEventListener<ClickEvent<Button>> listener) {
        save.addClickListener(listener);
    }

    protected void addDeleteClickListener(ComponentEventListener<ClickEvent<Button>> listener) {
        delete.addClickListener(listener);
    }

    public void setSaveButtonVisible(boolean visible) {
        save.setVisible(visible);
    }

    public void setDeleteButtonVisible(boolean visible) {
        delete.setVisible(visible);
    }

    public void setCancelButtonVisible(boolean visible) {
        cancel.setVisible(visible);
    }

    public void setTitle(String title) {
        setHeaderTitle(title);
    }
}
