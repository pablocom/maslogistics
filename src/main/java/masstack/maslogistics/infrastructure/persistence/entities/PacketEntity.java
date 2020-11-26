package masstack.maslogistics.infrastructure.persistence.entities;

import masstack.maslogistics.domain.packageAggregate.Packet;
import masstack.maslogistics.domain.packageAggregate.PacketDeliveryStatus;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "packets")
public class PacketEntity implements Serializable {
    @Id
    private UUID Id;
    private String description;
    private PacketDeliveryStatus deliveryStatus;

    public PacketEntity(Packet packet) {
        this.Id = packet.getId();
        this.description = packet.getDescription();
        this.deliveryStatus = packet.getDeliveryStatus();
    }

    // Hibernate required default constructor
    protected PacketEntity() {}

    public PacketDeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(PacketDeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public UUID getId() {
        return Id;
    }

    public Packet toDomain() {
        return new Packet(this.Id, this.description, this.deliveryStatus);
    }
}
