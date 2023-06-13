package org.cefet.models;

import org.cefet.enums.UserRole;

import java.util.Date;

public class Member extends User{
    public Member(String name, String lastName, String email, Date date, String password, UserRole userRole) {
        super(name, lastName, email, date, password, userRole);
    }

    public Member() {}
}
