package com.campus.exchange.repository;

import com.campus.exchange.ApplicationBootstrap;
import com.campus.exchange.model.Customer;
import com.campus.exchange.model.Furniture;
import com.campus.exchange.model.Textbook;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= ApplicationBootstrap.class)
public class CustomerDaoTest {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CustomerDao customerDao;
    private Customer c1;

    @Autowired
    private FurnitureDao furnitureDao;
    private Furniture f1;
    private Furniture f2;

    @Autowired
    private TextbookDao textbookDao;
    private Textbook t1;
    private Textbook t2;

    @Before
    public void setUp(){    // customerDao = new CustomerDaoImpl();
        // Logic 1: Save record on one side.
        c1 = new Customer();
        c1.setName("Julia Alex");
        c1.setPhoneNumber("2027788990");
        c1.setEmail("alex0505@gmail.com");
        c1.setPassword("19970505");
        c1 = customerDao.save(c1);

        // Logic 2: Save record on many side (1/2).
        f1 = new Furniture();
        f1.setName("3-Tier Cart");
        f1.setCategory("Kitchen");
        f1.setDescription("Near GWU campus.");
        f1.setPrice(15.00);
        f1.setCustomer(c1);
        furnitureDao.save(f1);
        // Logic 2: Save record on many side (2/2).
        f2 = new Furniture();
        f2.setName("Twin Size Bed");
        f2.setCategory("Bedroom");
        f2.setDescription("Located in Pentagon City, near metro station.");
        f2.setPrice(30.00);
        f2.setCustomer(c1);
        furnitureDao.save(f2);

        // Logic 2: Save record on many side (1/2).
        t1 = new Textbook();
        t1.setName("My Unforgiven Ex-boyfriend");
        t1.setCategory("Loving Relationship");
        t1.setIsbn("1997050573210");
        t1.setDescription("90% New, front page ripped from last customer.");
        t1.setPrice(30.00);
        t1.setCustomer(c1);
        textbookDao.save(t1);
        // Logic 2: Save record on many side (2/2).
        t2 = new Textbook();
        t2.setName("My Unforgettable Ex-girlfriend");
        t2.setCategory("Loving Relationship");
        t2.setIsbn("1997091623333");
        t2.setDescription("Like new, never read.");
        t2.setPrice(30.00);
        t2.setCustomer(c1);
        textbookDao.save(t2);

    }

    @After
    public void tearDown(){
        // Logic 1 delete record on many side.
        furnitureDao.delete(f1);
        furnitureDao.delete(f2);

        textbookDao.delete(t1);
        textbookDao.delete(t2);

        // Logic 2 delete record on one side.
        customerDao.delete(c1);

    }

    @Test
    public void getCustomersTest(){
        logger.debug("Start unit test for getCustomersTest...");
        List<Customer> customers = customerDao.getCustomers();
        int expectedNumOfCustomers = 5;
        assertEquals(expectedNumOfCustomers, customers.size());
        logger.debug("End unit test for getCustomersTest...");
    }

    @Test
    public void getCustomerEagerByTest(){
        logger.debug("Start unit test for getCustomerEagerByTest...");
        Customer customer = customerDao.getCustomerEagerBy(c1.getId());
        assertNotNull(customer);
        assertEquals(customer.getName(), c1.getName());
        assertTrue(customer.getFurnitures().size()>0);
        logger.debug("End unit test for getCustomerEagerByTest...");
    }

    @Test
    public void updateTest(){
        logger.debug("Start unit test for updateTest...");

        String updateName = "Alex Miranda";
        String updatePhoneNumber = "2022024999";
        String updateEmail = "alex@gmail.com";
        String updatePassword = "1997";

        c1.setName(updateName);
        c1.setPhoneNumber(updatePhoneNumber);
        c1.setEmail(updateEmail);
        c1.setPassword(updatePassword);

        customerDao.update(c1);
        Assert.assertEquals(updateName, c1.getName());
        Assert.assertEquals(updatePhoneNumber, c1.getPhoneNumber());
        Assert.assertEquals(updateEmail, c1.getEmail());
        Assert.assertEquals(updatePassword, c1.getPassword());

        logger.debug("End unit test for updateTest...");
    }

}

