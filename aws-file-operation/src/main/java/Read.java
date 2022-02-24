import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import java.io.*;
import java.util.logging.Logger;
import java.util.stream.Stream;
public class Read {
    public static void main(String[] args) throws Exception {

        Logger logger = Logger.getLogger(Read.class.getName());
        String bucketName = "testing-all-operation";
        /* Create S3 Client Object */
        AmazonS3 s3 = AmazonS3ClientBuilder
                .standard()
                .withRegion(Regions.US_EAST_1)
                .withCredentials(new AWSStaticCredentialsProvider(
                        new BasicAWSCredentials("AKIA42O3FNZMX25W4M3Y",
                                "araPp8JQCvAAc1yUvwB2dr3M7JIvvf6xBThD8hdS")))
                .build();
        try {
            /* Get first batch of objects in a given bucket */
            System.out.println("******Downloading and Reading Files******");
           /* ListObjectsRequest listObjectsRequest = new ListObjectsRequest()
                    .withBucketName(bucketName)
                    .withPrefix("test/");
            ObjectListing objects = s3Client.listObjects(listObjectsRequest);*/
            ObjectListing objects = s3.listObjects(bucketName);
            /* Recursively get all the objects inside given bucket */
            if (objects != null && objects.getObjectSummaries() != null) {
                while (true) {
                    for (S3ObjectSummary summary : objects.getObjectSummaries()) {
                        System.out.println("FileName :- " + summary.getKey());
                        String FileName = summary.getKey();
                        S3Object s3Object = s3.getObject(bucketName, FileName);
                        InputStream inputStream = s3Object.getObjectContent();
                        String string = IOUtils.toString(inputStream);
                        try {
                            ConvertJson jsonObj = new ConvertJson();
                            boolean jsonAsString = jsonObj.convertJson(string);
                        } catch (IOException ex) {
                            System.out.println("error while reading files");
                        }
                    }
                    // checks whether the object listing is complete or not
                    if (objects.isTruncated()) {
                        objects = s3.listNextBatchOfObjects(objects);
                    } else {
                        break;
                    }
                }
            }

        } catch (AmazonServiceException e) {
            System.out.println(e.getErrorMessage());
        } finally {

            if (s3 != null) {
                s3.shutdown();
            }
        }
    }
}
