package com.microservice.account_service.model;

import java.util.Date;

import org.hibernate.usertype.UserType;

import com.microservice.account_service.common.Gender;
import com.microservice.account_service.common.UserStatus;

public class User {
    public User(String email, String firstName, String lastName, Date dateOfBirth, Gender gender, String phone,
            String username, String password, UserType type, UserStatus status, Long roles,
            Long groups) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.phone = phone;
        this.username = username;
        this.password = password;
        this.type = type;
        this.status = status;
        this.roles = roles;
        this.groups = groups;
    }

    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private Gender gender;
    private String phone;
    private String email;
    private String username;
    private String password;
    private UserType type;
    private UserStatus status;
    private Long roles;
    private Long groups;
    
}
