package com.gpc.carros.electricos.services;

import com.gpc.carros.electricos.model.User;
import com.gpc.carros.electricos.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Repository
public class UserService {
    @Autowired
    private UserRepository userRepository;

}
