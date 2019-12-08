package rtdp;

import org.apache.kafka.common.serialization.Deserializer;
import org.codehaus.jackson.map.ObjectMapper;

import java.util.Map;

public class JSONDeserializer<T> implements Deserializer<T> {

    private ObjectMapper om = new ObjectMapper();
    private Class<T> type;

    public JSONDeserializer() {
    }

    public JSONDeserializer(Class<T> type) {
        this.type = type;
    }

    protected Class<T> getType() {
        return type;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void configure(Map<String, ?> map, boolean arg1) {
        if (type == null) {
            type = (Class<T>) map.get("type");
        }
    }

    @Override
    public T deserialize(String undefined, byte[] bytes) {
        T data = null;
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        try {
            System.out.println(getType());
            data = om.readValue(bytes, type);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    @Override
    public void close() {
    }
}
