package masstack.maslogistics.infrastructure.persistence.entities;

import masstack.maslogistics.domain.packetAggregate.Router;

import javax.persistence.Entity;
import java.util.UUID;

@Entity(name="routers")
public class RouterEntity extends ProductEntity {
    private String brand;

    protected RouterEntity(UUID id, String title, String weight, String size, String brand) {
        super(id, title, weight, size);
        
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    protected void setBrand(String brand) {
        this.brand = brand;
    }
    
    public static RouterEntity from(Router router) {
        return new RouterEntity(router.getId(), router.getTitle(), router.getWeight(), router.getSize(), router.getBrand());
    }
}
