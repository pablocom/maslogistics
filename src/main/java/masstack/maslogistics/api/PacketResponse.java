package masstack.maslogistics.api;

import masstack.maslogistics.domain.packageAggregate.Packet;

public class PacketResponse {
    private String id;
    private String description;
    private String deliveryStatus;

    private PacketResponse(String id, String description, String deliveryStatus) {
        this.id = id;
        this.description = description;
        this.deliveryStatus = deliveryStatus;
    }

    public static PacketResponse fromAggregate(Packet packet) {
        return new PacketResponse(packet.getId().toString(),
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
