package rtdp;

import org.apache.kafka.common.serialization.Serializer;
import org.codehaus.jackson.map.ObjectMapper;

import java.util.Map;

public class JSONSerializer<T> implements Serializer<T> {

    private ObjectMapper om = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public byte[] serialize(String s, T data) {
        byte[] retval = null;
        try {
            System.out.println(data.getClass());
            retval = om.writeValueAsString(data).getBytes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retval;
    }

    @Override
    public void close() {

    }
}