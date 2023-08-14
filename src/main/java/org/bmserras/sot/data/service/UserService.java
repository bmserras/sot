package org.bmserras.sot.data.service;

import org.bmserras.sot.data.db.user.User;
import org.bmserras.sot.data.repository.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements AbstractService<User> {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return findAll("");
    }

    @Override
    public List<User> findAll(String filter) {
        if (filter == null || filter.isEmpty()) {
            return userRepository.findAll();
        } else {
            return userRepository.search(filter);
        }
    }

    @Override
    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByName(String name) {
        return Optional.ofNullable(userRepository.findByName(name));
    }

    @Override
    public void save(User item) {
        if (item == null) {
            return;
        }
        userRepository.save(item);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }
}
