package org.cefet.models;

import java.util.Date;

public class Invoice extends EntityBase {
    private String name;
    private Date dueDate;

    public Invoice(String name, Date dueDate) {
        this.name = name;
        this.dueDate = dueDate;
    }

    public Invoice() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
}
