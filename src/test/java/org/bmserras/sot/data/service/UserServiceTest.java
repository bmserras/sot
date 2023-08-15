package org.bmserras.sot.data.service;

import org.bmserras.sot.data.db.user.UserDB;
import org.bmserras.sot.data.domain.Project;
import org.bmserras.sot.data.domain.Synoptic;
import org.bmserras.sot.data.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    ProjectService projectService;

    @Autowired
    SynopticService synopticService;

    @Test
    public void testFindByName() {
        deleteAll();

        User user = new User(1L, "U1", "pass");
        Project project1 = new Project(1L, "P1");
        Project project2 = new Project(2L, "P2");
        Project project3 = new Project(3L, "P3");
        user.addProjects(project1, project2, project3);

        userService.save(user);

        Optional<User> savedUserOp = userService.findByName("U1");
        assertTrue(savedUserOp.isPresent());
        User savedUser = savedUserOp.get();
    }

    @Test
    public void testSave() {
        deleteAll();

        User user = new User(1L, "U1", "pass");
        Project project1 = new Project(1L, "P1");
        Project project2 = new Project(2L, "P2");
        Project project3 = new Project(3L, "P3");
        user.addProjects(project1, project2, project3);

        userService.save(user);

        Optional<User> savedUserOp = userService.findByName("U1");
        assertTrue(savedUserOp.isPresent());
        User savedUser = savedUserOp.get();

        assertEquals(user.getId(), savedUser.getId());
        assertEquals(user.getUsername(), savedUser.getUsername());
        assertEquals(user.getPasswordHash(), savedUser.getPasswordHash());

        List<Project> savedProjects = savedUser.getProjects();

        Project savedProject1 = savedProjects.get(0);
        assertEquals(project1.getId(), savedProject1.getId());
        assertEquals(project1.getName(), savedProject1.getName());

        Project savedProject2 = savedProjects.get(1);
        assertEquals(project2.getId(), savedProject2.getId());
        assertEquals(project2.getName(), savedProject2.getName());

        Project savedProject3 = savedProjects.get(2);
        assertEquals(project3.getId(), savedProject3.getId());
        assertEquals(project3.getName(), savedProject3.getName());
    }

    @Test
    public void testDelete() {
        deleteAll();

        User user = new User(1L, "U1", "pass");
        Project project1 = new Project(1L, "P1");
        Project project2 = new Project(2L, "P2");
        Project project3 = new Project(3L, "P3");
        user.addProjects(project1, project2, project3);

        userService.save(user);

        userService.delete(user);
    }


    private void deleteAll() {
        userService.deleteAll();
        projectService.deleteAll();
        synopticService.deleteAll();
    }
}
