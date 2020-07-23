package com.qiaohy.feign;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.qiaohy.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "eureka-client-user", /*fallback = HystrixClientFallback.class, */fallbackFactory = HystrixClientFactory.class)
public interface UserFeignClient {

  @RequestMapping(value = "/simple/{id}", method = RequestMethod.GET)
  @HystrixCommand(fallbackMethod = "getDefaultFallBackMethod" ,commandProperties = @HystrixProperty(name = "execution.isolation.strategy",value = "SEMAPHORE"))
  public User findById(@PathVariable("id") Long id);
}
