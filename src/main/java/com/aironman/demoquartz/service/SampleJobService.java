package com.aironman.demoquartz.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.aironman.demoquartz.pojo.BitcoinEuro;

@Service
public class SampleJobService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public void executeSampleJob() {

        logger.info("The sample job has begun...");
        try {
        	RestTemplate restTemplate = new RestTemplate();
            // https://api.coinmarketcap.com/v1/ticker/bitcoin/?convert=EUR
            //Quote quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
            //log.info("json: " + quote.toString());
            BitcoinEuro[] bitCoinEuro =  restTemplate.getForObject("https://api.coinmarketcap.com/v1/ticker/bitcoin/?convert=EUR", BitcoinEuro[].class);
            logger.info(bitCoinEuro[0].toString());
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            logger.error("Error while executing sample job", e);
        } finally {
            logger.info("Sample job has finished...");
        }
    }
}
