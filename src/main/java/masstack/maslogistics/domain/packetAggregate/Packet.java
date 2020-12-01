package masstack.maslogistics.domain.packetAggregate;

import java.util.*;

public class Packet {
    private UUID Id;
    private String description;
    private PacketDeliveryStatus deliveryStatus;
    private List<Product> products;

    public Packet(UUID id, String description) {
        this.Id = id;
        this.description = description;
        this.deliveryStatus = PacketDeliveryStatus.PENDING;
        this.products = new ArrayList<>();
    }

    public Packet(UUID id, String description, PacketDeliveryStatus deliveryStatus, List<Product> products) {
        this.Id = id;
        this.description = description;
        this.deliveryStatus = deliveryStatus;
        this.products = products;
    }

    public void markAsCompleted() {
        deliveryStatus = PacketDeliveryStatus.DELIVERED;
    }

    public UUID getId() {
        return this.Id;
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

    public void addProducts(Product... products) {
        if (products != null)
            this.products.addAll(Arrays.asList(products));
    }
}
