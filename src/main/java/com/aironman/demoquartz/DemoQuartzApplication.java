package com.aironman.demoquartz;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.boot.system.ApplicationPidFileWriter;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@SpringBootApplication
@EnableEurekaClient
@RestController
@EnableFeignClients("com.aironman.demoquartz.springcloud.feign")
public class DemoQuartzApplication {
    
    Logger logger = LoggerFactory.getLogger(getClass());

    public static String cryptoType;

    public static void main(String[] args) {
	SpringApplication springApplication = new SpringApplication(DemoQuartzApplication.class);
	//springApplication.addListeners(new ApplicationPidFileWriter());
    //Arrays.stream(args).forEach(System.out::println);

    if (args.length != 1) {
        System.out.println("Insufficient arguments. Must be btc or eth. Applying btc...");
        cryptoType = "btc";
        //System.exit(-1);
    }else
        cryptoType = args[0];
    if (!cryptoType.equals("btc") || !cryptoType.equals("eth")){
        System.out.println("Arguments MUST be btc or eth. Exit. " + cryptoType + " argument was used!");
        System.exit(-1);
    }
    System.out.println("Selected TYPE: " + cryptoType);
	springApplication.run(args);
    //System.exit(0);
    }
    
    @PostConstruct
    public void doingSomethingCoolWithKafka() {
	logger.info("INIT doingSomethingCoolWithKafka...");
	logger.info("END doingSomethingCoolWithKafka...");
    }
    
    @RequestMapping("/greeting")
    public String greeting() {
	return "Hello from EurekaClient! This is DemoQuartzApplication running " + cryptoType + " mode.";
    }
    
}
