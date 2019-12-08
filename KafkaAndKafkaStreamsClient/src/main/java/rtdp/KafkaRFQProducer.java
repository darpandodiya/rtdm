package rtdp;

import org.apache.kafka.clients.producer.*;
import java.util.Properties;

public class KafkaRFQProducer {
    public static void main(String[] args) throws Exception{
        if (args.length != 2) {
            System.out.print("Please specify the topic name and kafka server url in the argument");
            return;
        }
        String topicName = args[0];
        String url = args[1];
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,url);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "KafkaExampleProducer");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JSONSerializer.class.getName());
        final Producer<String, RFQ> producer = new KafkaProducer<>(props);

        for (int i = 0; i < 10; i++) {
            RFQ rfq = new RFQ();
            rfq.setCapability("Bagging");
            ProducerRecord<String, RFQ > record = new ProducerRecord<>(topicName, rfq);
            System.out.println("Sent:"+ record);
            producer.send(record);
            Thread.sleep(250);
        }
        producer.close();

    }
}
