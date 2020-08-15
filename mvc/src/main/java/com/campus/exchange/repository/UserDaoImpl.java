package com.campus.exchange.repository;

import com.campus.exchange.model.Customer;
import com.campus.exchange.model.User;
import com.campus.exchange.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<User> findAllUsers(){
        String hql = "FROM User";
        try(Session session = sessionFactory.openSession()){
            Query<User> query = session.createQuery(hql);
            return query.list();
        }
    }

    @Override
    public User getById(Long id) {
        String hql = "FROM User u WHERE u.id=:Id";
        Session session = sessionFactory.openSession();
        try{
            Query<User> query = session.createQuery(hql);
            query.setParameter("Id", id);
            User result = query.uniqueResult();
            session.close();
            return result;
        }catch(HibernateException e){
            logger.error("Failure to retrieve data record", e);
            session.close();
            return null;
        }
    }

    @Override
    public User getUserByEmail(String email) {
        String hql = "FROM User as u " +
                "left join fetch u.roles " +
                "where lower(u.email) = :email";
        Session session = sessionFactory.openSession();
        try {
            Query<User> query = session.createQuery(hql);
            query.setParameter("email", email.toLowerCase());
            User result = query.uniqueResult();
            session.close();
            return result;
        }catch (HibernateException e){
            logger.error("Failure to retrieve user by email", e);
            session.close();
            return null;
        }
    }

    @Override
    public User getUserByCredentials(String email, String password) {
        String hql = "FROM User as u where lower(u.email) = :email and u.password = :password";
        logger.debug(String.format("User email: %s, password: %s", email, password));

        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery(hql);
            query.setParameter("email", email.toLowerCase().trim());
            query.setParameter("password", password);
            return query.uniqueResult();
        }catch (Exception e)
        {
            logger.error("Can't find user based on given credential info", e.getMessage());
            return null;
        }
    }


}
