package org.bmserras.sot.data.domain;

import org.bmserras.sot.data.db.WidgetInstanceDB;
import org.bmserras.sot.data.db.project.ProjectDB;
import org.bmserras.sot.data.db.project.ProjectSynopticDB;
import org.bmserras.sot.data.db.project.ProjectWidgetDB;
import org.bmserras.sot.data.db.synoptic.SynopticDB;
import org.bmserras.sot.data.db.user.UserDB;
import org.bmserras.sot.data.db.user.UserProjectDB;
import org.bmserras.sot.data.db.user.UserWidgetDB;
import org.bmserras.sot.data.db.widget.ReaderDB;
import org.bmserras.sot.data.db.widget.WidgetDB;
import org.bmserras.sot.data.db.widget.WidgetImageDB;
import org.bmserras.sot.data.domain.readers.Reader;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static WidgetImage toImage(WidgetImageDB widgetImageDB) {
        return new WidgetImage(widgetImageDB.getIdentifier(), widgetImageDB.getName(), widgetImageDB.getData(), widgetImageDB.getIconName());
    }

    public static WidgetImageDB toImageDB(WidgetImage widgetImage) {
        return new WidgetImageDB(widgetImage.getId(), widgetImage.getType(), widgetImage.getData(), widgetImage.getIconName());
    }

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
        List<ProjectSynopticDB> synoptics = projectDB.getSynoptics();
        List<ProjectWidgetDB> widgets = projectDB.getWidgets();
        synoptics.forEach(synoptic -> {
            SynopticDB synopticDB = synoptic.getSynoptic();
            project.addSynoptic(toSynoptic(synopticDB));
        });
        widgets.forEach(widget -> {
            WidgetDB widgetDB = widget.getWidget();
            project.addWidget(toWidget(widgetDB));
        });
        return project;
    }

    public static ProjectDB toProjectDB(Project project) {
        ProjectDB projectDB = new ProjectDB(project.getId(), project.getName());
        List<Synoptic> synoptics = project.getSynoptics();
        List<Widget> widgets = project.getWidgets();
        synoptics.forEach(synoptic -> projectDB.addSynoptic(toSynopticDB(synoptic)));
        widgets.forEach(widget -> projectDB.addWidget(toWidgetDB(widget)));
        return projectDB;
    }

    public static Synoptic toSynoptic(SynopticDB synopticDB) {
        Synoptic synoptic =  new Synoptic(synopticDB.getIdentifier(), synopticDB.getName(), synopticDB.getGrid());
        List<WidgetInstanceDB> widgetInstancesDB = synopticDB.getWidgetInstances();
        widgetInstancesDB.forEach(widgetInstanceDB -> {
            synoptic.addWidgetInstance(toWidgetInstance(widgetInstanceDB));
        });
        return synoptic;
    }

    public static SynopticDB toSynopticDB(Synoptic synoptic) {
        SynopticDB synopticDB = new SynopticDB(synoptic.getId(), synoptic.getName(), synoptic.getGrid());
        List<WidgetInstance> widgetInstances = synoptic.getWidgetInstances();
        widgetInstances.forEach(widgetInstance -> {
            synopticDB.addWidgetInstance(toWidgetInstanceDB(widgetInstance));
        });
        return synopticDB;
    }

    public static Widget toWidget(WidgetDB widgetDB) {
        Widget widget =  new Widget(widgetDB.getIdentifier(), widgetDB.getName());
        List<ReaderDB> readersDB = widgetDB.getReaders();
        readersDB.forEach(readerDB -> {
            widget.addReaders(toReader(readerDB));
        });
        widget.setImage(toImage(widgetDB.getImage()));
        return widget;
    }

    public static WidgetDB toWidgetDB(Widget widget) {
        WidgetDB widgetDB = new WidgetDB(widget.getId(), widget.getName(), new ArrayList<>(), new ArrayList<>(), new WidgetImageDB(), widget.getBorderWidth(), widget.getBorderStyle());
        List<Reader> readers = widget.getReaders();
        readers.forEach(reader -> widgetDB.addReader(toReaderDB(reader)));
        List<Widget> widgets = widget.getWidgets();
        widgets.forEach(innerWidget -> widgetDB.addInnerWidget(toWidgetDB(innerWidget)));
        widgetDB.setImage(toImageDB(widget.getImage()));
        return widgetDB;
    }

    public static Reader toReader(ReaderDB readerDB) {
//        if (readerDB instanceof GaugeDB gaugeDB) {
//            return null/*new Gauge(gaugeDB.getIdentifier(), gaugeDB.getName(), gaugeDB.getMin(), gaugeDB.getMax())*/;
//        }
//        else if (readerDB instanceof SolidGaugeDB solidGaugeDB) {
//            return null/*new SolidGauge(solidGaugeDB.getIdentifier(), solidGaugeDB.getName(), solidGaugeDB.getMin(),
//                    solidGaugeDB.getMax(), solidGaugeDB.getColor())*/;
//        }
//        return null;
        return new Reader(readerDB.getIdentifier(), readerDB.getName(), readerDB.getUnit(), readerDB.getUnitMin(), readerDB.getUnitMax());
    }

    public static ReaderDB toReaderDB(Reader reader) {
//        if (reader instanceof Gauge gauge) {
//            return null/*new GaugeDB(gauge.getId(), gauge.getName(), null, gauge.getMin(), gauge.getMax())*/;
//        } else if (reader instanceof SolidGauge solidGauge) {
//            return null/*new SolidGaugeDB(solidGauge.getId(), solidGauge.getName(), null, solidGauge.getMin(),
//                    solidGauge.getMax(), solidGauge.getColor())*/;
//        }
//        return null;
        return new ReaderDB(reader.getId(), reader.getName(), reader.getUnit(), reader.getUnitMin(), reader.getUnitMax());
    }

    public static WidgetInstance toWidgetInstance(WidgetInstanceDB widgetInstanceDB) {
        return new WidgetInstance(widgetInstanceDB.getIdentifier(), widgetInstanceDB.getName(), toWidget(widgetInstanceDB.getWidget()), widgetInstanceDB.getPosX(), widgetInstanceDB.getPosY());
    }

    public static WidgetInstanceDB toWidgetInstanceDB(WidgetInstance widgetInstance) {
        return new WidgetInstanceDB(widgetInstance.getId(), widgetInstance.getName(), toWidgetDB(widgetInstance.getWidget()), widgetInstance.getPosX(), widgetInstance.getPosY());
    }
}
