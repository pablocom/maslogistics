package unitTests.masstack.maslogistics.common.builders;

import masstack.maslogistics.domain.packageAggregate.Packet;

import java.util.UUID;

public class PacketBuilder {
    private UUID id;
    private String description;
    private String deliveryStatus;

    public Packet build() {
        return new Packet(id, description, deliveryStatus);
    }

    public PacketBuilder withId(UUID id) {
        this.id = id;
        return this;
    }

    public PacketBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public PacketBuilder withDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
        return this;
    }

    public static PacketBuilder validPacket() {
        return new PacketBuilder()
                .withId(UUID.randomUUID())
                .withDescription("SomeDescription");
    }
}
