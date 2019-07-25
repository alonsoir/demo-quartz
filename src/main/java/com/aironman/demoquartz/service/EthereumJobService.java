package com.aironman.demoquartz.service;

import com.aironman.demoquartz.kafka.EthereumKafkaEntity;
import com.aironman.demoquartz.model.EthereumEntity;
import com.aironman.demoquartz.pojo.Ethereum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EthereumJobService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private EthereumService service;

    private final String url = "https://api.coinmarketcap.com/v1/ticker/ethereum/?convert=EUR";

    public void executeJob() {

        logger.info("The sample job has begun...");
        try {
            RestTemplate restTemplate = new RestTemplate();
            Ethereum[] ethereums = restTemplate.getForObject(url, Ethereum[].class);
            logger.info(ethereums[0].toString());
            Ethereum jsonPojo = ethereums[0];

            EthereumEntity  entity = generatePojoFromJson(jsonPojo);
            EthereumEntity created = service.create(entity);
            logger.info("created entity...");
            logger.info(created.toString());
            // I want this id for kafka topic, it should be the id for ES too...
            jsonPojo.setId(String.valueOf(created.getIdETHEntity()));
            EthereumKafkaEntity kafkaEntity = generateKafkaPojoFromJson(jsonPojo);
            service.sendMessageToTopic(kafkaEntity);
            logger.info("entity sent to topic...");

            //Thread.sleep(5000);
        //} catch (InterruptedException e) {
            // logger.error("Error while executing sample job", e);
        } finally {
            logger.info("Sample job has finished...");
        }
    }

    private EthereumEntity generatePojoFromJson(Ethereum jsonPojo) {
        // It should be used something like dozer to create this pojo from json pojo...
        EthereumEntity  entity = new EthereumEntity ();
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
        return entity;
    }

    private EthereumKafkaEntity generateKafkaPojoFromJson(Ethereum jsonPojo) {
        // It should be used something like dozer to create this pojo from json pojo...
        EthereumKafkaEntity entity = new EthereumKafkaEntity();
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
        return entity;
    }


}
