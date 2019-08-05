A demo with spring quartz to consume data from a rest api every seconds, saving data to h2, pushing data to kafka topic. Using docker and docker-compose to build and run everything. 

In a system that implements the CQRS pattern (Command query responsibility segregation) this project would implement the Command part, at least, as I understand it would be to do this part, adding an event Kafka producer in which I create the event with data i want to persist in a scalable way with free writes. in this case with h2, but it could be any other that allows free and scalable writings, such as cassandra. By using spring-boot as a framework glue, it is possible to change the database with very little effort.

To run the project, docker must be up and running:

     mvn clean install 
     // optional if you want to push the project to your docker hub!
     mvn dockerfile:build dockerfile:push
     docker-compose up

You will have to create a kafka topic.

First, figure it out what ip has zookeeper asigned:

          ~/D/demo-quartz> docker container ls
          CONTAINER ID        IMAGE                                    COMMAND                  CREATED             STATUS              PORTS                                              NAMES
          26b3d4d05da0        aironman/demo-quartz:0.0.2-SNAPSHOT      "java -jar /opt/app/…"   30 minutes ago      Up 30 minutes                                                          demo-quartz_demo-quartz_1
          bb1bf6b11b76        confluentinc/cp-enterprise-kafka:5.0.0   "/etc/confluent/dock…"   30 minutes ago      Up 30 minutes       0.0.0.0:9092->9092/tcp, 0.0.0.0:29092->29092/tcp   kafka
          f7074f1c4001        confluentinc/cp-zookeeper:5.0.0          "/etc/confluent/dock…"   30 minutes ago      Up 30 minutes       2888/tcp, 0.0.0.0:2181->2181/tcp, 3888/tcp         zookeeper
          35bed865bcbc        springcloud/eureka                       "java -jar /app.jar"     8 days ago          Up 30 minutes       0.0.0.0:8761->8761/tcp                             demo-quartz_eureka-server_1
          

