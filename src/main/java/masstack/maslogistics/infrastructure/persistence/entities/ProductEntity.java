package masstack.maslogistics.infrastructure.persistence.entities;

import masstack.maslogistics.domain.packetAggregate.Packet;
import masstack.maslogistics.domain.packetAggregate.Product;
import masstack.maslogistics.domain.packetAggregate.Router;
import masstack.maslogistics.domain.packetAggregate.Sim;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class ProductEntity {
    @Id
    protected UUID id;
    protected String title;
    protected String weight;
    protected String size;

    protected ProductEntity(UUID id, String title, String weight, String size) {
        this.id = id;
        this.title = title;
        this.weight = weight;
        this.size = size;
    }

    protected ProductEntity() {

    }

    public static List<ProductEntity> from(List<Product> products) {
        var productEntities = new ArrayList<ProductEntity>();

        for (var product : products) {
            if (product instanceof Sim)
                productEntities.add(SimEntity.from((Sim) product));

            if (product instanceof Router)
                productEntities.add(RouterEntity.from((Router) product));
        }

        return productEntities;
    }
}
