package org.bmserras.sot.views.project.components;

import com.vaadin.flow.component.ComponentEventListener;
import org.bmserras.sot.events.project.ProjectDeleteEvent;
import org.bmserras.sot.events.project.ProjectSaveEvent;
import org.bmserras.sot.views.components.CustomConfirmDialog;
import org.bmserras.sot.views.components.CustomDialog;

import java.util.Optional;

public class ProjectDialog extends CustomDialog {

    private final ProjectForm projectForm;

    public ProjectDialog(ProjectForm projectForm, String title, int widthAsPercentage, int heightAsPercentage) {
        super(title, widthAsPercentage, heightAsPercentage);

        this.projectForm = projectForm;
        addToContent(projectForm);

        addSaveClickListener(click -> fireEvent(
                new ProjectSaveEvent(this, Optional.of(projectForm.getProject()))
        ));

        addDeleteClickListener(click -> {
            CustomConfirmDialog confirmDialog = new CustomConfirmDialog(
                    "Delete project",
                    "Are you sure you want to delete this project?",
                    "Yes",
                    true
            );
            confirmDialog.addConfirmListener(confirm -> fireEvent(new ProjectDeleteEvent(this,
                    Optional.of(projectForm.getProject()))));
            confirmDialog.open();
        });

    }

    public void addSaveListener(ComponentEventListener<ProjectSaveEvent> listener) {
        addListener(ProjectSaveEvent.class, listener);
    }

    public void addDeleteListener(ComponentEventListener<ProjectDeleteEvent> listener) {
        addListener(ProjectDeleteEvent.class, listener);
    }
}
