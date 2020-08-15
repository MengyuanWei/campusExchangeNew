package com.campus.exchange.service;

import com.campus.exchange.model.Customer;
import com.campus.exchange.repository.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerDao customerDao;

    public Customer save(Customer customer){
        return customerDao.save(customer);
    }

    public boolean update(Customer customer){
        return customerDao.update(customer);
    }

    public boolean delete(Customer customer){
        return customerDao.delete(customer);
    }

    public List<Customer> getCustomers(){
        return customerDao.getCustomers();
    }

    public Customer getBy(Long id){return customerDao.getBy(id);}

}
