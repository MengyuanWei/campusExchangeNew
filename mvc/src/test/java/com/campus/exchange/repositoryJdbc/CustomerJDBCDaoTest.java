package com.campus.exchange.repositoryJdbc;

import com.campus.exchange.modelJdbc.CustomerJDBC;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class CustomerJDBCDaoTest {

    private CustomerJDBCDao customerJDBCDao;
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Before
    public void setUp(){
        customerJDBCDao = new CustomerJDBCDao();
    }

    @After
    public void tearDown(){
        customerJDBCDao = null;
    }

    @Test
    public void getCustomersTest(){
        logger.debug("start unit test for getCustomersTest");
        CustomerJDBCDao customerJDBCDao = new CustomerJDBCDao();
        List<CustomerJDBC> customerJDBCList = customerJDBCDao.getCustomers();
        assertEquals(0, customerJDBCList.size());
        // assertEquals(customerJDBCDao.getCustomers().size(), 4);
        // customerJDBCDao.getCustomers().size == 4;
    }

//    @Test
//    public void getCustomerByName(){
//        CustomerJDBCDao customerJDBCDao = new CustomerJDBCDao();
//        List<CustomerJDBC> customerList = customerJDBCDao.getCustomers();
//        assertEquals(4, customerList.size());
//    }
}
