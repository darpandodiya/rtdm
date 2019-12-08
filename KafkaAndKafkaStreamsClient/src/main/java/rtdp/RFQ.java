package rtdp;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonRootName;

import java.io.Serializable;

@JsonRootName("RFQ")
public class RFQ implements Serializable {

    private static final long serialVersionUID = 1L;
    private String tranferringTopic;
    private String id;
    private String version;
    private String operation;
    private String capability;
    private String description;
    private long quantity;
    private String deliveryTime;
    private String manufacturingProcess;
    private String additionalServices;

    public RFQ() {

    }

    public String getTranferringTopic() {
        return tranferringTopic;
    }

    public void setTranferringTopic(String tranferringTopic) {
        this.tranferringTopic = tranferringTopic;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getCapability() {
        return capability;
    }

    public void setCapability(String capability) {
        this.capability = capability;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getManufacturingProcess() {
        return manufacturingProcess;
    }

    public void setManufacturingProcess(String manufacturingProcess) {
        this.manufacturingProcess = manufacturingProcess;
    }

    public String getAdditionalServices() {
        return additionalServices;
    }

    public void setAdditionalServices(String additionalServices) {
        this.additionalServices = additionalServices;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public RFQ(RFQ rfq, String tranferringTopic) {
        this.tranferringTopic = tranferringTopic;
        this.id = rfq.getId();
        this.version = rfq.getVersion();
        this.operation = rfq.getOperation();
        this.capability = rfq.getCapability();
        this.description = rfq.getDescription();
        this.quantity = rfq.getQuantity();
        this.deliveryTime = rfq.getDeliveryTime();
        this.manufacturingProcess = rfq.getManufacturingProcess();
        this.additionalServices = rfq.getAdditionalServices();
    }

    @JsonCreator
    public RFQ(@JsonProperty("id") String id, @JsonProperty("version") String version, @JsonProperty("operation") String operation,
               @JsonProperty("capability") String capability, @JsonProperty("description") String description,
               @JsonProperty("quantity") long quantity, @JsonProperty("deliveryTime") String deliveryTime,
               @JsonProperty("manufacturingProcess") String manufacturingProcess, @JsonProperty("additionalService") String additionalServices) {
        this.id = id;
        this.version = version;
        this.operation = operation;
        this.capability = capability;
        this.description = description;
        this.quantity = quantity;
        this.deliveryTime = deliveryTime;
        this.manufacturingProcess = manufacturingProcess;
        this.additionalServices = additionalServices;
    }
}
