package com.project.TFIBackendSpringBoot.controller;

import com.project.TFIBackendSpringBoot.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {


    private AppUserService userService;

    @Autowired
    public UserController(AppUserService userService){
        this.userService=userService;
    }

    @GetMapping("/home")
    public String home(){
        return "<h1> Welcome! </h1>";
    }
}
