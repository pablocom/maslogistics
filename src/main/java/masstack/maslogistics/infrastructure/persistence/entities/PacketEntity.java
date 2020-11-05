package masstack.maslogistics.infrastructure.persistence.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "packets")
public class PacketEntity implements Serializable {

    @Id
    private UUID Id = UUID.randomUUID();

    private String deliveryStatus = "defaultValue";

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public UUID getId() {
        return Id;
    }
}
