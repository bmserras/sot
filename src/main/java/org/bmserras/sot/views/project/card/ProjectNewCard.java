package org.bmserras.sot.views.project.card;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.bmserras.sot.events.project.ProjectAddEvent;
import org.vaadin.lineawesome.LineAwesomeIcon;

public class ProjectNewCard extends VerticalLayout {

    private final Button mainButton;
    private final HorizontalLayout container;
    private final Span title;
    private final Button optionsButton;

    public ProjectNewCard(Component icon, String title, String tooltipText) {
        addClassName("card");
        addClassName("new-card");
        setMargin(true);

        this.mainButton = new Button(icon);
        this.mainButton.addClassName("main-button");
        this.mainButton.setTooltipText(tooltipText);
        this.mainButton.addClickListener(click -> fireEvent(new ProjectAddEvent(this)));

        this.title = new Span(title);
        this.title.addClassName("title");

        this.optionsButton = new Button(LineAwesomeIcon.ELLIPSIS_V_SOLID.create());
        this.optionsButton.addClassName("options-button");
        this.optionsButton.setTooltipText("Options");

        this.container = new HorizontalLayout(this.title, optionsButton);
        this.container.addClassName("container");

        add(mainButton, container);
    }

    public void addMainListener(ComponentEventListener<ProjectAddEvent> listener) {
        addListener(ProjectAddEvent.class, listener);
    }
}
