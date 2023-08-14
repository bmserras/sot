package org.bmserras.sot.views.project.list;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.contextmenu.GridContextMenu;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import org.bmserras.sot.data.db.project.ProjectDB;
import org.bmserras.sot.data.domain.Project;
import org.bmserras.sot.data.service.ProjectService;
import org.vaadin.lineawesome.LineAwesomeIcon;

import java.util.Optional;

public class ProjectList extends VerticalLayout {

    LineAwesomeIcon addIcon = LineAwesomeIcon.PLUS_CIRCLE_SOLID;
    LineAwesomeIcon openIcon = LineAwesomeIcon.EXTERNAL_LINK_ALT_SOLID;
    LineAwesomeIcon mapIcon = LineAwesomeIcon.MAP_MARKED_ALT_SOLID;
    LineAwesomeIcon removeIcon = LineAwesomeIcon.TRASH_SOLID;

    Grid<Project> grid = new Grid<>(Project.class);
    ProjectContextMenu contextMenu = new ProjectContextMenu(grid);

    TextField filterText = new TextField();
    Button addProjectButton = new Button(addIcon.create());

    Optional<Project> selectedProject = Optional.empty();
    ProjectForm form;
    ProjectToolbar toolbar;
    ProjectService projectService;

    public ProjectList(ProjectService projectService) {
        this.projectService = projectService;

        setMargin(false);
        setPadding(false);

        setSizeFull();
        configureGrid();
        configureForm();

        add(getToolbar(), getContent());
        updateList();
        closeEditor();
    }

    private void configureGrid() {
        grid.setSizeFull();
        grid.setColumns("id", "name");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        grid.asSingleSelect().addValueChangeListener(event -> {
            selectedProject = Optional.ofNullable(event.getValue());
            editProject(selectedProject);
        });
    }

    private void configureForm() {
        form = new ProjectForm();
        form.setWidth("25em");
        form.addSaveListener(event -> saveProject(event.getProject()));
        form.addDeleteListener(event -> deleteProject(event.getProject()));
        form.addCloseListener(event -> closeEditor());
    }

    private Component getToolbar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        addProjectButton.addClickListener(click -> addProject());

        toolbar = new ProjectToolbar();
        toolbar.addOpenListener(event -> openProject(event.getProject()));
        toolbar.addRemoveListener(event -> removeProject(event.getProject()));

        return new HorizontalLayout(filterText, addProjectButton, toolbar/*openButton, mapButton, removeButton*/);
    }

    private HorizontalLayout getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, form);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, form);
        content.setSizeFull();
        return content;
    }

    private void updateList() {
        grid.setItems(projectService.findAll(filterText.getValue()));
    }

    public void editProject(Optional<Project> project) {
        if (project.isEmpty()) {
            closeEditor();
        }
        else {
            form.setProject(project.get());
            form.setVisible(true);

            toolbar.setProject(project.get());
            toolbar.setVisible(!project.get().equals(new Project()));
        }
    }

    private void addProject() {
        grid.asSingleSelect().clear();
        editProject(Optional.of(new Project()));
    }

    private void openProject(Optional<Project> project) {
        project.ifPresent(proj -> getUI().ifPresent(ui -> ui.navigate("project/" + proj.getId())));
    }

    private void openMap(Optional<Project> project) {

    }

    private void removeProject(Optional<Project> project) {
        project.ifPresent(proj -> {
            projectService.delete(proj);
            updateList();
            closeEditor();
        });
    }

    private void closeEditor() {
        form.setProject(null);
        form.setVisible(false);

        toolbar.setProject(null);
        toolbar.setVisible(false);
    }

    private void saveProject(Optional<Project> project) {
        project.ifPresent(proj -> {
            projectService.save(proj);
            updateList();
            closeEditor();
        });
    }

    private void deleteProject(Optional<Project> project) {
        project.ifPresent(proj -> {
            projectService.delete(proj);
            updateList();
            closeEditor();
        });
    }

    private class ProjectContextMenu extends GridContextMenu<Project> {

        public ProjectContextMenu(Grid<Project> target) {
            super(target);

            addItem(new HorizontalLayout(openIcon.create(), new Span("Open")), click -> openProject(click.getItem()));
            addItem(new HorizontalLayout(mapIcon.create(), new Span("Map")), click -> openMap(click.getItem()));
            addItem(new HorizontalLayout(removeIcon.create(), new Span("Remove")), click -> removeProject(click.getItem()));
        }
    }

}
