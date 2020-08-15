package com.campus.exchange.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.campus.exchange.ApplicationBootstrap;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.io.File;
import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= ApplicationBootstrap.class)
public class FileServiceTest {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private FileService fileService;
    @Autowired
    private AmazonS3 amazonS3;

    @Test
    public void uploadFileTest() throws IOException {
//        String bucketName = "awei-s3-bucket-new";
        fileService.uploadFile(new File("/home/alexwei/Desktop/test.txt"));
        // amazonS3: client
        verify(amazonS3,times(1)).putObject(any(PutObjectRequest.class));
    }

    @After
    public void tearDown() {
        logger.info("Unit test for FileService ends here.");
    }


}
