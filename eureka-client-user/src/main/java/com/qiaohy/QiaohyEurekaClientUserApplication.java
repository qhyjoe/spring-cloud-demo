package com.qiaohy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class QiaohyEurekaClientUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(QiaohyEurekaClientUserApplication.class, args);
    }



}
