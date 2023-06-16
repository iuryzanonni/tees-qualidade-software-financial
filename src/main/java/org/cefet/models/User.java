package org.cefet.models;

import org.cefet.enums.UserRole;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class User extends EntityBase {
    private String name;
    private String lastName;
    private String email;
    private String password;
    private UserRole userRole;

    private List<Investment> investments = new ArrayList<>();

    public User(String name, String lastName, String email, String password, UserRole userRole, List<Investment> investments) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.userRole = userRole;
        this.investments = investments;
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

    public List<Investment> getInvestments() {
        return investments;
    }

    public void setInvestments(List<Investment> investments) {
        this.investments = investments;
    }
}
