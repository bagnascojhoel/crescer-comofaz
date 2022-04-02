package br.com.cwi.crescer.api.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonConfig {

    private static final String ACCESS_KEY="AKIAZVUSD6XSV47SG5HQ";

    private static final String SECRET_KEY="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";

    private static final String REGION="us-east-1";

    @Bean
    public BasicAWSCredentials basicAWSCredentials(){
        return new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
    }

    @Bean
    public AmazonS3 amazonS3() {
        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials()))
                .withRegion(REGION)
                .build();
    }
}