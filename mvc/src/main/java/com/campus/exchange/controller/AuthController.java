package com.campus.exchange.controller;

import com.campus.exchange.model.User;
import com.campus.exchange.service.JWTService;
import com.campus.exchange.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.rmi.runtime.Log;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping(value = {"/auth"})
public class AuthController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private JWTService jwtService;

    @Autowired
    private UserService userService;

    private String errorMsg = "The email or password is not correct.";
    private String tokenKeyWord = "Authorization";
    private String tokenType = "Bearer";


    /* http://localhost:8080/auth POST:body, raw, json
    {
    "email":"dwang@training.ascendingdc.com",
    "password":"123456789"
    }
    */
    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity authenticate(@RequestBody User user) {
        String token = "";

        try {
            logger.debug(user.toString());
            User u = userService.getUserByCredentials(user.getEmail(), user.getPassword());
            if (u == null) {
                return ResponseEntity.status(HttpServletResponse.SC_NON_AUTHORITATIVE_INFORMATION).
                        body(errorMsg);
            }
            logger.debug(u.toString());
            token = jwtService.generateToken(u);
        } catch (Exception e) {
            e.printStackTrace();
            String msg = e.getMessage();
            if (msg == null) {
                msg = "BAD REQUEST!";
            }
            logger.error(msg);
            return ResponseEntity.status(HttpServletResponse.SC_BAD_REQUEST).body(msg);
        }

        return ResponseEntity.status(HttpServletResponse.SC_OK).
                body(tokenKeyWord + ":" + tokenType + " " + token);
    }

    @RequestMapping(value = "/token", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity authenticate(@RequestParam String email, @RequestParam String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        logger.debug(user.toString());

        return authenticate(user);
    }


}

// http://localhost:8080/auth?username=dwang@gmail.com&password=1234 POST
    /*
    1. Validate username and password
    2. Generate JWToken
    3. return token
     */