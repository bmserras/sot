package org.bmserras.sot.data.service;

import org.bmserras.sot.data.entity.project.Project;
import org.bmserras.sot.data.repository.project.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> findAllProjects(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return projectRepository.findAll();
        } else {
            return projectRepository.search(stringFilter);
        }
    }

    public Optional<Project> findProjectById(String id) {
        return projectRepository.findById(id);
    }

    public Project findProjectByName(String name) {
        return projectRepository.findByName(name);
    }

    public void saveProject(Project project) {
        if (project == null) {
            return;
        }
        projectRepository.save(project);
    }

    public void deleteProject(Project project) {
        projectRepository.delete(project);
    }
}
