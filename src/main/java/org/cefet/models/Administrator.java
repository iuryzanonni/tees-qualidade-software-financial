package org.cefet.models;


import org.cefet.enums.UserRole;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Administrator extends User{

    private List<Group> groups = new ArrayList<>();

    public Administrator(String name, String lastName, String email, String password, UserRole userRole,
                         List<Investment> investments, List<Invoice> invoices, List<Group> groups) {
        super(name, lastName, email, password, userRole, investments, invoices);
        this.groups = groups;
    }

    public Administrator() {}

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
}
