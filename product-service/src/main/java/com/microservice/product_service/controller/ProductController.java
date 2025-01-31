package com.microservice.product_service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("")
public class ProductController {

    @GetMapping("/product")
    @ResponseBody
    public String TestVerifyAuth() {
        return "Auth working !";
    }
}
