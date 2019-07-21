A demo with spring quartz to consume data from a rest api every seconds, saving data to h2, pushing data to kafka topic. Using docker and docker-compose to build and run everything. 

In a system that implements the CQRS pattern (Command query responsibility segregation) this project would implement the Command part, at least, as I understand it would be to do this part, adding an event Kafka producer in which I create the event with data i want to persist in a scalable way with free writes. in this case with h2, but it could be any other that allows free and scalable writings, such as cassandra. By using spring-boot as a framework glue, it is possible to change the database with very little effort.

To run the project:

     mvn clean install (just in case libraries are not up to date!)
     docker-compose up

You will have to create a kafka topic.

First, figure it out what ip has zookeeper asigned:

          $:demo-quartz aironman$ docker ps
          CONTAINER ID        IMAGE                                 COMMAND                  CREATED             STATUS              PORTS                                                NAMES
          e4f40423c08f        wurstmeister/zookeeper                "/bin/sh -c '/usr/sb…"   11 seconds ago      Up 9 seconds        22/tcp, 2888/tcp, 3888/tcp, 0.0.0.0:2181->2181/tcp   demo-quartz_zookeeper_1
          3e2b2a6f5ab3        wurstmeister/kafka                    "start-kafka.sh"         11 seconds ago      Up 8 seconds        0.0.0.0:9092->9092/tcp                               demo-quartz_kafka_1
          b101a22a6ff7        aironman/demo-quartz:0.0.1-SNAPSHOT   "/usr/bin/java -jar …"   11 seconds ago      Up 9 seconds                                                             demo-quartz_demo-quartz_1
          $:demo-quartz aironman$ docker exec -it e4f40423c08f /bin/sh

          ifconfig
          eth0      Link encap:Ethernet  HWaddr 02:42:ac:13:00:04  
                    inet addr:172.19.0.4  Bcast:172.19.255.255  Mask:255.255.0.0
                    UP BROADCAST RUNNING MULTICAST  MTU:1500  Metric:1
                    RX packets:154 errors:0 dropped:0 overruns:0 frame:0
                    TX packets:98 errors:0 dropped:0 overruns:0 carrier:0
                    collisions:0 txqueuelen:0 
                    RX bytes:14837 (14.8 KB)  TX bytes:11885 (11.8 KB)

          lo        Link encap:Local Loopback  
                    inet addr:127.0.0.1  Mask:255.0.0.0
                    UP LOOPBACK RUNNING  MTU:65536  Metric:1
                    RX packets:0 errors:0 dropped:0 overruns:0 frame:0
                    TX packets:0 errors:0 dropped:0 overruns:0 carrier:0
                    collisions:0 txqueuelen:1 
                    RX bytes:0 (0.0 B)  TX bytes:0 (0.0 B)


          $:demo-quartz aironman$ docker exec -it 3e2b2a6f5ab3 /bin/sh
          / # ifconfig
          eth0      Link encap:Ethernet  HWaddr 02:42:AC:13:00:03  
                    inet addr:172.19.0.3  Bcast:172.19.255.255  Mask:255.255.0.0
                    UP BROADCAST RUNNING MULTICAST  MTU:1500  Metric:1
                    RX packets:265 errors:0 dropped:0 overruns:0 frame:0
                    TX packets:345 errors:0 dropped:0 overruns:0 carrier:0
                    collisions:0 txqueuelen:0 
                    RX bytes:35465 (34.6 KiB)  TX bytes:29721 (29.0 KiB)

          lo        Link encap:Local Loopback  
                    inet addr:127.0.0.1  Mask:255.0.0.0
                    UP LOOPBACK RUNNING  MTU:65536  Metric:1
                    RX packets:23 errors:0 dropped:0 overruns:0 frame:0
                    TX packets:23 errors:0 dropped:0 overruns:0 carrier:0
                    collisions:0 txqueuelen:1 
                    RX bytes:1832 (1.7 KiB)  TX bytes:1832 (1.7 KiB)

          / # kafka-topics.sh --list --zookeeper 172.19.0.4:2181
          aironman

I already have this topic in my local containers, you will have to run within kafka container a command like this:

          kafka-topics.sh --create --zookeeper 172.10.0.4:2181 --replication-factor 1 --partitions 1 --topic aironman

You can change aironman topic`s name with whatever you want. 
Check application.properties file and change this line:

          message.topic.name=aironman

TODO

     Create an interface an its implementation service class to manage h2 pojos and a decent CommandHandler, because BitCoinEuroServiceImpl class is breaking single responsibility principle. 

     CommandHandler should inherit BitCoinEuroService and KafkaProducer and KafkaConsumer. 
     
     With KafkaProducer in CommandHandler, it should indicate to Query (Q in CQRS) that data is already persisted and ready to be consumed in query part.

     Create handlers to introduce data from another cryptocurrencies into kafka topics.

     Why Eureka server is not running while i run the server? wtf! because it is not present in docker-compose.yml. CHECK!