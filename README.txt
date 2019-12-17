The project involves multiple components. Each of which is described below:

1. KafkaAndKafkaStreamsClient/
	
	It comprises KafkaStreams Consumer and a Kafka Producer.
	The files in this folder are as follows:
	(a) CustomJSONParser:
		It is a custom JSON parser to parse the RFQ json file in RFQ Java Class for other uses.
	(b) JSONDeserializer:
		It is a deserializer used for deserializer received messages from Kafka message bus in the consumer
	(c) JSONSerializer:
		It is a serializer used for serializing messages to be sent on Kafka message bus
	(d) KafkaRFQProducer:
		This producer simulates an action to send RFQ message on a kafka server.
	(e) RFQ:
		This is the JAVA class for encapsulating RFQ properties for internal use
	(f) rfq:
		It is a RFQ json file which needs to be read and send on Kafka 
	(f) RFQMainConsumer:
		This is the main consumer file. It receives messages from kafka on topic "marketplace" and redirects those RFQ to relevant topics
	(g) StardogAPI:
		This file is used to call the Stardog server API hook with the query and use the response received in the usecase


NOTE: Before starting any files, you need to install Kafka on your machine and start zookeeper server and kafka server. You can follow this guide: https://www.tutorialspoint.com/apache_kafka/apache_kafka_installation_steps.htm
In our project, we hosted kafka and zookeeper on GCP at this endpoint: 35.232.69.10:9092.


To start the consumer, you need to run the "RFQMainConsumer" file. You can do that by opening the whole folder in IntelliJ or Eclipse and running the main class in the file "RFQMainConsumer".


In order to send a message on Kafka bus, you need to use KafkaAndKafkaStreamsClient/target/KafkaAndKafkaStreamsClient-0.1-jar-with-dependencies.jar along with the topic name to subscribe to and kafka server URL with the following command:

java -jar KafkaAndKafkaStreamsClient/target/KafkaAndKafkaStreamsClient-0.1-jar-with-dependencies.jar marketplace 35.232.69.10:9092

and it will send a RFQ accordingly.

NOTE: If you want to change the stardog API hook, you can do that by specifying it in the "StardogAPI" file.

