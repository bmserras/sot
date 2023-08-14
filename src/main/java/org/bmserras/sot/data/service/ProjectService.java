package org.bmserras.sot.data.service;

import org.bmserras.sot.data.db.project.ProjectDB;
import org.bmserras.sot.data.db.user.UserDB;
import org.bmserras.sot.data.db.user.UserProjectDB;
import org.bmserras.sot.data.domain.Project;
import org.bmserras.sot.data.domain.User;
import org.bmserras.sot.data.repository.project.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService implements AbstractService<Project> {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
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
        projectsDB.forEach(this::convertToProject);
        return projects;
    }

    @Override
    public Optional<Project> findById(String id) {
        Optional<ProjectDB> byId = projectRepository.findById(id);
        return byId.map(this::convertToProject);
    }

    @Override
    public Optional<Project> findByName(String name) {
        Optional<ProjectDB> byName = projectRepository.findByName(name);
        return byName.map(this::convertToProject);
    }

    @Override
    public void save(Project project) {
        if (project == null) return;
        ProjectDB projectDB = new ProjectDB(project.getId(), project.getName());
        projectRepository.save(projectDB);
    }

    @Override
    public void delete(Project project) {
        ProjectDB projectDB = new ProjectDB(project.getId(), project.getName());
        projectRepository.delete(projectDB);
    }

    private Project convertToProject(ProjectDB projectDB) {
        return new Project(projectDB.getIdentifier(), projectDB.getName());
    }
}
