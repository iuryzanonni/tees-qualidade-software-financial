package org.cefet.models;

import org.cefet.enums.UserRole;

import java.util.Date;
import java.util.List;

public class Member extends User{
    public Member(String name, String lastName, String email, String password, UserRole userRole, List<Investment> investments) {
        super(name, lastName, email, password, userRole, investments);
    }
    public Member() {}
}
