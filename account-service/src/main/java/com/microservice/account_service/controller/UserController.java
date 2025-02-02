package com.microservice.account_service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class UserController {

    @PostMapping("/register")
    public void registerAccount() {

    }

    @PostMapping("/update-account")
    public void updateAccount() {

    }

    @PostMapping("/get-list-account")
    public void getListAccount() {

    }

    @PostMapping("/account-detail")
    public void getDetailAccount(@PathVariable uuidAccount) {

    }

    @PostMapping("/account-detail")
    public void deleteAccount(@PathVariable uuidAccount) {

    }
}
