A demo with spring quartz to consume data from a rest api every seconds, saving data to h2, cassandra,kafka, showing data using a websocket channel. 

WORK IN PROGRESS.

1. Consuming data from the rest api every time. DONE
2. Saving data to h2. DONE.
3. Consume data from kafka topic to save it to Cassandra. NOT YET.
4. Saving data to Kafka. Reviewing.
5. Moving data to a websocket channel. NOT YET.
6. Adding docker file. NOT YET.
7. Adding a RESTfull controller. NOT YET. 


Actually i have to run zookeeper and kafka before running the process:

mvn clean package

zkServer start

kafka-server-start /usr/local/etc/kafka/server.properties

# aironman is the topic name. If you want to use another one else, modify message.topic.name within application.properties file, then run again mvn clean package.

kafka-topics --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic aironman

java -jar target/demo-quartz-0.0.1-SNAPSHOT.jar
