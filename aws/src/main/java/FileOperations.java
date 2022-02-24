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

        // creating client(include credential for accessing bucket when running)
        AmazonS3 S3client = AmazonS3ClientBuilder
                .standard()
                .build();

        int  i=0;

        List<Testobj> obj=new ArrayList<>();
        obj.add(new Testobj("clt","tenerity","S3aws" ));
        obj.add(new Testobj("clt2","tenerity2","S3aws2" ));
        obj.add(new Testobj("clt3","tenerity3","S3aws3" ));

//iterating each list record and uploading to bucket
        for (Testobj t:obj
        ) {
            i++;
            String temp=new Gson().toJson(t);
            System.out.println("\n"+i+" entry ");

            S3client.putObject(bucketName,"test/hello"+i+".json",temp);
        }
    }


}
