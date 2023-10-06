package org.bmserras.sot.data.domain;

import org.bmserras.sot.data.db.project.ProjectDB;
import org.bmserras.sot.data.db.project.ProjectSynopticDB;
import org.bmserras.sot.data.db.synoptic.SynopticDB;
import org.bmserras.sot.data.db.user.UserDB;
import org.bmserras.sot.data.db.user.UserProjectDB;
import org.bmserras.sot.data.db.widget.WidgetDB;

import java.util.List;

public class Utils {

    public static User toUser(UserDB userDB) {
        User user = new User(userDB.getIdentifier(), userDB.getUsername(), userDB.getPasswordHash());
        List<UserProjectDB> joins = userDB.getProjects();
        joins.forEach(join -> {
            ProjectDB projectDB = join.getProject();
            user.addProject(toProject(projectDB));
        });
        return user;
    }

    public static UserDB toUserDB(User user) {
        UserDB userDB = new UserDB(user.getId(), user.getUsername(), user.getPasswordHash());
        List<Project> projects = user.getProjects();
        projects.forEach(project -> {
            ProjectDB projectDB = toProjectDB(project);
            userDB.addProject(projectDB);
        });
        return userDB;
    }

    public static Project toProject(ProjectDB projectDB) {
        Project project = new Project(projectDB.getIdentifier(), projectDB.getName());
        List<ProjectSynopticDB> joins = projectDB.getSynoptics();
        joins.forEach(join -> {
            SynopticDB synopticDB = join.getSynoptic();
            project.addSynoptic(toSynoptic(synopticDB));
        });
        return project;
    }

    public static ProjectDB toProjectDB(Project project) {
        ProjectDB projectDB = new ProjectDB(project.getId(), project.getName());
        List<Synoptic> synoptics = project.getSynoptics();
        synoptics.forEach(synoptic -> projectDB.addSynoptic(toSynopticDB(synoptic)));
        return projectDB;
    }

    public static Synoptic toSynoptic(SynopticDB synopticDB) {
        return new Synoptic(synopticDB.getIdentifier(), synopticDB.getName());
    }

    public static SynopticDB toSynopticDB(Synoptic synoptic) {
        return new SynopticDB(synoptic.getId(), synoptic.getName());
    }

    public static Widget toWidget(WidgetDB widgetDB) {
        return new Widget(widgetDB.getIdentifier(), widgetDB.getName());
    }

    public static WidgetDB toWidgetDB(Widget widget) {
        return new WidgetDB(widget.getId(), widget.getName());
    }
}
