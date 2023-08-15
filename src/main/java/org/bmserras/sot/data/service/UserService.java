package org.bmserras.sot.data.service;

import org.bmserras.sot.data.db.user.UserDB;
import org.bmserras.sot.data.domain.User;
import org.bmserras.sot.data.domain.Utils;
import org.bmserras.sot.data.repository.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.bmserras.sot.data.domain.Utils.toUser;
import static org.bmserras.sot.data.domain.Utils.toUserDB;

@Service
@Transactional
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
        usersDB.forEach(userDB -> users.add(toUser(userDB)));
        return users;
    }

    @Override
    public Optional<User> findById(String id) {
        Optional<UserDB> byId = userRepository.findById(id);
        return byId.map(Utils::toUser);
    }

    @Override
    public Optional<User> findByName(String name) {
        Optional<UserDB> byName = userRepository.findByName(name);
        return byName.map(Utils::toUser);
    }

    @Override
    public void save(User user) {
        if (user == null) return;
        UserDB userDB = toUserDB(user);
        user.getProjects().forEach(projectService::save);
        userRepository.save(userDB);
    }

    @Override
    public void delete(User user) {
        UserDB userDB = toUserDB(user);
        userRepository.delete(userDB);
        user.getProjects().forEach(projectService::delete);
    }

    public void deleteAll() {
        userRepository.deleteAll();
    }
}
