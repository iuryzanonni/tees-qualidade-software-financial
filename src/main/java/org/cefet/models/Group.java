package org.cefet.models;

import java.util.Date;

public class Group extends EntityBase {
    private String name;
    private Date date;

    public Group(String name, Date date) {
        this.name = name;
        this.date = date;
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
}
