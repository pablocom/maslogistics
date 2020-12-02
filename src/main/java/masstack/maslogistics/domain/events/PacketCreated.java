package masstack.maslogistics.domain.events;

import masstack.maslogistics.domain.DomainEvent;
import masstack.maslogistics.domain.packetAggregate.PacketDeliveryStatus;

import java.util.UUID;

public class PacketCreated extends DomainEvent {
    private UUID id;
    private String description;
    private PacketDeliveryStatus deliveryStatus;

    public PacketCreated(String aggregateId, String description, PacketDeliveryStatus deliveryStatus) {
        super(aggregateId);
        this.description = description;
        this.deliveryStatus = deliveryStatus;
    }

    public UUID getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public PacketDeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }
}
