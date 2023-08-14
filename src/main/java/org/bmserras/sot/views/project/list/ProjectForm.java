package org.bmserras.sot.views.project.list;

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
import org.bmserras.sot.data.db.project.ProjectDB;
import org.bmserras.sot.data.domain.Project;
import org.bmserras.sot.events.project.ProjectCloseEvent;
import org.bmserras.sot.events.project.ProjectRemoveEvent;
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
        binder.bind(identifier, project -> (double) project.getId(), null);
        binder.bind(name, Project::getName, Project::setName);

        add(identifier, name, createButtonsLayout());
    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(event -> validateAndSave()); // <1>
        delete.addClickListener(event -> fireEvent(new ProjectRemoveEvent(this, Optional.of(binder.getBean())))); // <2>
        close.addClickListener(event -> fireEvent(new ProjectCloseEvent(this))); // <3>

        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid())); // <4>
        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {
        if (binder.isValid()) {
            fireEvent(new ProjectSaveEvent(this, Optional.of(binder.getBean()))); // <6>
        }
    }

    public void setProject(Project project) {
        binder.setBean(project);
    }

    public Registration addDeleteListener(ComponentEventListener<ProjectRemoveEvent> listener) {
        return addListener(ProjectRemoveEvent.class, listener);
    }

    public Registration addSaveListener(ComponentEventListener<ProjectSaveEvent> listener) {
        return addListener(ProjectSaveEvent.class, listener);
    }

    public Registration addCloseListener(ComponentEventListener<ProjectCloseEvent> listener) {
        return addListener(ProjectCloseEvent.class, listener);
    }
}
