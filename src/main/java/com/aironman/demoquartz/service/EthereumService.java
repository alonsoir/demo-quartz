package com.aironman.demoquartz.service;

import com.aironman.demoquartz.kafka.EthereumKafkaEntity;
import com.aironman.demoquartz.model.EthereumEntity;

public interface EthereumService {

    EthereumEntity create (EthereumEntity entity);

    void sendMessageToTopic(EthereumKafkaEntity entity) ;

}
