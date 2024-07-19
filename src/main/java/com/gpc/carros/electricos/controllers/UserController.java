package com.gpc.carros.electricos.controllers;

import com.gpc.carros.electricos.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class UserController {
    @Autowired
    private UserService userService;


}
