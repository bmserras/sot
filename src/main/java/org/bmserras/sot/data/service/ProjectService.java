package org.bmserras.sot.data.service;

import org.bmserras.sot.data.db.project.ProjectDB;
import org.bmserras.sot.data.db.synoptic.SynopticDB;
import org.bmserras.sot.data.db.widget.WidgetDB;
import org.bmserras.sot.data.domain.Project;
import org.bmserras.sot.data.domain.Synoptic;
import org.bmserras.sot.data.domain.Utils;
import org.bmserras.sot.data.domain.Widget;
import org.bmserras.sot.data.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService implements AbstractService<Project> {

    private final ProjectRepository projectRepository;
    private final SynopticService synopticService;
    private final WidgetService widgetService;

    public ProjectService(ProjectRepository projectRepository, SynopticService synopticService, WidgetService widgetService) {
        this.projectRepository = projectRepository;
        this.synopticService = synopticService;
        this.widgetService = widgetService;
    }

    @Override
    public List<Project> findAll() {
        return findAll("");
    }

    @Override
    public List<Project> findAll(String filter) {
        List<Project> projects = new ArrayList<>();
        List<ProjectDB> projectsDB = (filter == null || filter.isEmpty()) ? projectRepository.findAll() :
                projectRepository.search(filter);
        projectsDB.forEach(projectDB -> projects.add(Utils.toProject(projectDB)));
        return projects;
    }

    @Override
    public Optional<Project> findById(String id) {
        Optional<ProjectDB> byId = projectRepository.findById(id);
        return byId.map(Utils::toProject);
    }

    @Override
    public Optional<Project> findByName(String name) {
        Optional<ProjectDB> byName = projectRepository.findByName(name);
        return byName.map(Utils::toProject);
    }

    @Override
    public void save(Project project) {
        if (project == null) return;
        ProjectDB projectDB = new ProjectDB(project.getId(), project.getName());
        List<Synoptic> synoptics = project.getSynoptics();
        List<Widget> widgets = project.getWidgets();
        synoptics.forEach(synoptic -> {
            SynopticDB synopticDB = new SynopticDB(synoptic.getId(), synoptic.getName(), synoptic.getGrid());
            synopticService.save(synoptic);
            projectDB.addSynoptic(synopticDB);
        });
        widgets.forEach(widget -> {
            WidgetDB widgetDB = Utils.toWidgetDB(widget);
            widgetService.save(widget);
            projectDB.addWidget(widgetDB);
        });
        projectRepository.save(projectDB);
    }

    @Override
    public void delete(Project project) {
        ProjectDB projectDB = new ProjectDB(project.getId(), project.getName());
        projectRepository.delete(projectDB);
        project.getSynoptics().forEach(synopticService::delete);
    }

    public void deleteAll() {
        projectRepository.deleteAll();
    }
}
