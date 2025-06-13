package com.microservice.account_service.controller.request;

import com.microservice.account_service.common.Gender;
import com.microservice.account_service.common.UserType;
import lombok.*;


import java.io.Serializable;
import java.util.Date;

@Getter
public class UserCreationRequestDTO implements Serializable {
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private Gender gender;
    private String phone;
    private String email;
    private String username;
    private String password;
    private UserType type;
    //private UserStatus status;
//    private Date createdAt;
//    private Date updatedAt;
}
