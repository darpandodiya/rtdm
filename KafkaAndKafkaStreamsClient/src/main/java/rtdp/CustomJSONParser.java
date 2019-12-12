package rtdp;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.kafka.common.protocol.types.Field;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import scala.util.parsing.json.JSON;

public class CustomJSONParser {
    public static RFQ readJSON(String fileName) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        RFQ rfq = new RFQ();
        FileReader reader = new FileReader(fileName);
        Object obj = jsonParser.parse(reader);
        JSONArray array = (JSONArray) obj;
        JSONObject rfqObj = (JSONObject)array.get(0);
        rfq.setId((String) rfqObj.get("id"));
        rfq.setVersion((String) rfqObj.get("version"));
        rfq.setOperation((String) rfqObj.get("operation"));
        rfq.setDescription((String) rfqObj.get("description"));
        rfq.setQuantity((Long) rfqObj.get("quantity"));
        rfq.setCapability((String) rfqObj.get("capability"));
        rfq.setDeliveryTime((String) rfqObj.get("deliveryTime"));
        rfq.setAdditionalServices((String) rfqObj.get("additionalServices"));
        return rfq;
    }
}

