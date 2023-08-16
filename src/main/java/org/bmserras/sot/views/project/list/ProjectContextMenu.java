package org.bmserras.sot.views.project.list;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.contextmenu.GridContextMenu;
import com.vaadin.flow.component.grid.contextmenu.GridMenuItem;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import org.bmserras.sot.data.domain.Project;
import org.bmserras.sot.events.project.ProjectAddEvent;
import org.bmserras.sot.events.project.ProjectEditEvent;
import org.bmserras.sot.events.project.ProjectOpenEvent;
import org.bmserras.sot.events.project.ProjectDeleteEvent;
import org.vaadin.lineawesome.LineAwesomeIcon;

import java.util.Optional;

public class ProjectContextMenu extends GridContextMenu<Project> {

    Component addIcon = LineAwesomeIcon.PLUS_CIRCLE_SOLID.create();
    Component openIcon = LineAwesomeIcon.EXTERNAL_LINK_ALT_SOLID.create();
    Component editIcon = LineAwesomeIcon.EDIT.create();
    Component deleteIcon = LineAwesomeIcon.TRASH_SOLID.create();

    GridMenuItem<Project> add;
    GridMenuItem<Project> open;
    GridMenuItem<Project> edit;
    GridMenuItem<Project> delete;

    public ProjectContextMenu(Grid<Project> target) {
        super(target);

        add = addItem(new ProjectContextMenuItem(addIcon, "Add"),
                click -> fireEvent(new ProjectAddEvent(this)));
        open = addItem(new ProjectContextMenuItem(openIcon, "Open"),
                click -> fireEvent(new ProjectOpenEvent(this, click.getItem())));
        edit = addItem(new ProjectContextMenuItem(editIcon, "Edit"),
                click -> fireEvent(new ProjectEditEvent(this, click.getItem())));
        delete = addItem(new ProjectContextMenuItem(deleteIcon, "Delete"),
                click -> {
                    ConfirmDialog confirmDialog = new ConfirmDialog();
                    confirmDialog.setHeader("Delete project");
                    confirmDialog.setText("Are you sure you want to delete this project?");

                    confirmDialog.setCancelable(true);
                    confirmDialog.setConfirmText("Yes");
                    confirmDialog.addConfirmListener(confirm -> fireEvent(new ProjectDeleteEvent(this, click.getItem())));

                    confirmDialog.open();
                });

        this.addGridContextMenuOpenedListener(event -> {
            boolean isPresent = event.getItem().isPresent();
            add.setVisible(!isPresent);
            open.setVisible(isPresent);
            edit.setVisible(isPresent);
            delete.setVisible(isPresent);
        });
    }

    public void addAddListener(ComponentEventListener<ProjectAddEvent> listener) {
        addListener(ProjectAddEvent.class, listener);
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

    private static class ProjectContextMenuItem extends HorizontalLayout {
        public ProjectContextMenuItem(Component icon, String text) {
            this.setAlignItems(Alignment.CENTER);
            add(icon, new Span(text));
        }
    }
}