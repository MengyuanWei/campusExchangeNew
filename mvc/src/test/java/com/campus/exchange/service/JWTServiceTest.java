package com.campus.exchange.service;

import com.campus.exchange.ApplicationBootstrap;
import com.campus.exchange.model.User;
import io.jsonwebtoken.Claims;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= ApplicationBootstrap.class)
public class JWTServiceTest {

    @Autowired
    private JWTService jwtService;

    @Test
    public void generateTokenTest(){
        User u = new User();
        u.setId(1L);
        u.setName("AlexWei");
        String token = jwtService.generateToken(u);

        String[] array = token.split("\\.");
        System.out.println(token);

        // Assertion
        assertNotNull(token);
        assertEquals(array.length, 3);
    }

    @Test
    public void decryptJwtTokenTest(){
        User u = new User();
        u.setId(10L);
        u.setName("Ran");
        String token = jwtService.generateToken(u);
        Claims cLaims = jwtService.decryptJwtToken(token);
        assertNotNull(cLaims);
        assertEquals(cLaims.getSubject(), "Ran");
    }

}
