package com.campus.exchange.repository;

import com.campus.exchange.model.Role;

import java.util.List;

public interface RoleDao {
//    Role getRoleByName(String name);

    List<Role> findAllRoles();
}
