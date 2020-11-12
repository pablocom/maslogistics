package masstack.maslogistics.api.controllers;

import masstack.maslogistics.domain.packageAggregate.Packet;

public class PacketDto {
    private final String id;
    private final String description;
    private final String deliveryStatus;

    private PacketDto(String id, String description, String deliveryStatus) {
        this.id = id;
        this.description = description;
        this.deliveryStatus = deliveryStatus;
    }

    public static PacketDto fromAggregate(Packet packet) {
        return new PacketDto(packet.getId().toString(),
                packet.getDescription(),
                packet.getDeliveryStatus());
    }

    public String getDescription() {
        return description;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public String getId() {
        return id;
    }
}
