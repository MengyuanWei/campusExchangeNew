package com.campus.exchange.service;

import com.campus.exchange.model.Role;
import com.campus.exchange.model.User;
import com.campus.exchange.repository.RoleDao;
import com.campus.exchange.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleDao roleDao;

    public List<Role> getAllRoles(){
        List<Role> roleList = roleDao.findAllRoles();
        return roleList;
    }
}
