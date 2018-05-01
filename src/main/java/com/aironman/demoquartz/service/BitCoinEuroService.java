package com.aironman.demoquartz.service;

import com.aironman.demoquartz.kafka.BitcoinEuroKafkaEntity;
import com.aironman.demoquartz.model.BitcoinEuroEntity;

public interface BitCoinEuroService {

	BitcoinEuroEntity create(BitcoinEuroEntity entity);
	void sendMessageToTopic(BitcoinEuroKafkaEntity entity);
	void sendMessageToPartitionedTopicName(BitcoinEuroKafkaEntity entity);
	void sendMessageToFilteredTopicName(BitcoinEuroKafkaEntity entity);
}
