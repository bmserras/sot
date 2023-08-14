package org.bmserras.sot.data.db.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.bmserras.sot.data.db.AbstractEntity;

@Entity
@Table(name = "user_account")
public class User extends AbstractEntity {

    @Column(unique = true)
    private String username;
    private String passwordHash;

    public User() {

    }

    public User(String username, String passwordHash) {
        super();
        this.username = username;
        this.passwordHash = passwordHash;
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

}
