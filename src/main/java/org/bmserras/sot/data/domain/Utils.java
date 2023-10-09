package org.bmserras.sot.data.domain;

import org.bmserras.sot.data.db.project.ProjectDB;
import org.bmserras.sot.data.db.project.ProjectSynopticDB;
import org.bmserras.sot.data.db.synoptic.SynopticDB;
import org.bmserras.sot.data.db.user.UserDB;
import org.bmserras.sot.data.db.user.UserProjectDB;
import org.bmserras.sot.data.db.user.UserWidgetDB;
import org.bmserras.sot.data.db.widget.GaugeDB;
import org.bmserras.sot.data.db.widget.SolidGaugeDB;
import org.bmserras.sot.data.db.widget.ValueReaderDB;
import org.bmserras.sot.data.db.widget.WidgetDB;
import org.bmserras.sot.data.domain.readers.Gauge;
import org.bmserras.sot.data.domain.readers.SolidGauge;
import org.bmserras.sot.data.domain.readers.ValueReader;

import java.util.List;

public class Utils {

    public static User toUser(UserDB userDB) {
        User user = new User(userDB.getIdentifier(), userDB.getUsername(), userDB.getPasswordHash());
        List<UserProjectDB> projects = userDB.getProjects();
        projects.forEach(project -> {
            ProjectDB projectDB = project.getProject();
            user.addProject(toProject(projectDB));
        });
        List<UserWidgetDB> widgets = userDB.getWidgets();
        widgets.forEach(widget -> {
            WidgetDB widgetDB = widget.getWidget();
            user.addWidget(toWidget(widgetDB));
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
        List<Widget> widgets = user.getWidgets();
        widgets.forEach(widget -> {
            WidgetDB widgetDB = toWidgetDB(widget);
            userDB.addWidget(widgetDB);
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
        Widget widget =  new Widget(widgetDB.getIdentifier(), widgetDB.getName());
        List<ValueReaderDB> readersDB = widgetDB.getReaders();
        readersDB.forEach(readerDB -> {
            widget.addReaders(toReader(readerDB));
        });
        return widget;
    }

    public static WidgetDB toWidgetDB(Widget widget) {
        WidgetDB widgetDB = new WidgetDB(widget.getId(), widget.getName());
        List<ValueReader> readers = widget.getReaders();
        readers.forEach(reader -> widgetDB.addReader(toReaderDB(reader)));
        return widgetDB;
    }

    public static ValueReader toReader(ValueReaderDB readerDB) {
        if (readerDB instanceof GaugeDB gaugeDB) {
            return new Gauge(gaugeDB.getIdentifier(), gaugeDB.getName(), gaugeDB.getMin(), gaugeDB.getMax());
        }
        else if (readerDB instanceof SolidGaugeDB solidGaugeDB) {
            return new SolidGauge(solidGaugeDB.getIdentifier(), solidGaugeDB.getName(), solidGaugeDB.getMin(),
                    solidGaugeDB.getMax(), solidGaugeDB.getColor());
        }
        return null;
    }

    public static ValueReaderDB toReaderDB(ValueReader reader) {
        if (reader instanceof Gauge gauge) {
            return new GaugeDB(gauge.getId(), gauge.getName(), null, gauge.getMax(), gauge.getMin());
        } else if (reader instanceof SolidGauge solidGauge) {
            return new SolidGaugeDB(solidGauge.getId(), solidGauge.getName(), null, solidGauge.getMin(),
                    solidGauge.getMax(), solidGauge.getColor());
        }
        return null;
    }
}
