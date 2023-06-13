package org.cefet.models;

import org.cefet.enums.UserRole;

import java.util.Date;

public abstract class User extends EntityBase {
    private String name;
    private String lastName;
    private String email;
    private Date date;
    private String password;
    private UserRole userRole;

    public User(String name, String lastName, String email, Date date, String password, UserRole userRole) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.date = date;
        this.password = password;
        this.userRole = userRole;
    }

    public User() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
}
