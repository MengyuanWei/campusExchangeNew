package com.campus.exchange.service;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class FileService {

    private Logger logger = LoggerFactory.getLogger(getClass());
    //@Autowired
    private AmazonS3 amazonS3;

    private AmazonSQS sqsClient;

    String bucketName = "awei-s3-bucket-new";
    String fileName = "julia.txt";

    // constructor Dependency Injection
    public FileService(@Autowired AmazonS3 amazonS3){
        this.amazonS3 = amazonS3;
    }

    public void uploadFile(File f) throws IOException {
        PutObjectRequest request = new PutObjectRequest(bucketName, f.getName(), f);
        amazonS3.putObject(request);
    }

    /*
     * MultipartFile is a representation of an uploaded file received in a multipart request.
     * multipart request is the request that one or more different sets of data are combined in the request body
     *
     */
    public String uploadFile(String bucketName, MultipartFile file) {
        String fileUrl = null;

        try {
            if (amazonS3.doesObjectExist(bucketName, file.getOriginalFilename())) {
                logger.info(String.format("The file '%s' exists in the bucket %s", file.getOriginalFilename(), bucketName));
                return fileUrl;
            }

            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(file.getContentType());
            objectMetadata.setContentLength(file.getSize());
            amazonS3.putObject(bucketName, file.getOriginalFilename(),
                    file.getInputStream(), objectMetadata);
            fileUrl = getFileUrl(bucketName, file.getOriginalFilename());
            logger.info(String.format("The file name=%s, size=%d was uploaded to bucket %s",
                    file.getOriginalFilename(), file.getSize(), bucketName));
        }
        catch (Exception e) {
            logger.error(e.getMessage());
        }
        return fileUrl;
    }

    public String getFileUrl(String bucketName, String fileName) {
        LocalDateTime expiration = LocalDateTime.now().plusDays(1);
        GeneratePresignedUrlRequest generatePresignedUrlRequest =
                new GeneratePresignedUrlRequest(bucketName, fileName);
        generatePresignedUrlRequest.withMethod(HttpMethod.GET);
        generatePresignedUrlRequest.withExpiration
                (Date.from(expiration.toInstant(ZoneOffset.UTC)));

        return amazonS3.generatePresignedUrl(generatePresignedUrlRequest).toString();
    }

//
//    public void createBucket(String bucketName) {
//        if (!amazonS3.doesBucketExistV2(bucketName)) amazonS3.createBucket(bucketName);
//    }
//
//    public boolean saveFile(MultipartFile multipartFile, String filePath) {
//        boolean isSuccess = false;
//
//        try {
//            File directory = new File(filePath);
//            if (!directory.exists()) directory.mkdir();
//            Path path = Paths.get(filePath, multipartFile.getOriginalFilename());
//            multipartFile.transferTo(path);
//            isSuccess = true;
//            logger.info(String.format("The file %s is saved in the foldr %s", multipartFile.getOriginalFilename(), filePath));
//        }
//        catch(Exception e) {
//            logger.error(e.getMessage());
//        }
//
//        return isSuccess;
//   }
//}




}
