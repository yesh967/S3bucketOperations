import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.google.gson.Gson;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileOperations {

    public static void main(String[] args) throws IOException {

       String bucketName="testing-all-operation";

        System.setProperty("aws.region", "us-east-1");

        AmazonS3 S3client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(
                        new BasicAWSCredentials("AKIA42O3FNZMX25W4M3Y",
                                "araPp8JQCvAAc1yUvwB2dr3M7JIvvf6xBThD8hdS")))
                .build();

        int  i=0;

        List<Testobj> obj=new ArrayList<>();
        obj.add(new Testobj("clt","tenerity","S3aws" ));
        obj.add(new Testobj("clt2","tenerity2","S3aws2" ));
        obj.add(new Testobj("clt3","tenerity3","S3aws3" ));


        for (Testobj t:obj
             ) {
            i++;
            String temp=new Gson().toJson(t);
            System.out.println("\n"+i+" entry ");

            S3client.putObject(bucketName,"test/hello"+i+".json",temp);
        }
    }


}
