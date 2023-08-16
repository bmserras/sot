package org.bmserras.sot.views.project.components;

import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import org.bmserras.sot.data.domain.Project;
import org.bmserras.sot.events.project.ProjectCloseEvent;
import org.bmserras.sot.events.project.ProjectDeleteEvent;
import org.bmserras.sot.events.project.ProjectSaveEvent;

import java.util.Optional;

public class ProjectForm extends FormLayout {

    private final NumberField identifier = new NumberField("Identifier");
    private final TextField name = new TextField("Name");

    private final Button save = new Button("Save");
    private final Button delete = new Button("Delete");
    private final Button close = new Button("Cancel");

    private final Binder<Project> binder = new Binder<>(Project.class);

    public ProjectForm() {
        name.setPlaceholder("Blank project");

        binder.bind(identifier, project -> (double) project.getId(), null);
        binder.bind(name, Project::getName, Project::setName);

        add(identifier, name, createButtonsLayout());
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

            ConfirmDialog confirmDialog = new ConfirmDialog();
            confirmDialog.setHeader("Delete project");
            confirmDialog.setText("Are you sure you want to delete this project?");

            confirmDialog.setCancelable(true);
            confirmDialog.setConfirmText("Yes");
            confirmDialog.addConfirmListener(confirm -> fireEvent(new ProjectDeleteEvent(this, Optional.of(binder.getBean()))));

            confirmDialog.open();
        });
        close.addClickListener(event -> fireEvent(new ProjectCloseEvent(this)));

        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));
        HorizontalLayout horizontalLayout = new HorizontalLayout(save, delete, close);
        horizontalLayout.expand(save, delete, close);
        return horizontalLayout;
    }

    private void validateAndSave() {
        if (binder.isValid()) {
            fireEvent(new ProjectSaveEvent(this, Optional.of(binder.getBean())));
        }
    }

    public void setProject(Project project) {
        binder.setBean(project);
        this.setVisible(project != null);
    }

    public void addSaveListener(ComponentEventListener<ProjectSaveEvent> listener) {
        addListener(ProjectSaveEvent.class, listener);
    }

    public void addDeleteListener(ComponentEventListener<ProjectDeleteEvent> listener) {
        addListener(ProjectDeleteEvent.class, listener);
    }

    public void addCloseListener(ComponentEventListener<ProjectCloseEvent> listener) {
        addListener(ProjectCloseEvent.class, listener);
    }
}
