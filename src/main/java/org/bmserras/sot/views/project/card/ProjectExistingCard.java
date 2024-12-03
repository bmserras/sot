package org.bmserras.sot.views.project.card;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.bmserras.sot.data.domain.Project;
import org.bmserras.sot.events.project.ProjectDeleteEvent;
import org.bmserras.sot.events.project.ProjectEditEvent;
import org.bmserras.sot.events.project.ProjectOpenEvent;
import org.vaadin.lineawesome.LineAwesomeIcon;

import java.util.Optional;

public class ProjectExistingCard extends VerticalLayout {

    private final Button mainButton;
    private final HorizontalLayout container;
    private final Span title;
    private final Button optionsButton;

    private ProjectCardContextMenu mainContextMenu;
    private ProjectCardContextMenu optionsContextMenu;

    public ProjectExistingCard(Component icon, String title, String tooltipText, Project project) {
        addClassName("card");

        setSizeFull();
        setMargin(true);

        this.mainButton = new Button(icon);
        this.mainButton.addClassName("main-button");
        this.mainButton.setTooltipText(tooltipText);
        this.mainButton.addClickListener(click -> fireEvent(new ProjectOpenEvent(this, Optional.of(project))));

        this.title = new Span(title);
        this.title.addClassName("title");

        this.optionsButton = new Button(LineAwesomeIcon.ELLIPSIS_V_SOLID.create());
        this.optionsButton.addClassName("options-button");
        this.optionsButton.setTooltipText("Options");

        this.container = new HorizontalLayout(this.title, optionsButton);
        this.container.addClassName("container");

        mainContextMenu = new ProjectCardContextMenu(this, project);
        mainContextMenu.setOpenOnClick(false);

        optionsContextMenu = new ProjectCardContextMenu(optionsButton, project);
        optionsContextMenu.setOpenOnClick(true);

        add(mainButton, container);
    }

    public void addMainListener(ComponentEventListener<ProjectOpenEvent> listener) {
        addListener(ProjectOpenEvent.class, listener);
    }

    public void addOpenListener(ComponentEventListener<ProjectOpenEvent> listener) {
        mainContextMenu.addOpenListener(listener);
        optionsContextMenu.addOpenListener(listener);
        mainContextMenu.addOpenListener(click -> mainContextMenu.close());
        optionsContextMenu.addOpenListener(click -> optionsContextMenu.close());
    }

    public void addEditListener(ComponentEventListener<ProjectEditEvent> listener) {
        mainContextMenu.addEditListener(listener);
        optionsContextMenu.addEditListener(listener);
    }

    public void addDeleteListener(ComponentEventListener<ProjectDeleteEvent> listener) {
        mainContextMenu.addDeleteListener(listener);
        optionsContextMenu.addDeleteListener(listener);
        mainContextMenu.addDeleteListener(click -> mainContextMenu.close());
        optionsContextMenu.addDeleteListener(click -> optionsContextMenu.close());
    }
}
