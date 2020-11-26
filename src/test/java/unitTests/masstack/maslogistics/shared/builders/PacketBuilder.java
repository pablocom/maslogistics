package unitTests.masstack.maslogistics.shared.builders;

import masstack.maslogistics.domain.packageAggregate.Packet;
import masstack.maslogistics.domain.packageAggregate.PacketDeliveryStatus;

import java.util.UUID;

public class PacketBuilder {
    private UUID id;
    private String description;
    private PacketDeliveryStatus deliveryStatus;

    public Packet build() {
        return new Packet(id, description, deliveryStatus);
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

    public static PacketBuilder validPacket() {
        return new PacketBuilder()
                .withId(UUID.randomUUID())
                .withDescription("SomeDescription")
                .withDeliveryStatus(PacketDeliveryStatus.PENDING);
    }
}