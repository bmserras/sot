package org.bmserras.sot.views.project.card;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import org.bmserras.sot.data.domain.Project;
import org.bmserras.sot.events.project.ProjectDeleteEvent;
import org.bmserras.sot.events.project.ProjectEditEvent;
import org.bmserras.sot.events.project.ProjectOpenEvent;
import org.bmserras.sot.views.components.CustomConfirmDialog;
import org.vaadin.lineawesome.LineAwesomeIcon;

import java.util.Optional;

public class ProjectCardContextMenu extends ContextMenu {

    private Component openIcon = LineAwesomeIcon.EXTERNAL_LINK_ALT_SOLID.create();
    private Component editIcon = LineAwesomeIcon.EDIT.create();
    private Component deleteIcon = LineAwesomeIcon.TRASH_SOLID.create();

    private MenuItem open;
    private MenuItem edit;
    private MenuItem delete;

    public ProjectCardContextMenu(Component target, Project project) {
        super(target);

        open = addItem(new HorizontalLayout(openIcon, new Span("Open")),
                click -> fireEvent(new ProjectOpenEvent(this, Optional.of(project))));
        edit = addItem(new HorizontalLayout(editIcon, new Span("Edit")),
                click -> fireEvent(new ProjectEditEvent(this, Optional.of(project))));
        delete = addItem(new HorizontalLayout(deleteIcon, new Span("Delete")),
                click -> {
                    CustomConfirmDialog confirmDialog = new CustomConfirmDialog(
                            "Delete project",
                            "Are you sure you want to delete this project?",
                            "Yes",
                            true
                    );
                    confirmDialog.addConfirmListener(confirm -> fireEvent(new ProjectDeleteEvent(this,
                            Optional.of(project))));
                    confirmDialog.open();
                });
    }

    public void addOpenListener(ComponentEventListener<ProjectOpenEvent> listener) {
        addListener(ProjectOpenEvent.class, listener);
    }

    public void addEditListener(ComponentEventListener<ProjectEditEvent> listener) {
        addListener(ProjectEditEvent.class, listener);
    }

    public void addDeleteListener(ComponentEventListener<ProjectDeleteEvent> listener) {
        addListener(ProjectDeleteEvent.class, listener);
    }
}
