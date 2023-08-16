package org.bmserras.sot.views.project.list;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.bmserras.sot.data.domain.Project;
import org.bmserras.sot.events.project.ProjectOpenEvent;
import org.bmserras.sot.events.project.ProjectDeleteEvent;
import org.bmserras.sot.events.project.ProjectSaveEvent;
import org.bmserras.sot.views.project.components.ProjectForm;
import org.bmserras.sot.views.project.components.ProjectToolbar;

import java.util.List;
import java.util.Optional;

public class ProjectListLayout extends VerticalLayout {

    private final Grid<Project> grid = new Grid<>(Project.class);

    private final ProjectForm form = new ProjectForm();
    private final ProjectContextMenu contextMenu = new ProjectContextMenu(grid);
    private final ProjectToolbar toolbar = new ProjectToolbar();

    public ProjectListLayout() {
        setSizeFull();

        configureGrid();
        configureForm();
        configureContextMenu();
        configureToolbar();

        add(getToolbar(), getContent());
        closeEditor();
    }

    private void configureGrid() {
        grid.setSizeFull();
        grid.setColumns("id", "name");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        grid.asSingleSelect().addValueChangeListener(event -> editProject(event.getValue()));
    }

    private void configureForm() {
        form.setWidth("25em");
        form.addSaveListener(event -> closeEditor());
        form.addDeleteListener(event -> closeEditor());
        form.addCloseListener(event -> closeEditor());
    }

    private void configureContextMenu() {
        contextMenu.addAddListener(event -> addProject());
        contextMenu.addEditListener(event -> {
            Optional<Project> project = event.getProject();
            project.ifPresentOrElse(this::editProject, () -> editProject(null));
        });
    }

    private void configureToolbar() {
        toolbar.addAddListener(click -> addProject());
    }

    private Component getToolbar() {
        return new HorizontalLayout(toolbar);
    }

    private HorizontalLayout getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, form);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, form);
        content.setSizeFull();
        return content;
    }

    public void setItems(List<Project> projects) {
        grid.setItems(projects);
    }

    public void editProject(Project project) {
        if (project == null) closeEditor();
        else {
            form.setProject(project);
            toolbar.setProject(project);
        }
    }

    private void closeEditor() {
        grid.asSingleSelect().clear();
        form.setProject(null);
        toolbar.setProject(null);
    }

    private void addProject() {
        grid.asSingleSelect().clear();
        editProject(new Project());
    }

    public void addOpenListener(ComponentEventListener<ProjectOpenEvent> listener) {
        toolbar.addOpenListener(listener);
        contextMenu.addOpenListener(listener);
    }

    public void addSaveListener(ComponentEventListener<ProjectSaveEvent> listener) {
        form.addSaveListener(listener);
    }

    public void addDeleteListener(ComponentEventListener<ProjectDeleteEvent> listener) {
        toolbar.addDeleteListener(listener);
        contextMenu.addDeleteListener(listener);
        form.addDeleteListener(listener);
    }
}
