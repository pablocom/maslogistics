package masstack.maslogistics.infrastructure.persistence.entities;

import masstack.maslogistics.domain.packetAggregate.Packet;
import masstack.maslogistics.domain.packetAggregate.PacketDeliveryStatus;
import masstack.maslogistics.domain.packetAggregate.Product;
import masstack.maslogistics.domain.packetAggregate.Sim;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "packets")
public class PacketEntity implements Serializable {
    @Id
    private UUID id;
    private String description;
    private PacketDeliveryStatus deliveryStatus;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ProductEntity> products = new ArrayList<>();

    public PacketEntity(Packet packet) {
        this.id = packet.getId();
        this.description = packet.getDescription();
        this.deliveryStatus = packet.getDeliveryStatus();
        this.products = ProductEntity.from(packet.getProducts());
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
        return id;
    }

    public List<ProductEntity> getProducts() {
        return this.products;
    }

    public Packet toDomain() {

        var products = new ArrayList<Product>();
        for (var product : this.products) {
            if (product instanceof SimEntity)
                products.add(new Sim(product.id, product.title, product.weight, product.size, ((SimEntity)product).getImsi()));
        }
        return new Packet(this.id, this.description, this.deliveryStatus, products);
    }
}
