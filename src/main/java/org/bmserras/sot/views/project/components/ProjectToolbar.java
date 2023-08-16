package org.bmserras.sot.views.project.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import org.bmserras.sot.data.domain.Project;
import org.bmserras.sot.events.project.ProjectAddEvent;
import org.bmserras.sot.events.project.ProjectDeleteEvent;
import org.bmserras.sot.events.project.ProjectOpenEvent;
import org.vaadin.lineawesome.LineAwesomeIcon;

import java.util.Optional;

public class ProjectToolbar extends HorizontalLayout {

    Component addIcon = LineAwesomeIcon.PLUS_CIRCLE_SOLID.create();
    Component openIcon = LineAwesomeIcon.EXTERNAL_LINK_ALT_SOLID.create();
    Component deleteIcon = LineAwesomeIcon.TRASH_SOLID.create();

    Button add = new Button(addIcon);
    Button open = new Button(openIcon);
    Button delete = new Button(deleteIcon);

    Project project;

    public ProjectToolbar() {
        add.addClickListener(click -> fireEvent(new ProjectAddEvent(this)));
        open.addClickListener(click -> fireEvent(new ProjectOpenEvent(this, Optional.of(project))));
        delete.addClickListener(click -> fireEvent(new ProjectDeleteEvent(this, Optional.of(project))));

        add(add, open, delete);
    }

    public void setProject(Project p) {
        project = p;
        open.setVisible(p != null);
        delete.setVisible(p != null);
    }

    public void addAddListener(ComponentEventListener<ProjectAddEvent> listener) {
        addListener(ProjectAddEvent.class, listener);
    }

    public void addOpenListener(ComponentEventListener<ProjectOpenEvent> listener) {
        addListener(ProjectOpenEvent.class, listener);
    }

    public void addDeleteListener(ComponentEventListener<ProjectDeleteEvent> listener) {
        addListener(ProjectDeleteEvent.class, listener);
    }
}
