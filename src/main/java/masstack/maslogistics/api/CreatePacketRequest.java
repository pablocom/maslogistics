package masstack.maslogistics.api;

public final class CreatePacketRequest {
    private String description;
    private String deliveryStatus;

    public void setDescription(String description) {
        this.description = description;
    }

    String getDescription() {
        return description;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }
}
