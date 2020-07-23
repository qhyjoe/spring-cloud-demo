package com.qiaohy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OutController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/getString")
    public String getString(){
        return restTemplate.getForObject("https://www.hao123.com/",String.class);
    }

}
