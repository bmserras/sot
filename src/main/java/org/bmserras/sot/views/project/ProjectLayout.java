package org.bmserras.sot.views.project;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.bmserras.sot.data.domain.Project;
import org.bmserras.sot.events.project.ProjectOpenEvent;
import org.bmserras.sot.events.project.ProjectDeleteEvent;
import org.bmserras.sot.events.project.ProjectSaveEvent;
import org.bmserras.sot.views.components.Toolbar;
import org.bmserras.sot.views.project.card.ProjectCardLayout;
import org.bmserras.sot.views.project.list.ProjectListLayout;
import org.vaadin.lineawesome.LineAwesomeIcon;

import java.util.List;

public class ProjectLayout extends VerticalLayout {

    private final HorizontalLayout header;
    private final H2 title;
    private final Toolbar toolbar;

    private final ProjectCardLayout cardLayout;
    private final ProjectListLayout listLayout;

    private final Button changeViewButton;
    private final Component cardIcon = LineAwesomeIcon.TH_SOLID.create();
    private final Component listIcon = LineAwesomeIcon.LIST_SOLID.create();

    private final Button infoButton;
    private final Component infoIcon = LineAwesomeIcon.INFO_CIRCLE_SOLID.create();
    private final Dialog infoDialog;

    // true = card, false = list
    private boolean view = false;

    public ProjectLayout() {
        setSizeFull();

        title = new H2("My Projects");
        toolbar = new Toolbar();
        header = new HorizontalLayout(title, toolbar);
        header.setWidthFull();
        header.expand(title);
        header.setAlignItems(Alignment.BASELINE);

        changeViewButton = toolbar.addButton(cardIcon, click -> changeView());

        infoButton = toolbar.addButton(infoIcon, click -> displayInfo());
        infoDialog = new Dialog("This is an information dialog");

        cardLayout = new ProjectCardLayout();
        listLayout = new ProjectListLayout();
        changeView();

        add(header, cardLayout, listLayout, infoDialog);
    }

    private void displayInfo() {
        infoDialog.open();
    }

    private void changeView() {
        view = !view;
        cardLayout.setVisible(view);
        listLayout.setVisible(!view);
        changeViewButton.setIcon(view ? listIcon : cardIcon);
    }

    public void setItems(List<Project> projects) {
        cardLayout.setItems(projects);
        listLayout.setItems(projects);
    }

    public void addOpenListener(ComponentEventListener<ProjectOpenEvent> listener) {
        listLayout.addOpenListener(listener);
        cardLayout.addOpenListener(listener);
    }

    public void addSaveListener(ComponentEventListener<ProjectSaveEvent> listener) {
        listLayout.addSaveListener(listener);
        cardLayout.addSaveListener(listener);
    }

    public void addDeleteListener(ComponentEventListener<ProjectDeleteEvent> listener) {
        listLayout.addDeleteListener(listener);
        cardLayout.addDeleteListener(listener);
    }
}
