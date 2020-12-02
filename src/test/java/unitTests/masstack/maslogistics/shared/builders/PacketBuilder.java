package unitTests.masstack.maslogistics.shared.builders;

import masstack.maslogistics.domain.packetAggregate.Packet;
import masstack.maslogistics.domain.packetAggregate.PacketDeliveryStatus;
import masstack.maslogistics.domain.packetAggregate.Product;

import java.util.List;
import java.util.UUID;

public class PacketBuilder {
    private UUID id;
    private String description;
    private PacketDeliveryStatus deliveryStatus;
    private Product[] products = new Product[0];

    public Packet build() {
        var packet = new Packet(id, description, deliveryStatus, List.of(this.products));
        return packet;
    }

    public PacketBuilder withId(UUID id) {
        this.id = id;
        return this;
    }

    public PacketBuilder withId(String id) {
        this.id = UUID.fromString(id);
        return this;
    }

    public PacketBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public PacketBuilder withDeliveryStatus(PacketDeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
        return this;
    }

    public PacketBuilder withProducts(Product... products) {
        this.products = products.clone();
        return this;
    }

    public static PacketBuilder validPacket() {
        return new PacketBuilder()
                .withId(UUID.randomUUID())
                .withDescription("SomeDescription")
                .withDeliveryStatus(PacketDeliveryStatus.PENDING);
    }
}
