package org.bmserras.sot.views.project.card;

import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.bmserras.sot.data.domain.Project;
import org.bmserras.sot.events.project.ProjectDeleteEvent;
import org.bmserras.sot.events.project.ProjectOpenEvent;
import org.bmserras.sot.events.project.ProjectSaveEvent;
import org.bmserras.sot.views.project.components.ProjectForm;
import org.vaadin.lineawesome.LineAwesomeIcon;

import java.util.ArrayList;
import java.util.List;

public class ProjectCardLayout extends VerticalLayout {

    private final List<ComponentEventListener<ProjectOpenEvent>> openListeners;
    private final List<ComponentEventListener<ProjectDeleteEvent>> deleteListeners;

    private final List<ProjectExistingCard> existingCards;
    private final HorizontalLayout cards;
    private final ProjectNewCard newCard;

    private Dialog dialogForm;
    private ProjectForm form;

    public ProjectCardLayout() {
        setSizeFull();

        configureDialogForm();

        newCard = new ProjectNewCard(
                LineAwesomeIcon.FOLDER_PLUS_SOLID.create(),
                "New Project",
                "Create new project"
        );
        newCard.addMainListener(click -> {
            form.setProject(new Project());
            dialogForm.open();
        });

        existingCards = new ArrayList<>();
        cards = new HorizontalLayout();
        cards.setWidthFull();
        cards.setHeight("40%");

        openListeners = new ArrayList<>();
        deleteListeners = new ArrayList<>();

        add(cards);
    }

    private void configureDialogForm() {
        form = new ProjectForm();
        form.setWidth("25em");
        dialogForm = new Dialog(form);

        form.addSaveListener(click -> dialogForm.close());
        form.addCloseListener(click -> dialogForm.close());
        form.addDeleteListener(click -> dialogForm.close());
    }

    public void setItems(List<Project> projects) {
        existingCards.clear();
        cards.removeAll();
        cards.add(newCard);
        for (Project project : projects) {
            ProjectExistingCard existingCard = new ProjectExistingCard(LineAwesomeIcon.FOLDER_OPEN.create(), project.getName(), "Open project", project);
            existingCards.add(existingCard);
            cards.add(existingCard);
            openListeners.forEach(existingCard::addMainListener);
            openListeners.forEach(existingCard::addOpenListener);
            deleteListeners.forEach(existingCard::addDeleteListener);

            existingCard.addEditListener(event -> {
                form.setProject(project);
                dialogForm.open();
            });
        }
    }

    public void addOpenListener(ComponentEventListener<ProjectOpenEvent> listener) {
        existingCards.forEach(card -> card.addOpenListener(listener));
        existingCards.forEach(card -> card.addMainListener(listener));
        openListeners.add(listener);
    }

    public void addSaveListener(ComponentEventListener<ProjectSaveEvent> listener) {
        form.addSaveListener(listener);
    }

    public void addDeleteListener(ComponentEventListener<ProjectDeleteEvent> listener) {
        existingCards.forEach(card -> card.addDeleteListener(listener));
        form.addDeleteListener(listener);
        deleteListeners.add(listener);
    }
}
