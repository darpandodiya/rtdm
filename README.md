# Semantic Event Detection in SmartChainDB

Product manufacturing life cycle starts with Request for Quote (RFQ) process. In the RFQ process,
a Contract Manufacturer (CM) is chosen, and the price, delivery, and other terms are negotiated.
In a marketplace, the manufacturers listen to the RFQs they are capable of manufacturing. The
semantics of such RFQs could be complex and implicit. This could lead to some manufacturers not
receiving RFQs even though they possess the capability to manufacture the requested product.

In this project, we attempt to map such complex and vague RFQ events into an appropriate
publish/subscribe event detection model. Kafka would be used for publish/subscribe mode. Event
publisher would be a marketplace events from SmartChainDB and event consumer would be manufacturers listening to Kafka topics. Terms and relationships of the requests would be captured in
an ontology.

Please refer to the report present in this repository for more details on this project. This README primarily serves as code setup guide. 

Link to GitHub repository: https://github.com/darpandodiya/rtdm

## Important Links
Stardog DigitalOcean IP Address: 45.55.41.230:5820

Heroku Git Repo: https://git.heroku.com/rtdm-flask-client.git

Endpoint to query related terms from Stardog
https://rtdm-flask-client.herokuapp.com/

Example of query:
https://rtdm-flask-client.herokuapp.com/topics?term=Bagging

GCP IP for Kafka Host: 35.232.69.10:9092

## Major Components

1. Kafka and Kafka Streams Client
2. Stardog Flask API Client
3. Ontology UI 

## Kafka and Kafka Streams Client Setup Instructions

## Stardog Flask API Client
The client to interact with Stardog server has been written in Python with the use of Flask framework. 

The code for this component can be found under *flask-client* folder of this repository. The steps are:

1. Clone the repo
2. Go the folder *flask-client* 
3. Make sure to install Python3 and pip tool
4. Run command, `pip install -r requirements.txt`
5. Once all the requirements have been installed, run, `python app.py`
6. This will start local Flask server and the API will be available at http://localhost:5000
7. The main code is into `stardog_util.py`
8. You may need to change connection strings found in `stardog_util.py` to connect to your Stardog Server
9. Additionally, after the setup is done, you may want to host the Flask app to public cloud provider such as AWS or Heroku

## Ontology UI 
The ontology visualization UI is built using simple HTML and JavaScript so it is faily easy to setup and get running. 

The code for this component can be found under *stardog-gui* folder of this repository. 

The main code is in demo.html file, you may need to change the first few lines to change connection details. 

After that, opening demo.html file in any web browser will start the Ontology UI. No additional actions or setup steps are needed, all the required Javascript files have been included in the folder. 


## KafkaAndKafkaStreamsClient

It comprises KafkaStreams Consumer and a Kafka Producer.
The files in this folder are as follows:
1. CustomJSONParser: 
    It is a custom JSON parser to parse the RFQ json file in RFQ Java Class for other uses.
2. JSONDeserializer:
    It is a deserializer used for deserializer received messages from Kafka message bus in the consumer
3. JSONSerializer:
    It is a serializer used for serializing messages to be sent on Kafka message bus
4. KafkaRFQProducer:
    This producer simulates an action to send RFQ message on a kafka server.
5. RFQ:
    This is the JAVA class for encapsulating RFQ properties for internal use
6. rfq:
    It is a RFQ json file which needs to be read and send on Kafka 
7. RFQMainConsumer:
    This is the main consumer file. It receives messages from kafka on topic "marketplace" and redirects those RFQ to relevant topics
8. StardogAPI:
    This file is used to call the Stardog server API hook with the query and use the response received in the usecase

#### NOTE: Before starting any files, you need to install Kafka on your machine and start zookeeper server and kafka server. You can follow this guide: https://www.tutorialspoint.com/apache_kafka/apache_kafka_installation_steps.htm. In our project, we hosted kafka and zookeeper on GCP at this endpoint: 35.232.69.10:9092.


``` To start the consumer, you need to run the "RFQMainConsumer" file. You can do that by opening the whole folder in IntelliJ or Eclipse and running the main class in the file "RFQMainConsumer".```


In order to send a message on Kafka bus, you need to use KafkaAndKafkaStreamsClient/target/KafkaAndKafkaStreamsClient-0.1-jar-with-dependencies.jar along with the topic name to subscribe to and kafka server URL with the following command:

```java -jar KafkaAndKafkaStreamsClient/target/KafkaAndKafkaStreamsClient-0.1-jar-with-dependencies.jar marketplace 35.232.69.10:9092```

and it will send a RFQ accordingly.

#### NOTE: If you want to change the stardog API hook, you can do that by specifying it in the "StardogAPI" file.



