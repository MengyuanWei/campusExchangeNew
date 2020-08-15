package com.campus.exchange.controller;

import com.campus.exchange.model.Customer;
import com.campus.exchange.service.CustomerService;
import com.campus.exchange.util.HibernateUtil;
import org.hibernate.usertype.CompositeUserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = {"/customers", "/custs"})
public class CustomerController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CustomerService customerService;

    // http://localhost:8080/customers GET
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Customer> getCustomers(){
        List<Customer> customers = customerService.getCustomers();
        logger.debug("I am currently in the customer controller");
        return customers;
    }

    // http://localhost:8080/customers/1 GET
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Customer getCustomerById(@PathVariable(name = "id") Long id){
        logger.debug("I am in the customer controller get by " + id);
        return customerService.getBy(id);
    }

//    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
//    public void updateCustomer(@PathVariable("id") Long id,@RequestParam("name") String name) {
//        logger.debug("I am in the customer controller update customer by its" + id + "and name" + name);
//    }

    // http://localhost:8080/customers/1?name=Rebecca Miranda PATCH
    // http://localhost:8080/customers/1 GET
    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public Boolean updateCustomer(@PathVariable("id") Long id,@RequestParam("name") String name){
        Customer c = customerService.getBy(id);
        c.setName(name);
        boolean d = customerService.update(c);
        return d;
     }

//     @RequestMapping(value = "", method = RequestMethod.POST)
//     public Customer create(Customer newObject){
//        Customer cus = customerService.save(newObject);
//        return cus;
//     }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public void create(@RequestBody Customer newObject){
        logger.debug(newObject.toString());
    }




}
