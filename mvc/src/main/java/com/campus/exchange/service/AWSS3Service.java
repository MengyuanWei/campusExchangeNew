package com.campus.exchange.service;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URL;

@Service
public class AWSS3Service {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private AmazonS3 amazonS3;

    public AWSS3Service(@Autowired AmazonS3 amazonS3){
        this.amazonS3 = amazonS3;
    }

    public Bucket createBucket(String bucketName){
        Bucket bucket = null;
        if(!amazonS3.doesBucketExistV2(bucketName)){
            bucket = amazonS3.createBucket(bucketName);
        }
        else{
            logger.info("bucket name: {} is not available."
                    + " Try again with a different Bucket name.", bucketName);
        }
        return bucket;
    }

    public String generatePresignedURLForUploading(String bucketName,
                                                   String objectKey) {
        return generatePresignedURL(bucketName, objectKey, "PUT");
    }

    public String generatePresignedURLForDownloading(String bucketName,
                                                     String objectKey) {
        return generatePresignedURL(bucketName, objectKey, "GET");
    }

    public String generatePresignedURL(String bucketName,
                                       String objectKey, String httpMethodString) {
        // Set the pre-signed URL to expire after one hour.
        java.util.Date expiration = new java.util.Date();
        long expTimeMillis = expiration.getTime();
        expTimeMillis += 1000 * 60 * 60;
        expiration.setTime(expTimeMillis);
        // Generate the pre-signed URL.
        logger.info("Generating pre-signed URL.");
        GeneratePresignedUrlRequest generatePresignedUrlRequest =
                new GeneratePresignedUrlRequest(bucketName, objectKey)
                        .withMethod(HttpMethod.valueOf(httpMethodString))
                        .withExpiration(expiration);
        URL url = amazonS3.generatePresignedUrl(generatePresignedUrlRequest);
        return url.toString();
    }

//    public boolean isBucketExist(String bucketName) {
//        public boolean isObjectExist(String bucketName, String objectKey) {
//            public List<Bucket> getBucketList()
//            public void deleteBucket(String bucketName)
//            public void uploadObject(String bucketName,
//                    String key, String fullFilepath) {
//                amazonS3.putObject(bucketName, key,
//                        new File(fullFilepath));
//            }
//            public ObjectListing listObjects(String bucketName)
//            public List<String> findObjectKeyList(String bucketName) {
//                ObjectListing objectListing = amazonS3.listObjects(bucketName);
//                List<String> objectKeyList = new ArrayList<String>();
//                for(S3ObjectSummary os : objectListing.getObjectSummaries()) {
//                    objectKeyList.add(os.getKey());
//                }
//                return objectKeyList;
//            }
//            public File downloadObject(String bucketName,
//                    String objectKey, String destinationFullPath) throws IOException {
//                S3Object s3Object = amazonS3.getObject(bucketName, objectKey);
//                S3ObjectInputStream inputStream = s3Object.getObjectContent();
//                File destFile = new File(destinationFullPath);
//                FileUtils.copyInputStreamToFile(inputStream, destFile);
//                return destFile;
//            }
//            public void copyObject(String oriBucketName,
//                    String oriObjectKey, String destBucketName,
//                    String destObjectKey) {
//                amazonS3.copyObject(oriBucketName, oriObjectKey,
//                        destBucketName, destObjectKey);
//            }
//            public void copyObjectUsingCopyObjectRequest(
//                    String oriBucketName,
//                    String oriObjectKey,
//                    String destBucketName,
//                    String destObjectKey) {
//                CopyObjectRequest copyObjectRequest = new CopyObjectRequest();
//                copyObjectRequest.setSourceBucketName(oriBucketName);
//                copyObjectRequest.setSourceKey(oriObjectKey);
//                copyObjectRequest.setDestinationBucketName(destBucketName);
//                copyObjectRequest.setDestinationKey(destObjectKey);
//                amazonS3.copyObject(copyObjectRequest);
//            }
//            public void deleteObject(String bucketName, String objectKey) {
//                amazonS3.deleteObject(bucketName, objectKey);
//            }

}
