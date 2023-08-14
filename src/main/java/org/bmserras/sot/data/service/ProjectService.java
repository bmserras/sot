package org.bmserras.sot.data.service;

import org.bmserras.sot.data.db.project.Project;
import org.bmserras.sot.data.repository.project.ProjectRepository;
import org.springframework.stereotype.Service;

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
        if (filter == null || filter.isEmpty()) {
            return projectRepository.findAll();
        } else {
            return projectRepository.search(filter);
        }
    }

    @Override
    public Optional<Project> findById(String id) {
        return projectRepository.findById(id);
    }

    @Override
    public Optional<Project> findByName(String name) {
        return Optional.ofNullable(projectRepository.findByName(name));
    }

    @Override
    public void save(Project item) {
        if (item == null) {
            return;
        }
        projectRepository.save(item);
    }

    @Override
    public void delete(Project project) {
        projectRepository.delete(project);
    }
}
