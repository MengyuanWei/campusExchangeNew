package com.campus.exchange.repositoryJdbc;

import com.campus.exchange.modelJdbc.FurnitureJDBC;

import java.math.BigDecimal;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FurnitureJDBCDaoTest {
    private FurnitureJDBCDao furnitureJDBCDao;
    private Logger logger = LoggerFactory.getLogger(getClass());

    FurnitureJDBC oldFurnitureJDBC = new FurnitureJDBC( "3-tier Cart", "Kitchen",
            "3-Tier Storage Cart in Grey, used for half an year, " +
                    "85% new. Located at Rockville",
            3, BigDecimal.valueOf(30));

    @Before
    public void setUp(){
        furnitureJDBCDao = new FurnitureJDBCDao();
        furnitureJDBCDao.save(oldFurnitureJDBC);
    }

    @After
    public void tearDown(){
        furnitureJDBCDao.delete(oldFurnitureJDBC.getName());
        furnitureJDBCDao = null;
    }

    @Test
    public void getFurnituresTest(){
        logger.debug("Start unit test for getFurnitureTest");
        List<FurnitureJDBC> furnitureJDBCS = furnitureJDBCDao.getFurnitures();
        int expectedNumOfFurn = 0;
        Assert.assertEquals(expectedNumOfFurn, furnitureJDBCS.size());
        logger.debug("Unit Test for getFurnitureTest Ends Here.");
    }

    @Test
    public void update(){
        FurnitureJDBC updateFurnitureJDBC = new FurnitureJDBC(
                "King Size Bed", "Beds",
                "Located around Rockville, without a bed frame",
                3, BigDecimal.valueOf(55));
        furnitureJDBCDao.update(oldFurnitureJDBC.getName(), updateFurnitureJDBC);

        Assert.assertEquals("King Size Bed", updateFurnitureJDBC.getName());
        logger.debug("Unit Test for updateFurnitureTest Ends Here.");
    }

}
