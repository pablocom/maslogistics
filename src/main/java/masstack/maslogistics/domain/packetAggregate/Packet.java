package masstack.maslogistics.domain.packetAggregate;

import masstack.maslogistics.domain.AggregateRoot;
import masstack.maslogistics.domain.DomainEventPublisher;
import masstack.maslogistics.domain.events.PacketCreated;

import java.util.*;

public class Packet implements AggregateRoot {
    private UUID id;
    private String description;
    private PacketDeliveryStatus deliveryStatus;
    private List<Product> products;

    public Packet(UUID id, String description) {
        this.id = id;
        this.description = description;
        this.deliveryStatus = PacketDeliveryStatus.PENDING;
        this.products = new ArrayList<>();

        DomainEventPublisher.instance()
                .publish(new PacketCreated(this.id.toString(), this.description, this.deliveryStatus));
    }

    public Packet(UUID id, String description, PacketDeliveryStatus deliveryStatus, List<Product> products) {
        this.id = id;
        this.description = description;
        this.deliveryStatus = deliveryStatus;
        this.products = products;

        DomainEventPublisher.instance()
                .publish(new PacketCreated(this.id.toString(), this.description, this.deliveryStatus));
    }

    public void markAsCompleted() {
        deliveryStatus = PacketDeliveryStatus.DELIVERED;
    }

    public UUID getId() {
        return this.id;
    }

    public String getDescription() {
        return this.description;
    }

    public PacketDeliveryStatus getDeliveryStatus() {
        return this.deliveryStatus;
    }

    public List<Product> getProducts() {
        return this.products;
    }
}
