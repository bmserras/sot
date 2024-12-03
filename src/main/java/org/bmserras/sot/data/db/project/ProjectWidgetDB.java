package org.bmserras.sot.data.db.project;

import jakarta.persistence.*;
import org.bmserras.sot.data.db.widget.WidgetDB;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "project_widget")
@IdClass(ProjectWidgetDB.ProjectWidgetId.class)
public class ProjectWidgetDB implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "project_identifier", referencedColumnName = "identifier")
    private ProjectDB project;

    @Id
    @ManyToOne
    @JoinColumn(name = "widget_identifier", referencedColumnName = "identifier")
    private WidgetDB widget;

    public ProjectWidgetDB() {
    }

    public ProjectWidgetDB(ProjectDB project, WidgetDB widget) {
        this.project = project;
        this.widget = widget;
    }

    public ProjectDB getProject() {
        return project;
    }

    public void setProject(ProjectDB projectDB) {
        this.project = projectDB;
    }

    public WidgetDB getWidget() {
        return widget;
    }

    public void setWidget(WidgetDB widgetDB) {
        this.widget = widgetDB;
    }

    @Override
    public String toString() {
        return "ProjectWidget{" +
                ", widget=" + widget +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectWidgetDB that = (ProjectWidgetDB) o;
        return project.equals(that.project) && widget.equals(that.widget);
    }

    @Override
    public int hashCode() {
        return Objects.hash(project, widget);
    }

    public static class ProjectWidgetId implements Serializable {

        private long project;
        private long widget;

        public long getProject() {
            return project;
        }

        public void setProject(long project) {
            this.project = project;
        }

        public long getWidget() {
            return widget;
        }

        public void setWidget(long widget) {
            this.widget = widget;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ProjectWidgetId that = (ProjectWidgetId) o;
            return project == that.project && widget == that.widget;
        }

        @Override
        public int hashCode() {
            return Objects.hash(project, widget);
        }
    }
}
