package com.campus.exchange.service;

import com.campus.exchange.model.User;
import com.campus.exchange.repository.CustomerDao;
import com.campus.exchange.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public List<User> getAllUsers(){
        List<User> userList = userDao.findAllUsers();
        return userList;
    }

    public User getUserByCredentials(String email, String password) {
        return userDao.getUserByCredentials(email, password);
    }

    public User getById(Long id){
        return userDao.getById(id);
    }

    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

}
