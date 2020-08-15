package com.campus.exchange.repository;

import com.campus.exchange.model.Customer;
import com.campus.exchange.model.Furniture;
import com.campus.exchange.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FurnitureDaoImpl implements FurnitureDao {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired private SessionFactory sessionFactory;

    @Override
    public Furniture save(Furniture furniture) {
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        try{
            transaction = session.beginTransaction();
            session.save(furniture);
            transaction.commit();
            session.close();
            return furniture;
        }catch(Exception e){
            if(transaction != null) transaction.rollback();
            logger.error("Failure to insert record.", e);
            session.close();
            return null;
        }
    }

    @Override
    public boolean update(Furniture furniture) {
        Transaction transaction = null;
        Boolean isSuccess = null;
        Session session = sessionFactory.openSession();

        try{
            transaction = session.beginTransaction();
            session.update(furniture);
            transaction.commit();
            session.close();
        }catch(Exception e){
            isSuccess = false;
            if(transaction != null) transaction.rollback();
            logger.error("Failure to update record", e.getMessage());
            session.close();
            return isSuccess;
        }
        if(isSuccess){
            logger.warn(String.format("The furniture %s has been updated.", furniture.toString()));
        }
        return isSuccess;
    }

    @Override
    public boolean delete(Furniture furn) {
        // :Id placeholder
        String hql = "DELETE Furniture as furn WHERE furn.id = :Id";
        int deletedCount = 0;
        Transaction transaction = null;
        Session session = sessionFactory.openSession();

        try{
            transaction = session.beginTransaction();
            Query<Furniture> query = session.createQuery(hql);
            query.setParameter("Id", furn.getId());
            deletedCount = query.executeUpdate();
            transaction.commit();
            session.close();
            return deletedCount >= 1? true: false;
        }catch(HibernateException e){
            if(transaction != null) transaction.rollback();
            session.close();
            logger.error("Unable to delete furniture record.", e);
        }
        return false;
    }

    @Override
    public List<Furniture> getFurnitures() {  // Dummy implementation
        List<Furniture> result = new ArrayList<>();
        String hql = "FROM Furniture";
        Session session = sessionFactory.openSession();
        try{
            Query query = session.createQuery(hql);
            result = query.list();
            session.close();
        }catch(HibernateException e){
            logger.error("Unable to get furniture records.", e.getMessage());
            session.close();
        }
        return result;
    }

    @Override
    public Furniture getFurnitureByName(String name){
        String hql = "FROM Furniture as furn LEFT JOIN FETCH furn.customer WHERE furn.name=:Name";
        Session session = sessionFactory.openSession();
        try{
            Query<Furniture> query = session.createQuery(hql);
            query.setParameter("Name", name);
            Furniture result = query.uniqueResult();
            session.close();
            return result;
        }catch(HibernateException e){
            logger.error("Unable to retrieve furnitures by id.", e.getMessage());
            session.close();
            return null;
        }
    }

}
