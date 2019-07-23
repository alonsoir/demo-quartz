package com.aironman.demoquartz.kafka;

import com.aironman.demoquartz.pojo.Ethereum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

// TODO refactor this class to send data with the same producer...
public class MessageProducer {


    @Autowired
    KafkaTemplate<String, BitcoinEuroKafkaEntity> producer;

    @Autowired
    KafkaTemplate<String, EthereumKafkaEntity> producerEthereum;
    
    @Value(value = "${message.topic.name}")
    private String topicName;

    @Value(value = "${ethereum.topic.name}")
    private String ethereumTopicName;

    @Value(value = "${partitioned.topic.name}")
    private String partionedTopicName;
    
    @Value(value = "${filtered.topic.name}")
    private String filteredTopicName;
    
    public void sendMessageToTopic(BitcoinEuroKafkaEntity entity) {
        final String key = "BTC-" + String.valueOf(System.currentTimeMillis());
	    try {
	        producer.send(topicName, key ,entity);
	    } catch (Exception e) {
	        System.out.println("Something went wrong when sending entity to topic: " + e.getMessage());
	    } catch (Throwable th) {
	        System.out.println("Something went wrong when sending entity to topic: " + th.getMessage());
	    }
    }

    public void sendMessageToTopic(EthereumKafkaEntity entity) {

        final String key = "ETH-" + String.valueOf(System.currentTimeMillis());
        try {
            producerEthereum.send(ethereumTopicName,key, entity);
        } catch (Exception e) {
            System.out.println("Something went wrong when sending entity to topic: " + e.getMessage());
        } catch (Throwable th) {
            System.out.println("Something went wrong when sending entity to topic: " + th.getMessage());
        }
    }
    
    public void sendMessageToPartitionedTopicName(BitcoinEuroKafkaEntity entity) {
	producer.send(partionedTopicName, entity);
    }
    
    public void sendMessageToFilteredTopicName(BitcoinEuroKafkaEntity entity) {
	producer.send(filteredTopicName, entity);
    }
}
