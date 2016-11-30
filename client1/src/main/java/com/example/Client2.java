package com.example;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Andreas on 30.11.2016.
 */
@FeignClient(value = "TESTCLIENT2", fallback = Client2Fallback.class)
public interface Client2 {
    @RequestMapping(method = RequestMethod.GET, value = "/test2")
    String getMessage();

}