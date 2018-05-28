package com.aironman.demoquartz;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.system.ApplicationPidFileWriter;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class DemoQuartzApplication {
    
    Logger logger = LoggerFactory.getLogger(getClass());
    
    public static void main(String[] args) {
	SpringApplication springApplication = new SpringApplication(DemoQuartzApplication.class);
	springApplication.addListeners(new ApplicationPidFileWriter());
	springApplication.run(args);
    }
    
    @PostConstruct
    public void doingSomethingCoolWithKafka() {
	logger.info("INIT doingSomethingCoolWithKafka...");
	logger.info("END doingSomethingCoolWithKafka...");
    }
    
    @RequestMapping("/greeting")
    public String greeting() {
	return "Hello from EurekaClient! This is DemoQuartzApplication...";
    }
    
}
