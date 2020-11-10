package masstack.maslogistics.domain.packageAggregate;

import java.util.UUID;

public class Packet {
    private UUID Id;
    private String description;
    private String deliveryStatus;

    public Packet(String description) {
        this.description = description;
        this.deliveryStatus = "PENDING";
    }

    public Packet(UUID id, String description, String deliveryStatus) {
        this.Id = id;
        this.description = description;
        this.deliveryStatus = deliveryStatus;
    }

    public void markAsCompleted() {
        deliveryStatus = "COMPLETED";
    }

    public UUID getId() {
        return this.Id;
    }

    public String getDescription() {
        return this.description;
    }

    public String getDeliveryStatus() {
        return this.deliveryStatus;
    }
}
