package com.aironman.demoquartz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aironman.demoquartz.kafka.BitcoinEuroKafkaEntity;
import com.aironman.demoquartz.kafka.MessageProducer;
import com.aironman.demoquartz.model.BitcoinEuroEntity;
import com.aironman.demoquartz.repository.BitCoinEuroRepository;

@Service
public class BitCoinEuroServiceImpl implements BitCoinEuroService {

	@Autowired
	private BitCoinEuroRepository repository;

	@Autowired
	private MessageProducer producer;

	@Override
	@Transactional
	public BitcoinEuroEntity create(BitcoinEuroEntity entity) {
		// TODO Auto-generated method stub
		return repository.save(entity);
	}

	public void sendMessageToTopic(BitcoinEuroKafkaEntity entity) {
		producer.sendMessageToTopic(entity);
	}

	@Override
	public void sendMessageToPartitionedTopicName(BitcoinEuroKafkaEntity entity) {
		producer.sendMessageToPartitionedTopicName(entity);
	}

	@Override
	public void sendMessageToFilteredTopicName(BitcoinEuroKafkaEntity entity) {
		producer.sendMessageToFilteredTopicName(entity);
	}

}
