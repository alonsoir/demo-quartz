package com.aironman.demoquartz.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.aironman.demoquartz.springcloud.feign.TestFeign;

@RestController
@RequestMapping("/ping")
public class TestController {
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    private TestFeign testFeign;
    
    public TestController() {
	// For Spring
    }
    
    @RequestMapping
    public String doAlive() {
	return "Alive!";
    }
    
    @RequestMapping("/rest/feign")
    public String doRestAliveUsingFeign() {
	return testFeign.doAlive();
    }
    
    @RequestMapping("/rest")
    public String doRestAlive() {
	return new RestTemplate().getForObject("http://localhost:8080/ping", String.class);
    }
    
    @RequestMapping("/rest/ribbon")
    public String doRestAliveUsingEurekaAndRibbon() {
	return restTemplate.getForObject("http://demo-quartz:0/ping", String.class);
    }
}