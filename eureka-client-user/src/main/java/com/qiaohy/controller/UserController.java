package com.qiaohy.controller;

import com.google.common.collect.Lists;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.qiaohy.entity.User;
import com.qiaohy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Qualifier("eurekaClient")
    @Autowired
    private EurekaClient eurekaClient;

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/simple/{id}")
    public User findById(@PathVariable Long id) {
        return this.userRepository.getOne(id);
    }

    @GetMapping("/eureka-instance")
    public String serviceUrl() {
        InstanceInfo instance = this.eurekaClient.getNextServerFromEureka("eureka-client", false);
        return instance.getHomePageUrl();
    }

    @GetMapping("/getEurekaServices")
    public List<String> getEurekaServices() {
        List<String> services = new ArrayList<>();
        List<String> serviceNames = discoveryClient.getServices();
        for (String serviceName : serviceNames) {
            List<ServiceInstance> serviceInstances = discoveryClient.getInstances(serviceName);
            for (ServiceInstance serviceInstance : serviceInstances) {
                services.add(String.format("%s:%s", serviceName, serviceInstance.getUri()));
            }
        }
        return services;
    }

    @PostMapping("/user")
    public User postUser(@RequestBody User user) {
        return user;
    }

    @GetMapping("/getUser")
    public User getUser(User user) {
        return user;
    }

    @GetMapping("list-all")
    public List<User> listAll() {
        ArrayList<User> list = Lists.newArrayList();
        User user = new User(1L, "zhangsan");
        User user2 = new User(2L, "zhangsan");
        User user3 = new User(3L, "zhangsan");
        list.add(user);
        list.add(user2);
        list.add(user3);
        return list;

    }
}
