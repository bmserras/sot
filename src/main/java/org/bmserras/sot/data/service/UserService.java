package org.bmserras.sot.data.service;

import org.bmserras.sot.data.db.project.ProjectDB;
import org.bmserras.sot.data.db.user.UserDB;
import org.bmserras.sot.data.db.user.UserProjectDB;
import org.bmserras.sot.data.domain.Project;
import org.bmserras.sot.data.domain.User;
import org.bmserras.sot.data.repository.project.ProjectRepository;
import org.bmserras.sot.data.repository.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements AbstractService<User> {

    private final UserRepository userRepository;
    private final ProjectService projectService;

    public UserService(UserRepository userRepository, ProjectService projectService) {
        this.userRepository = userRepository;
        this.projectService = projectService;
    }

    @Override
    public List<User> findAll() {
        return findAll("");
    }

    @Override
    public List<User> findAll(String filter) {
        List<User> users = new ArrayList<>();
        List<UserDB> usersDB = (filter == null || filter.isEmpty()) ? userRepository.findAll() : userRepository.search(filter);
        usersDB.forEach(this::convertToUser);
        return users;
    }

    @Override
    public Optional<User> findById(String id) {
        Optional<UserDB> byId = userRepository.findById(id);
        return byId.map(this::convertToUser);
    }

    @Override
    public Optional<User> findByName(String name) {
        Optional<UserDB> byName = userRepository.findByName(name);
        return byName.map(this::convertToUser);
    }

    @Override
    public void save(User user) {
        if (user == null) return;
        UserDB userDB = new UserDB(user.getId(), user.getUsername(), user.getPasswordHash());
        List<Project> projects = user.getProjects();
        projects.forEach(project -> {
            ProjectDB projectDB = new ProjectDB(project.getId(), project.getName());
            projectService.save(project);
            userDB.addProject(projectDB);
        });
        userRepository.save(userDB);
    }

    @Override
    public void delete(User user) {
        UserDB userDB = new UserDB(user.getId());
        userRepository.delete(userDB);
    }

    private User convertToUser(UserDB userDB) {
        List<Project> projects = new ArrayList<>();
        List<UserProjectDB> projectsDB = userDB.getProjects();
        projectsDB.forEach(projectDB -> {
            ProjectDB project = projectDB.getProject();
            projects.add(new Project(project.getIdentifier(), project.getName()));
        });
        return new User(userDB.getIdentifier(), userDB.getUsername(), userDB.getPasswordHash(), projects);
    }

}
