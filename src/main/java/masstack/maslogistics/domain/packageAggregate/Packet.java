package masstack.maslogistics.domain.packageAggregate;

import java.util.UUID;


public class Packet {
    private UUID Id;
    private String description;
    private PacketDeliveryStatus deliveryStatus;

    public Packet(UUID id, String description) {
        this.Id = id;
        this.description = description;
        this.deliveryStatus = PacketDeliveryStatus.PENDING;
    }

    public Packet(UUID id, String description, PacketDeliveryStatus deliveryStatus) {
        this.Id = id;
        this.description = description;
        this.deliveryStatus = deliveryStatus;
    }

    public void markAsCompleted() {
        deliveryStatus = PacketDeliveryStatus.DELIVERED;
    }

    public UUID getId() {
        return this.Id;
    }

    public String getDescription() {
        return this.description;
    }

    public PacketDeliveryStatus getDeliveryStatus() {
        return this.deliveryStatus;
    }
}
