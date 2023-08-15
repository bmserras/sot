package org.bmserras.sot.data.domain;

import org.bmserras.sot.data.db.project.ProjectDB;
import org.bmserras.sot.data.db.project.ProjectSynopticDB;
import org.bmserras.sot.data.db.synoptic.SynopticDB;
import org.bmserras.sot.data.db.user.UserDB;
import org.bmserras.sot.data.db.user.UserProjectDB;
import org.bmserras.sot.data.domain.Project;
import org.bmserras.sot.data.domain.Synoptic;
import org.bmserras.sot.data.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.bmserras.sot.data.domain.Utils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
public class UtilsTest {

    @Test
    public void testToUser() {
        UserDB userDB = new UserDB(1L, "U1", "pass");
        ProjectDB projectDB1 = new ProjectDB(1L, "P1");
        ProjectDB projectDB2 = new ProjectDB(2L, "P2");
        ProjectDB projectDB3 = new ProjectDB(3L, "P3");
        userDB.addProjects(projectDB1, projectDB2, projectDB3);

        User user = toUser(userDB);

        assertEquals(userDB.getIdentifier(), user.getId());
        assertEquals(userDB.getUsername(), user.getUsername());
        assertEquals(userDB.getPasswordHash(), user.getPasswordHash());

        List<Project> projects = user.getProjects();
        assertEquals(projects.size(), 3);

        Project project1 = projects.get(0);
        assertEquals(projectDB1.getIdentifier(), project1.getId());
        assertEquals(projectDB1.getName(), project1.getName());

        Project project2 = projects.get(1);
        assertEquals(projectDB2.getIdentifier(), project2.getId());
        assertEquals(projectDB2.getName(), project2.getName());

        Project project3 = projects.get(2);
        assertEquals(projectDB3.getIdentifier(), project3.getId());
        assertEquals(projectDB3.getName(), project3.getName());
    }

    @Test
    public void testToUserDB() {
        User user = new User(1L, "U1", "pass");
        Project project1 = new Project(1L, "P1");
        Project project2 = new Project(2L, "P2");
        Project project3 = new Project(3L, "P3");
        user.addProjects(project1, project2, project3);

        UserDB userDB = toUserDB(user);

        assertEquals(user.getId(), userDB.getIdentifier());
        assertEquals(user.getUsername(), userDB.getUsername());
        assertEquals(user.getPasswordHash(), userDB.getPasswordHash());

        List<UserProjectDB> joins = userDB.getProjects();
        assertEquals(joins.size(), 3);

        ProjectDB projectDB1 = joins.get(0).getProject();
        assertEquals(project1.getId(), projectDB1.getIdentifier());
        assertEquals(project1.getName(), projectDB1.getName());

        ProjectDB projectDB2 = joins.get(1).getProject();
        assertEquals(project2.getId(), projectDB2.getIdentifier());
        assertEquals(project2.getName(), projectDB2.getName());

        ProjectDB projectDB3 = joins.get(2).getProject();
        assertEquals(project3.getId(), projectDB3.getIdentifier());
        assertEquals(project3.getName(), projectDB3.getName());
    }

    @Test
    public void testToProject() {
        ProjectDB projectDB = new ProjectDB(1L, "P1");
        SynopticDB synopticDB1 = new SynopticDB(1L, "S1");
        SynopticDB synopticDB2 = new SynopticDB(2L, "S2");
        projectDB.addSynoptics(synopticDB1, synopticDB2);

        Project project = toProject(projectDB);

        assertEquals(projectDB.getIdentifier(), project.getId());
        assertEquals(projectDB.getName(), project.getName());

        List<Synoptic> synoptics = project.getSynoptics();
        assertEquals(synoptics.size(), 2);

        Synoptic synoptic1 = synoptics.get(0);
        assertEquals(synopticDB1.getIdentifier(), synoptic1.getId());
        assertEquals(synopticDB1.getName(), synoptic1.getName());

        Synoptic synoptic2 = synoptics.get(1);
        assertEquals(synopticDB2.getIdentifier(), synoptic2.getId());
        assertEquals(synopticDB2.getName(), synoptic2.getName());
    }

    @Test
    public void testToProjectDB() {
        Project project = new Project(1L, "P1");
        Synoptic synoptic1 = new Synoptic(1L, "S1");
        Synoptic synoptic2 = new Synoptic(2L, "S2");
        Synoptic synoptic3 = new Synoptic(3L, "S3");
        project.addSynoptics(synoptic1, synoptic2, synoptic3);

        ProjectDB projectDB = toProjectDB(project);

        assertEquals(project.getId(), projectDB.getIdentifier());
        assertEquals(project.getName(), projectDB.getName());

        List<ProjectSynopticDB> joins = projectDB.getSynoptics();
        assertEquals(joins.size(), 3);

        SynopticDB synopticDB1 = joins.get(0).getSynoptic();
        assertEquals(synoptic1.getId(), synopticDB1.getIdentifier());
        assertEquals(synoptic1.getName(), synopticDB1.getName());

        SynopticDB synopticDB2 = joins.get(1).getSynoptic();
        assertEquals(synoptic2.getId(), synopticDB2.getIdentifier());
        assertEquals(synoptic2.getName(), synopticDB2.getName());

        SynopticDB synopticDB3 = joins.get(2).getSynoptic();
        assertEquals(synoptic3.getId(), synopticDB3.getIdentifier());
        assertEquals(synoptic3.getName(), synopticDB3.getName());
    }

    @Test
    public void testToSynoptic() {
        SynopticDB synopticDB = new SynopticDB(1L, "S1");
        Synoptic synoptic = toSynoptic(synopticDB);

        assertEquals(synopticDB.getIdentifier(), synoptic.getId());
        assertEquals(synopticDB.getName(), synoptic.getName());
    }

    @Test
    public void testToSynopticDB() {
        Synoptic synoptic = new Synoptic(1L, "S1");
        SynopticDB synopticDB = toSynopticDB(synoptic);

        assertEquals(synoptic.getId(), synopticDB.getIdentifier());
        assertEquals(synoptic.getName(), synopticDB.getName());
    }

}
