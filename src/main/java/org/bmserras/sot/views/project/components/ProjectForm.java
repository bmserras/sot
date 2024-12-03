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
import org.bmserras.sot.views.components.CustomConfirmDialog;

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

        add(identifier, name);
    }

    private void validateAndSave() {
        if (binder.isValid()) {
            fireEvent(new ProjectSaveEvent(this, Optional.of(binder.getBean())));
        }
    }

    public Project getProject() {
        return binder.getBean();
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
