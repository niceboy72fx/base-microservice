package com.microservice.authentication_service.service.implement;

import com.microservice.authentication_service.repositories.UserRepository;
import com.microservice.authentication_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplement implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetailsService userDetailsService() {
        return userRepository::findByUsername;
    }
}
