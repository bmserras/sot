package org.bmserras.sot.views.project;

import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.shared.Registration;
import org.bmserras.sot.data.entity.project.Project;
import org.bmserras.sot.events.project.ProjectOpenEvent;
import org.bmserras.sot.events.project.ProjectRemoveEvent;
import org.vaadin.lineawesome.LineAwesomeIcon;

import java.util.Optional;

public class ProjectToolbar extends HorizontalLayout {

    LineAwesomeIcon openIcon = LineAwesomeIcon.EXTERNAL_LINK_ALT_SOLID;
    LineAwesomeIcon removeIcon = LineAwesomeIcon.TRASH_SOLID;

    Button openButton = new Button(openIcon.create());
    Button removeButton = new Button(removeIcon.create());

    Optional<Project> project = Optional.empty();

    public ProjectToolbar() {

        openButton.addClickListener(click -> fireEvent(new ProjectOpenEvent(this, project)));
        removeButton.addClickListener(click -> fireEvent(new ProjectRemoveEvent(this, project)));

        add(openButton, removeButton);
    }

    public void setProject(Project s) {
        project = Optional.ofNullable(s);
    }

    public Registration addOpenListener(ComponentEventListener<ProjectOpenEvent> listener) {
        return addListener(ProjectOpenEvent.class, listener);
    }

    public Registration addRemoveListener(ComponentEventListener<ProjectRemoveEvent> listener) {
        return addListener(ProjectRemoveEvent.class, listener);
    }
}
