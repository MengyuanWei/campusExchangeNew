package com.campus.exchange.repository;

import com.campus.exchange.ApplicationBootstrap;
import com.campus.exchange.model.Customer;
import com.campus.exchange.model.Furniture;
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
public class FurnitureDaoTest {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private FurnitureDao furnitureDao;

    @Autowired
    private CustomerDao customerDao;

    private Furniture testFurn;
    private Customer testCus;


    @Before
    public void setUp(){  //        furnitureDao = new FurnitureDaoImpl();

        // Insert data into the test Furniture.
        testCus= new Customer();
        testFurn = new Furniture();

        testFurn.setName("Twin Bed");
        testFurn.setCategory("Bed");
        testFurn.setDescription("Located around D.C.");
        testFurn.setPrice(20.0);
        testFurn.setCustomer(testCus);
        furnitureDao.save(testFurn);
    }

    @After
    public void tearDown(){

        furnitureDao.delete(testFurn);
        customerDao.delete(testCus);

    }

    @Test
    public void getFurnituresTest(){
        logger.debug("Start unit test for getFurnituresTest...");
        List<Furniture> Furnitures = furnitureDao.getFurnitures();
        int expectedNumOfFurnitures = 4;
        assertEquals(expectedNumOfFurnitures, Furnitures.size());
        logger.debug("End unit test for getFurnituresTest...");
    }

    @Test
    public void getFurnitureByNameTest(){
        logger.debug("Start unit test for getFurnitureByNameTest...");
        String name = "Mattress";
        Furniture furniture = furnitureDao.getFurnitureByName(name);
        Assert.assertEquals(name, furniture.getName());
        logger.debug("End unit test for getFurnitureByNameTest...");
    }

}
