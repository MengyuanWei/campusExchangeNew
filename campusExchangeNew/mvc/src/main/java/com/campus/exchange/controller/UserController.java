package com.campus.exchange.controller;

import com.campus.exchange.model.User;
import com.campus.exchange.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"/users"})
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @GetMapping(value = "/{email}", produces = "application/json")
    public User getUserBy(@PathVariable String email) {
        User user = userService.getUserByEmail(email);

        if (user != null) {
            logger.info(user.toString());
        }

        return user;
    }
}
