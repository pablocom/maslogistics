package masstack.maslogistics.api.controllers;

public final class CreatePacketRequest {
    private String description;
    private String deliveryStatus;

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }
}
