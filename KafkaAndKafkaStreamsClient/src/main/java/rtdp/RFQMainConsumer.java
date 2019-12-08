package rtdp;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.*;
import org.apache.kafka.streams.kstream.*;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class RFQMainConsumer {
    private static AdminClient admin = null;
    private static StardogAPI api = null;
    private final static String broker = "35.232.69.10:9092";
    private final static String apiUrl = "https://rtdm-flask-client.herokuapp.com/topics?term=";

    public static void main(String[] args){
        Properties config = new Properties();
        config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG,broker);
        admin = AdminClient.create(config);

        api = new StardogAPI();
        api.setApiUrl(apiUrl);

        JSONSerializer<RFQ> rfqJsonSerializer = new JSONSerializer<>();
        JSONDeserializer<RFQ> rfqJsonDeserializer = new JSONDeserializer<>(RFQ.class);
        final Serde<RFQ> rfqSerde = Serdes.serdeFrom(rfqJsonSerializer, rfqJsonDeserializer);
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "streams-rfq");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, broker);
        final StreamsBuilder builder = new StreamsBuilder();
        final KStream<String, RFQ> streamSource = builder.stream("marketplace", Consumed.with(Serdes.String(), rfqSerde));
        streamSource.flatMapValues((s, rfq) -> {
            List<String> results = null;
            api.setCapability(rfq.getCapability());
            results = api.getResults();
            for (String topic : results) {
                if (!topic.equals(rfq.getCapability())) {
                    try {
                        createTopicIfNotExists(topic);
                    } catch (ExecutionException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            return results.stream().map( topic -> new RFQ(rfq,topic)).collect(Collectors.toList());
        }).to((s,rfq,recordContext) -> {
            System.out.println("Transferred to: " + rfq.getTranferringTopic());
            return rfq.getTranferringTopic();
            },Produced.with(Serdes.String(),rfqSerde));
        final Topology topology = builder.build();

        final KafkaStreams streams = new KafkaStreams(topology, props);
        final CountDownLatch latch = new CountDownLatch(1);
        Runtime.getRuntime().addShutdownHook(new Thread("streams-shutdown-hook") {
            @Override
            public void run() {
                streams.close();
                latch.countDown();
            }
        });
        try {
            streams.start();
            latch.await();
        } catch (Throwable e) {
            System.exit(1);
        }
        System.exit(0);
    }

    public static void createTopicIfNotExists(String topic) throws ExecutionException, InterruptedException {

        ListTopicsResult listTopics = admin.listTopics();
        Set<String> names = listTopics.names().get();
        System.out.println(names);
        boolean contains = names.contains(topic);
        if (!contains) {
            System.out.println("Creating new topic:"+ topic);
            List<NewTopic> topicList = new ArrayList<>();
            Map<String, String> configs = new HashMap<>();
            int partitions = 1;
            Short replication = 1;
            NewTopic newTopic = new NewTopic(topic, partitions, replication).configs(configs);
            topicList.add(newTopic);
            admin.createTopics(topicList);
        }
    }

}
