package org.bmserras.sot.views.project.card;

import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.bmserras.sot.data.domain.Project;
import org.bmserras.sot.events.project.ProjectDeleteEvent;
import org.bmserras.sot.events.project.ProjectOpenEvent;
import org.bmserras.sot.events.project.ProjectSaveEvent;
import org.bmserras.sot.views.project.components.ProjectDialog;
import org.bmserras.sot.views.project.components.ProjectForm;
import org.vaadin.lineawesome.LineAwesomeIcon;

import java.util.ArrayList;
import java.util.List;

public class ProjectCardLayout extends FlexLayout {

    private final List<ComponentEventListener<ProjectOpenEvent>> openListeners;
    private final List<ComponentEventListener<ProjectDeleteEvent>> deleteListeners;

    private final List<ProjectExistingCard> existingCards;
    private final ProjectNewCard newCard;

    private ProjectDialog dialog;
    private ProjectForm form;

    public ProjectCardLayout() {
        setSizeFull();

        this.setFlexDirection(FlexDirection.ROW);
        this.setFlexWrap(FlexWrap.WRAP);
        this.setJustifyContentMode(JustifyContentMode.CENTER);

        configureDialogForm();

        newCard = new ProjectNewCard(
                LineAwesomeIcon.FOLDER_PLUS_SOLID.create(),
                "New Project",
                "Create new project"
        );
        newCard.addMainListener(click -> {
            form.setProject(new Project());
            dialog.setTitle("Create project");
            dialog.setDeleteButtonVisible(false);
            dialog.open();
        });

        existingCards = new ArrayList<>();

        openListeners = new ArrayList<>();
        deleteListeners = new ArrayList<>();

        add(newCard);
    }

    private void configureDialogForm() {
        form = new ProjectForm();
        form.setWidth("25em");
        dialog = new ProjectDialog(form, 50, 50);
        //dialog.addToContent(form);

        dialog.addSaveListener(click -> dialog.close());
        dialog.addDeleteListener(click -> dialog.close());
    }

    public void setItems(List<Project> projects) {
        existingCards.clear();
        removeAll();
        add(newCard);
        for (Project project : projects) {
            ProjectExistingCard existingCard = new ProjectExistingCard(LineAwesomeIcon.FOLDER_OPEN.create(), project.getName(), "Open project", project);
            existingCards.add(existingCard);
            add(existingCard);
            openListeners.forEach(existingCard::addMainListener);
            openListeners.forEach(existingCard::addOpenListener);
            deleteListeners.forEach(existingCard::addDeleteListener);

            existingCard.addEditListener(event -> {
                form.setProject(project);
                dialog.setTitle("Edit project");
                dialog.setDeleteButtonVisible(true);
                dialog.open();
            });
        }
    }

    public void addOpenListener(ComponentEventListener<ProjectOpenEvent> listener) {
        existingCards.forEach(card -> card.addOpenListener(listener));
        existingCards.forEach(card -> card.addMainListener(listener));
        openListeners.add(listener);
    }

    public void addSaveListener(ComponentEventListener<ProjectSaveEvent> listener) {
        dialog.addSaveListener(listener);
    }

    public void addDeleteListener(ComponentEventListener<ProjectDeleteEvent> listener) {
        existingCards.forEach(card -> card.addDeleteListener(listener));
        dialog.addDeleteListener(listener);
        deleteListeners.add(listener);
    }
}
