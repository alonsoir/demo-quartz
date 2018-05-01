package com.aironman.demoquartz.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

public class MessageProducer {

    @Autowired
    KafkaTemplate<String, BitcoinEuroKafkaEntity> producer;
    
    @Value(value = "${message.topic.name}")
    private String topicName;

    @Value(value = "${partitioned.topic.name}")
    private String partionedTopicName;

    @Value(value = "${filtered.topic.name}")
    private String filteredTopicName;

    public void sendMessageToTopic (BitcoinEuroKafkaEntity entity) {
    	producer.send(topicName,entity);
    }
    
    public void sendMessageToPartitionedTopicName(BitcoinEuroKafkaEntity entity) {
    	producer.send(partionedTopicName,entity);
    }
    
    public void sendMessageToFilteredTopicName(BitcoinEuroKafkaEntity entity) {
    	producer.send(filteredTopicName,entity);
    }
}
