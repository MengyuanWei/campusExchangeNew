package com.campus.exchange.repository;

import com.campus.exchange.model.Role;
import com.campus.exchange.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Role> findAllRoles(){
        String hql = "FROM Role";
        try(Session session = sessionFactory.openSession())
        {
            Query<Role> query = session.createQuery(hql);
            return query.list();
        }
    }
}
