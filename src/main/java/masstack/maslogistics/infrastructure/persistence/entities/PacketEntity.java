package masstack.maslogistics.infrastructure.persistence.entities;

import masstack.maslogistics.domain.packageAggregate.Packet;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "packets")
public class PacketEntity implements Serializable {
    @Id
    private UUID Id;
    private String description;
    private String deliveryStatus = "defaultValue";

    public PacketEntity(Packet packet) {
        this.Id = UUID.randomUUID();
        this.description = packet.getDescription();
        this.deliveryStatus = packet.getDeliveryStatus();
    }

    // Hibernate required default constructor
    public PacketEntity() {}

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public UUID getId() {
        return Id;
    }

    public Packet toDomain() {
        return new Packet(this.Id, this.description, this.deliveryStatus);
    }
}
