package com.campus.exchange.repository;

import com.campus.exchange.model.Customer;

import java.util.List;

public interface CustomerDao {
    Customer save(Customer customer);

    boolean update(Customer customer);

    boolean delete(Customer customer);

    List<Customer> getCustomers();

    Customer getCustomerEagerBy(Long id);

    Customer getBy(Long id);
}

// 方法是标准，change codes fewer and easier
// CustomerDao customerDao = new CustomerDaoImpl();
// customerDao.save(customer);
// List l = new Vector();
// l.add(user);
// Multiple implementations in one project, using interface to better implement.
