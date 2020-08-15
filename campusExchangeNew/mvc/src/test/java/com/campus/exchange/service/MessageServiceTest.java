package com.campus.exchange.service;

import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.GetQueueUrlRequest;
import com.campus.exchange.ApplicationBootstrap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= ApplicationBootstrap.class)
public class MessageServiceTest {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MessageService messageService;
    @Autowired
    private AmazonSQS sqsClient;

    @Test
    public void getQueueUrlTest(){
        messageService.getQueueUrl("123");
        verify(sqsClient,times(1)).getQueueUrl("123");
    }

    @Test
    public void sendMessageTest(){
        messageService.sendMessage("test", 1);
        assertTrue(true);
    }

}
