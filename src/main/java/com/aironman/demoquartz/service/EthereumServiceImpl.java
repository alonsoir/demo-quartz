package com.aironman.demoquartz.service;

import com.aironman.demoquartz.kafka.EthereumKafkaEntity;
import com.aironman.demoquartz.kafka.MessageProducer;
import com.aironman.demoquartz.model.EthereumEntity;
import com.aironman.demoquartz.repository.EthereumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EthereumServiceImpl implements EthereumService {

    @Autowired
    EthereumRepository repository;

    @Autowired
    private MessageProducer producer;

    @Override
    public EthereumEntity create(EthereumEntity entity) {
        return repository.save(entity);
    }

    @Override
    public void sendMessageToTopic(EthereumKafkaEntity entity) {
        producer.sendMessageToTopic(entity);
    }
}
