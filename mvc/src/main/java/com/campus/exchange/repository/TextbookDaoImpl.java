package com.campus.exchange.repository;

import com.campus.exchange.model.Customer;
import com.campus.exchange.model.Textbook;
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

import java.util.List;

@Repository
public class TextbookDaoImpl implements TextbookDao {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired private SessionFactory sessionFactory;

    @Override
    public Textbook save(Textbook textbook) {
        Transaction transaction = null;
        Session session = sessionFactory.openSession();

        try{
            transaction = session.beginTransaction();
            session.save(textbook);
            transaction.commit();
            session.close();
            return textbook;
        }catch (Exception e){
            if(transaction != null) transaction.rollback();
            logger.error("Failure to insert record.", e);
            session.close();
            return null;
        }
    }

    @Override
    public boolean update(Textbook textbook) {
        Transaction transaction = null;
        Boolean isSuccess = null;
        Session session = sessionFactory.openSession();

        try{
            transaction = session.beginTransaction();
            session.update(textbook);
            transaction.commit();
            session.close();
        }
        catch(Exception e){
            isSuccess = false;
            if(transaction != null) transaction.rollback();
            logger.error("Failure to update record", e.getMessage());
            session.close();
            return isSuccess;
        }
        if(isSuccess){
            logger.warn(String.format("The textbook %s has been updated.", textbook.toString()));
        }
        return isSuccess;
    }

    @Override
    public boolean delete(Textbook textbook) {
        String hql = "DELETE Textbook as text WHERE text.id = :Id";
        int deletedCount = 0;
        Transaction transaction = null;
        Session session = sessionFactory.openSession();

        try{
            transaction = session.beginTransaction();
            Query<Customer> query = session.createQuery(hql);
            query.setParameter("Id", textbook.getId());
            deletedCount = query.executeUpdate();
            transaction.commit();
            session.close();
            return deletedCount >= 1? true: false;
        }catch(HibernateException e){
            if(transaction != null) transaction.rollback();
            session.close();
            logger.error("Unable to delete textbook record.", e);
        }
        return false;
    }

    @Override
    public List<Textbook> getTextbooks() {
        String hql = "FROM Textbook as text LEFT JOIN FETCH Customer.id, Customer.name";
        Session session = sessionFactory.openSession();
        List<Textbook> result;
        try{
            Query query = session.createQuery(hql);
            result = query.list();
            session.close();
        }catch(HibernateException e){
            logger.error("Unable to get textbook records.", e.getMessage());
            session.close();
            return null;
        }
        return result;
    }

}