I found that with latest confluentinc components, topics are created when the app is running, so there will no need to login into kafka container and create the topics.
You can change aironman topic`s name with whatever you want. 

Check application.properties file and change this line:

          message.topic.name=btc-topic
          ethereum.topic.name=eth-topic

If you want to see how data is pushed into kafka topic, run this command in your localhost:

    ~/D/demo-quartz> kafka-console-consumer --bootstrap-server 0.0.0.0:29092 --topic aironman --from-beginning --property print.key=true

    EXPLANATION
    
    If you get notice about bootstrap-server, 29092 port is outbounded to the exter, meanwhile, 9092 is the inner to docker containers.
    
TODO

     Create an interface an its implementation service class to manage h2 pojos and a decent CommandHandler, because BitCoinEuroServiceImpl class is breaking single responsibility principle. 

     CommandHandler should inherit BitCoinEuroService and KafkaProducer and KafkaConsumer. 
     
     With KafkaProducer in CommandHandler, it should indicate to Query (Q in CQRS) that data is already persisted and ready to be consumed in query part.

     Create handlers to introduce data from another cryptocurrencies into kafka topics.
        In progress. Ethereum handlers and service is already created. I have to activate it through docker-compose.yml
        
     Why Eureka server is not running while i run the server? wtf! because it is not present in docker-compose.yml. CHECK!
     
     I have to run two services within docker-compose.yml. One for BTC, another for ETH. I have to figure out how to pass arguments to Docker...
        In progress...
         
TROUBLESHOOTING

     I had encountered problems with latest kafka release, but reading this thread, it got solved. 
     https://stackoverflow.com/questions/35788697/leader-not-available-kafka-in-console-producer
     
     I have problems with Eureka server...
     
     eureka-server_1  | 2019-07-30 10:57:21.113  WARN 1 --- [nio-8761-exec-5] com.netflix.eureka.InstanceRegistry      : DS: Registry: lease doesn't exist, registering resource: DEMO-QUARTZ - 26b3d4d05da0:demo-quartz:0
     eureka-server_1  | 2019-07-30 10:57:21.114  WARN 1 --- [nio-8761-exec-5] c.n.eureka.resources.InstanceResource    : Not Found (Renew): DEMO-QUARTZ - 26b3d4d05da0:demo-quartz:0
     demo-quartz_1    | web - 2019-07-30 10:57:21,118 [DiscoveryClient-HeartbeatExecutor-0] ERROR c.n.d.s.t.d.RedirectingEurekaHttpClient - Request execution error
     demo-quartz_1    | javax.ws.rs.WebApplicationException: null
     demo-quartz_1    | 	at com.netflix.discovery.provider.DiscoveryJerseyProvider.readFrom(DiscoveryJerseyProvider.java:110)
     demo-quartz_1    | 	at com.sun.jersey.api.client.ClientResponse.getEntity(ClientResponse.java:634)
     demo-quartz_1    | 	at com.sun.jersey.api.client.ClientResponse.getEntity(ClientResponse.java:586)
     demo-quartz_1    | 	at com.netflix.discovery.shared.transport.jersey.AbstractJerseyEurekaHttpClient.sendHeartBeat(AbstractJerseyEurekaHttpClient.java:105)
     demo-quartz_1    | 	at com.netflix.discovery.shared.transport.decorator.EurekaHttpClientDecorator$3.execute(EurekaHttpClientDecorator.java:92)
     demo-quartz_1    | 	at com.netflix.discovery.shared.transport.decorator.MetricsCollectingEurekaHttpClient.execute(MetricsCollectingEurekaHttpClient.java:73)
     demo-quartz_1    | 	at com.netflix.discovery.shared.transport.decorator.EurekaHttpClientDecorator.sendHeartBeat(EurekaHttpClientDecorator.java:89)
     demo-quartz_1    | 	at com.netflix.discovery.shared.transport.decorator.EurekaHttpClientDecorator$3.execute(EurekaHttpClientDecorator.java:92)
     demo-quartz_1    | 	at com.netflix.discovery.shared.transport.decorator.RedirectingEurekaHttpClient.executeOnNewServer(RedirectingEurekaHttpClient.java:118)
     demo-quartz_1    | 	at com.netflix.discovery.shared.transport.decorator.RedirectingEurekaHttpClient.execute(RedirectingEurekaHttpClient.java:79)
     demo-quartz_1    | 	at com.netflix.discovery.shared.transport.decorator.EurekaHttpClientDecorator.sendHeartBeat(EurekaHttpClientDecorator.java:89)
     demo-quartz_1    | 	at com.netflix.discovery.shared.transport.decorator.EurekaHttpClientDecorator$3.execute(EurekaHttpClientDecorator.java:92)
     demo-quartz_1    | 	at com.netflix.discovery.shared.transport.decorator.RetryableEurekaHttpClient.execute(RetryableEurekaHttpClient.java:119)
     demo-quartz_1    | 	at com.netflix.discovery.shared.transport.decorator.EurekaHttpClientDecorator.sendHeartBeat(EurekaHttpClientDecorator.java:89)
     demo-quartz_1    | 	at com.netflix.discovery.shared.transport.decorator.EurekaHttpClientDecorator$3.execute(EurekaHttpClientDecorator.java:92)
     demo-quartz_1    | 	at com.netflix.discovery.shared.transport.decorator.SessionedEurekaHttpClient.execute(SessionedEurekaHttpClient.java:77)
     demo-quartz_1    | 	at com.netflix.discovery.shared.transport.decorator.EurekaHttpClientDecorator.sendHeartBeat(EurekaHttpClientDecorator.java:89)
     demo-quartz_1    | 	at com.netflix.discovery.DiscoveryClient.renew(DiscoveryClient.java:824)
     demo-quartz_1    | 	at com.netflix.discovery.DiscoveryClient$HeartbeatThread.run(DiscoveryClient.java:1393)
     demo-quartz_1    | 	at java.base/java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:515)
     demo-quartz_1    | 	at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264)
     demo-quartz_1    | 	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1128)
     demo-quartz_1    | 	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:628)
     demo-quartz_1    | 	at java.base/java.lang.Thread.run(Thread.java:835)
     demo-quartz_1    | web - 2019-07-30 10:57:21,118 [DiscoveryClient-HeartbeatExecutor-0] WARN  c.n.d.s.t.d.RetryableEurekaHttpClient - Request execution failed with message: null
     demo-quartz_1    | web - 2019-07-30 10:57:21,118 [DiscoveryClient-HeartbeatExecutor-0] ERROR c.netflix.discovery.DiscoveryClient - DiscoveryClient_DEMO-QUARTZ/26b3d4d05da0:demo-quartz:0 - was unable to send heartbeat!
     demo-quartz_1    | com.netflix.discovery.shared.transport.TransportException: Cannot execute request on any known server
     demo-quartz_1    | 	at com.netflix.discovery.shared.transport.decorator.RetryableEurekaHttpClient.execute(RetryableEurekaHttpClient.java:111)
     demo-quartz_1    | 	at com.netflix.discovery.shared.transport.decorator.EurekaHttpClientDecorator.sendHeartBeat(EurekaHttpClientDecorator.java:89)
     demo-quartz_1    | 	at com.netflix.discovery.shared.transport.decorator.EurekaHttpClientDecorator$3.execute(EurekaHttpClientDecorator.java:92)
     demo-quartz_1    | 	at com.netflix.discovery.shared.transport.decorator.SessionedEurekaHttpClient.execute(SessionedEurekaHttpClient.java:77)
     demo-quartz_1    | 	at com.netflix.discovery.shared.transport.decorator.EurekaHttpClientDecorator.sendHeartBeat(EurekaHttpClientDecorator.java:89)
     demo-quartz_1    | 	at com.netflix.discovery.DiscoveryClient.renew(DiscoveryClient.java:824)
     demo-quartz_1    | 	at com.netflix.discovery.DiscoveryClient$HeartbeatThread.run(DiscoveryClient.java:1393)
     demo-quartz_1    | 	at java.base/java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:515)
     demo-quartz_1    | 	at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264)
     demo-quartz_1    | 	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1128)
     demo-quartz_1    | 	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:628)
     demo-quartz_1    | 	at java.base/java.lang.Thread.run(Thread.java:835)
     demo-quartz_1    | web - 2019-07-30 10:57:27,605 [scheduler_Worker-2] INFO  c.a.demoquartz.scheduler.SampleJob - SampleJob ** BTC-Qrtz_Job_Detail ** fired @ Tue Jul 30 10:57:27 GMT 2019
     demo-quartz_1    | web - 2019-07-30 10:57:27,606 [scheduler_Worker-2] INFO  c.a.d.service.SampleJobService - The sample job has begun...
     demo-quartz_1    | web - 2019-07-30 10:57:27,680 [scheduler_Worker-2] INFO  c.a.d.service.SampleJobService - com.aironman.demoquartz.pojo.BitcoinEuro@3c8b87d9[id=bitcoin,
     demo-quartz_1    | ,name=Bitcoin,
     demo-quartz_1    | ,symbol=BTC,
     demo-quartz_1    | ,rank=1,
     demo-quartz_1    | ,priceUsd=9500.75890644,
     demo-quartz_1    | ,priceBtc=1.0,_24hVolumeUsd=13196573183.1,
     demo-quartz_1    | ,marketCapUsd=169558970617,
     demo-quartz_1    | ,availableSupply=17846887.0,
     demo-quartz_1    | ,totalSupply=17846887.0,
     demo-quartz_1    | ,maxSupply=21000000.0,
     demo-quartz_1    | ,percentChange1h=-0.06,
     demo-quartz_1    | ,percentChange24h=-0.03,
     demo-quartz_1    | ,percentChange7d=-5.29,
     demo-quartz_1    | ,lastUpdated=1564484132,
     demo-quartz_1    | ,priceEur=8523.56784988,
     demo-quartz_1    | ,_24hVolumeEur=11839252845.0,
     demo-quartz_1    | ,marketCapEur=152119152254,
     demo-quartz_1    | ,additionalProperties={}]
     demo-quartz_1    | web - 2019-07-30 10:57:27,683 [scheduler_Worker-2] INFO  c.a.d.service.SampleJobService - created entity...
     demo-quartz_1    | web - 2019-07-30 10:57:27,683 [scheduler_Worker-2] INFO  c.a.d.service.SampleJobService - BitcoinEuroEntity [idBCEntity=239, id=bitcoin, name=Bitcoin, symbol=BTC, rank=1, priceUsd=9500.75890644, priceBtc=1.0, _24hVolumeUsd=13196573183.1, marketCapUsd=169558970617, availableSupply=17846887.0, totalSupply=17846887.0, maxSupply=21000000.0, percentChange1h=-0.06, percentChange24h=-0.03, percentChange7d=-5.29, lastUpdated=1564484132, priceEur=8523.56784988, _24hVolumeEur=11839252845.0, marketCapEur=152119152254]
     demo-quartz_1    | web - 2019-07-30 10:57:27,683 [scheduler_Worker-2] INFO  c.a.d.service.SampleJobService - entity sent to topic...
     demo-quartz_1    | web - 2019-07-30 10:57:27,683 [scheduler_Worker-2] INFO  c.a.d.service.SampleJobService - Sample job has finished...
     demo-quartz_1    | web - 2019-07-30 10:57:27,683 [scheduler_Worker-2] INFO  c.a.demoquartz.scheduler.SampleJob - Next SampleJob scheduled @ Tue Jul 30 10:57:37 GMT 2019
     





