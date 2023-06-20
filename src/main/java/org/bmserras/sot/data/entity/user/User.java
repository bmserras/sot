package org.bmserras.sot.data.entity.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.bmserras.sot.data.entity.AbstractEntity;

@Entity
@Table(name = "userr")
public class User extends AbstractEntity {

    @Column(unique = true)
    private String username;
    private String passwordHash;
    private UserRole role;

    public User() {

    }

    public User(String username, String passwordHash, UserRole role) {
        super("");
        this.username = username;
        this.passwordHash = passwordHash;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    @Override
    public String getPath() {
        return "user/";
    }
}
