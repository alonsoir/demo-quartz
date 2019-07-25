package com.aironman.demoquartz.service;

import com.aironman.demoquartz.DemoQuartzApplication;
import com.aironman.demoquartz.kafka.EthereumKafkaEntity;
import com.aironman.demoquartz.model.EthereumEntity;
import com.aironman.demoquartz.pojo.Ethereum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.aironman.demoquartz.kafka.BitcoinEuroKafkaEntity;
import com.aironman.demoquartz.model.BitcoinEuroEntity;
import com.aironman.demoquartz.pojo.BitcoinEuro;

/***
 * Generic Service to handle CRYPTO_CURRENCIES
 */
@Service
public class SampleJobService {
    
    @Autowired
    private BitCoinEuroService bitCoinService;

	@Autowired
	private EthereumService ethereumService;

    
    private Logger logger = LoggerFactory.getLogger(getClass());

    private final String URL_BTC = "https://api.coinmarketcap.com/v1/ticker/bitcoin/?convert=EUR";

	private final String URL_ETH = "https://api.coinmarketcap.com/v1/ticker/ethereum/?convert=EUR";

	public void executeSampleJob() {
	
	logger.info("The sample job has begun...");
	try {
		if (DemoQuartzApplication.cryptoType.equals("btc")) bitCoinJob();
		if (DemoQuartzApplication.cryptoType.equals("eth")) ethereumJob();

	} finally {
	    logger.info("Sample job has finished...");
	}
    }

	private void ethereumJob() {
		RestTemplate restTemplate = new RestTemplate();
		Ethereum[] ethereums = restTemplate.getForObject(URL_ETH, Ethereum[].class);
		logger.info(ethereums[0].toString());
		Ethereum jsonPojo = ethereums[0];

		EthereumEntity entity = generatePojoFromJson(jsonPojo);
		EthereumEntity created = ethereumService.create(entity);
		logger.info("created entity...");
		logger.info(created.toString());
		// I want this id for kafka topic, it should be the id for ES too...
		jsonPojo.setId(String.valueOf(created.getIdETHEntity()));
		EthereumKafkaEntity kafkaEntity = generateKafkaPojoFromJson(jsonPojo);
		ethereumService.sendMessageToTopic(kafkaEntity);
		logger.info("entity sent to topic...");
	}

	private void bitCoinJob() {
		RestTemplate restTemplate = new RestTemplate();
		BitcoinEuro[] bitCoinEuro = restTemplate.getForObject(URL_BTC,
				BitcoinEuro[].class);
		logger.info(bitCoinEuro[0].toString());
		BitcoinEuro jsonPojo = bitCoinEuro[0];

		BitcoinEuroEntity entity = generatePojoFromJson(jsonPojo);
		BitcoinEuroEntity created = bitCoinService.create(entity);
		logger.info("created entity...");
		logger.info(created.toString());
		// I want this id for kafka topic, it should be the id for ES too...
		jsonPojo.setId(String.valueOf(created.getIdBCEntity()));
		BitcoinEuroKafkaEntity kafkaEntity = generateKafkaPojoFromJson(jsonPojo);
		bitCoinService.sendMessageToTopic(kafkaEntity);
		logger.info("entity sent to topic...");
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

	private BitcoinEuroEntity generatePojoFromJson(BitcoinEuro jsonPojo) {
	// It should be used something like dozer to create this pojo from json pojo...
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
	return entity;
    }
    
    private BitcoinEuroKafkaEntity generateKafkaPojoFromJson(BitcoinEuro jsonPojo) {
	// It should be used something like dozer to create this pojo from json pojo...
	BitcoinEuroKafkaEntity entity = new BitcoinEuroKafkaEntity();
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
