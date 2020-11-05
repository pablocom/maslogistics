package masstack.maslogistics.domain;

import java.util.UUID;

public class Packet {
    private UUID Id;
    private String deliveryStatus;

    public Packet() {
        Id = UUID.randomUUID();
        deliveryStatus = "PENDING";
    }

    public void markAsCompleted() {
        deliveryStatus = "COMPLETED";
    }

    public UUID getId() {
        return this.Id;
    }

    public String getDeliveryStatus() {
        return this.deliveryStatus;
    }
}
