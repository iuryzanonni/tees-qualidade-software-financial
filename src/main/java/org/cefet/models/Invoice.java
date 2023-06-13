package org.cefet.models;

import java.util.Date;

public class Invoice extends EntityBase {
    private String name;
    private Date createDate;
    private Date dueDate;

    public Invoice(String name, Date createDate, Date dueDate) {
        this.name = name;
        this.createDate = createDate;
        this.dueDate = dueDate;
    }

    public Invoice() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
}
