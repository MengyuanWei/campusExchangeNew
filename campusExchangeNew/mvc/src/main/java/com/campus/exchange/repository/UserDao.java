package com.campus.exchange.repository;

import com.campus.exchange.model.Customer;
import com.campus.exchange.model.User;

import java.util.List;

public interface UserDao {
//    User save(User user);
//
//    User findById(Long Id);
//
//    void delete(User user);

    List<User> findAllUsers();
    User getById(Long id);
    User getUserByEmail(String email);
    User getUserByCredentials(String email, String password);


}
