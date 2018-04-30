package com.aironman.demoquartz.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.aironman.demoquartz.model.BitcoinEuroEntity;
import com.aironman.demoquartz.pojo.BitcoinEuro;

@Service
public class SampleJobService {

	@Autowired
	private BitCoinEuroService bitCoinService;
	
    private Logger logger = LoggerFactory.getLogger(getClass());

    
    public void executeSampleJob() {

        logger.info("The sample job has begun...");
        try {
        	RestTemplate restTemplate = new RestTemplate();
            BitcoinEuro[] bitCoinEuro =  restTemplate.getForObject("https://api.coinmarketcap.com/v1/ticker/bitcoin/?convert=EUR", BitcoinEuro[].class);
            logger.info(bitCoinEuro[0].toString());
            
            // It should be used something like dozer to create this pojo from json pojo...
            BitcoinEuro jsonPojo = bitCoinEuro[0];
            BitcoinEuroEntity entity = new BitcoinEuroEntity();
            entity.set_24hVolumeEur(jsonPojo.get24hVolumeEur());
            entity.set_24hVolumeUsd(jsonPojo.get24hVolumeUsd());
            entity.setAvailableSupply(jsonPojo.getAvailableSupply());
            entity.setId(jsonPojo.getId());
            entity.setLastUpdated(jsonPojo.getLastUpdated());
            entity.setMarketCapEur(jsonPojo.getMarketCapEur());
            entity.setMarketCapUsd(jsonPojo.getMarketCapUsd());
            entity.setMaxSupply(jsonPojo.getMaxSupply());
            entity.setName(jsonPojo.getName());
            entity.setPriceBtc(jsonPojo.getPriceBtc());
            entity.setPercentChange1h(jsonPojo.getPercentChange1h());
            entity.setPercentChange24h(jsonPojo.getPercentChange24h());
            entity.setPercentChange7d(jsonPojo.getPercentChange7d());
            entity.setPriceEur(jsonPojo.getPriceEur());
            entity.setPriceUsd(jsonPojo.getPriceUsd());
            entity.setRank(jsonPojo.getRank());
            entity.setSymbol(jsonPojo.getSymbol());
            entity.setTotalSupply(jsonPojo.getTotalSupply());
            BitcoinEuroEntity created = bitCoinService.create(entity);
            logger.info("created entity...");
            logger.info(created.toString());
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            logger.error("Error while executing sample job", e);
        } finally {
            logger.info("Sample job has finished...");
        }
    }
}
