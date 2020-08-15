package com.campus.exchange.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectListing;
import com.campus.exchange.ApplicationBootstrap;
import org.apache.commons.codec.digest.DigestUtils;
//import org.apache.commons.io.FileUtils;
//import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import java.util.List;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= ApplicationBootstrap.class)
public class AWSServiceTest {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AWSS3Service awss3Service;

    @Test
    public void testCreateBucket(){
        String bucketName = "awei-s3-bucket-new-4";
        Bucket bucket = awss3Service.createBucket(bucketName);
        Assert.assertNotNull(bucket);
    }

    @Test
    public void testPresignedURLForUploading()  {
        String bucketName = "mgao-s3-bucket-1";
        String filename = "presignedTest.txt";
        String urlString = awss3Service.generatePresignedURLForUploading(bucketName, filename);
        logger.info("==== PUT presigneURL string ={}", urlString);
        try {
            URL url = new URL(urlString);
            // Create the connection and use it to upload the new object using the pre-signed URL.
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("PUT");
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
            out.write("This text uploaded as an object via presigned URL.");
            out.close();
            // Check the HTTP response code. To complete the upload and make the object available,
            // you must interact with the connection object in some way.
            connection.getResponseCode();
            logger.info("HTTP response code: " + connection.getResponseCode());
        } catch (MalformedURLException e) {
            // The call was transmitted successfully, but Amazon S3 couldn't process
            // it, so it returned an error response.
            logger.error(e.getMessage());
        }catch (AmazonServiceException e) {
            // The call was transmitted successfully, but Amazon S3 couldn't process
            // it, so it returned an error response.
            logger.error(e.getMessage());
        } catch (SdkClientException e) {
            // Amazon S3 couldn't be contacted for a response, or the client
            // couldn't parse the response from Amazon S3.
            logger.error(e.getMessage());
        } catch (IOException e) {
            // The call was transmitted successfully, but Amazon S3 couldn't process
            // it, so it returned an error response.
            logger.error(e.getMessage());
        }
    }

//    @Test
//    public void testPresignedURLForDownloading() {
//        String bucketName = "mgao-s3-bucket-1";
//        String filename = "presignedTest.txt";
//        String destFilename = "/home/michael/temp/presignedGetTest.txt";
//        String urlString = awss3Service.generatePresignedURLForDownloading(bucketName, filename);
//        logger.info("==== GET presigneURL string ={}", urlString);
//        try {
//            URL url = new URL(urlString);
//            // Create the connection and use it to upload the new object using the pre-signed URL.
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setDoInput(true);
//            connection.setRequestMethod("GET");
//            File destFile = new File(destFilename);
//            FileUtils.touch(destFile);
//            InputStream is = connection.getInputStream();
//            FileUtils.copyInputStreamToFile(is, destFile);
//            // Check the HTTP response code. To complete the upload and make the object available,
//            // you must interact with the connection object in some way.
//            connection.getResponseCode();
//            logger.info("HTTP response code: " + connection.getResponseCode());
//            IOUtils.closeQuietly(is);
//            IOUtils.close(connection);
//        } catch (MalformedURLException e) {
//            // The call was transmitted successfully, but Amazon S3 couldn't process
//            // it, so it returned an error response.
//            logger.error(e.getMessage());
//        }catch (AmazonServiceException e) {
//            // The call was transmitted successfully, but Amazon S3 couldn't process
//            // it, so it returned an error response.
//            logger.error(e.getMessage());
//        } catch (SdkClientException e) {
//            // Amazon S3 couldn't be contacted for a response, or the client
//            // couldn't parse the response from Amazon S3.
//            logger.error(e.getMessage());
//        } catch (IOException e) {
//            // The call was transmitted successfully, but Amazon S3 couldn't process
//            // it, so it returned an error response.
//            logger.error(e.getMessage());
//        }
//    }


}
