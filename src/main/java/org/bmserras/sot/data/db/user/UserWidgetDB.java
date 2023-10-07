package org.bmserras.sot.data.db.user;

import jakarta.persistence.*;
import org.bmserras.sot.data.db.widget.WidgetDB;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "user_widget")
@IdClass(UserWidgetDB.UserWidgetId.class)
public class UserWidgetDB {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_identifier", referencedColumnName = "identifier")
    private UserDB user;

    @Id
    @ManyToOne
    @JoinColumn(name = "widget_identifier", referencedColumnName = "identifier")
    private WidgetDB widget;

    public UserWidgetDB() {
    }

    public UserWidgetDB(UserDB user, WidgetDB widget) {
        this.user = user;
        this.widget = widget;
    }

    public UserDB getUser() {
        return user;
    }

    public void setUser(UserDB userDB) {
        this.user = userDB;
    }

    public WidgetDB getWidget() {
        return widget;
    }

    public void setWidget(WidgetDB widgetDB) {
        this.widget = widgetDB;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserWidgetDB that = (UserWidgetDB) o;
        return Objects.equals(user, that.user) && Objects.equals(widget, that.widget);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, widget);
    }

    public static class UserWidgetId implements Serializable {

        private long user;
        private long widget;

        public long getUser() {
            return user;
        }

        public void setUser(long user) {
            this.user = user;
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
            UserWidgetId that = (UserWidgetId) o;
            return user == that.user && widget == that.widget;
        }

        @Override
        public int hashCode() {
            return Objects.hash(user, widget);
        }
    }

}
