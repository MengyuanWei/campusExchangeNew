package com.campus.exchange.controller;

import com.campus.exchange.model.Role;
import com.campus.exchange.model.User;
import com.campus.exchange.service.CustomerService;
import com.campus.exchange.service.RoleService;
import com.campus.exchange.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.print.attribute.standard.Media;
import java.awt.*;
import java.util.List;

@RestController
@RequestMapping(value = {"/usersandroles"})
public class UserRoleController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    // http://localhost:8080/users GET
    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> findAllUsers(){
        return userService.getAllUsers();
    }

    // http://localhost:8080/roles GET
    @GetMapping(value = "/roles", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Role> findAllRoles(){
        return roleService.getAllRoles();
    }


}
