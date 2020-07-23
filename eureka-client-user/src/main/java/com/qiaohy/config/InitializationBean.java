package com.qiaohy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class InitializationBean {

    @Bean(name = "restTemplate")
    public RestTemplate initRestTemplate(){
        return new RestTemplate();
    }
}
