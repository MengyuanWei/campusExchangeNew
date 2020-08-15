package com.campus.exchange.repository;

import com.campus.exchange.model.Customer;
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
public class CustomerDaoImpl implements CustomerDao{

    private Logger logger = LoggerFactory.getLogger(getClass());
//    @Autowired private CustomerDao customerDao;
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Customer save(Customer customer) {
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        try{
            transaction = session.beginTransaction();
            session.save(customer);
            transaction.commit();
            session.close();
            return customer;
        }
        catch(Exception e){
            if(transaction != null) transaction.rollback();
            logger.error("Failure to insert record.", e);
            session.close();
            return null;
        }
    }

    @Override
    public boolean update(Customer customer) {
        String msg = String.format("The customer %s was updated.", customer.toString());
        Transaction transaction = null;
        Boolean isSuccess = true;

        Session session = sessionFactory.openSession();
        try{
            transaction = session.beginTransaction();
            session.saveOrUpdate(customer);
            transaction.commit();
            session.close();
        }
        catch(Exception e){
            isSuccess = false;
            msg = e.getMessage();
            if(transaction != null) transaction.rollback();
            session.close();
        }
        if(isSuccess) logger.debug(msg);
        return isSuccess;
    }

    @Override
    public boolean delete(Customer customer) {
        // :Id placeholder
        String hql = "DELETE Customer as cus WHERE cus.id =:Id";
        int deletedCount = 0;
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        try{
            transaction = session.beginTransaction();
            Query<Customer> query = session.createQuery(hql);
            query.setParameter("Id", customer.getId());
            deletedCount = query.executeUpdate();
            transaction.commit();
            session.close();
            return deletedCount >= 1? true: false;
        }catch(HibernateException e){
            if(transaction != null) transaction.rollback();
            session.close();
            logger.error("Unable to delete customer record.", e);
        }
        return false;
    }

    @Override
    public List<Customer> getCustomers() {  // Dummy implementation
        List<Customer> result = new ArrayList<>();
        String hql = "FROM Customer";
        // If you have red underline, then IDE couldn't recognize the model. It thinks it is an object but it is not.
        Session session = sessionFactory.openSession();
        try{
            Query query = session.createQuery(hql);
            result = query.list();
            session.close();
        }catch(HibernateException e){
            logger.error("Unable to get customer records.", e.getMessage());
            session.close();
        }
        return result;
    }

    @Override
    public Customer getCustomerEagerBy(Long id){
        String hql = "FROM Customer c LEFT JOIN FETCH c.furnitures WHERE c.id=:Id";
        Session session = sessionFactory.openSession();
        try {
            Query<Customer> query = session.createQuery(hql);
            query.setParameter("Id",id);
            Customer result = query.uniqueResult();
            session.close();
            return result;
        }catch (HibernateException e){
            logger.error("Unable to retrieve customers by id.",e);
            session.close();
            return null;
        }
    }

    @Override
    public Customer getBy(Long id) {
        String hql = "FROM Customer c WHERE c.id=:Id";
        Session session = sessionFactory.openSession();
        try{
            Query<Customer> query = session.createQuery(hql);
            query.setParameter("Id", id);
            Customer result = query.uniqueResult();
            session.close();
            return result;
        }catch(HibernateException e){
            logger.error("Failure to retrieve data record", e);
            session.close();
            return null;
        }
    }


}
