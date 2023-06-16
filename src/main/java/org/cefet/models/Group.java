package org.cefet.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Group extends EntityBase {
    private String name;
    private Date date;

    private List<User> users = new ArrayList<>();

    public Group(String name, Date date, List<User> users) {
        this.name = name;
        this.date = date;
        this.users = users;
    }

    public Group() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
