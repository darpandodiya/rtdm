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
