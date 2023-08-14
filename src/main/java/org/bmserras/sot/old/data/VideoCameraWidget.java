package org.bmserras.sot.old.data;

import jakarta.persistence.Entity;

@Entity
public class VideoCameraWidget extends Widget {

    private String username;
    private String password;

    public VideoCameraWidget() {
    }

    @Override
    public String getPath() {
        return "widget/";
    }

    public VideoCameraWidget(int identifier, String name, String username, String password) {
        super(identifier, name);
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "VideoCameraWidget{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                "} " + super.toString();
    }
}
