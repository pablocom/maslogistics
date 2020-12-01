package masstack.maslogistics.infrastructure.persistence.entities;

import masstack.maslogistics.domain.packetAggregate.Sim;

import javax.persistence.Entity;
import java.util.UUID;

@Entity(name = "sims")
public class SimEntity extends ProductEntity {
    private String imsi;

    protected SimEntity(UUID id, String title, String weight, String size, String imsi) {
        super(id, title, weight, size);
    }

    public String getImsi() {
        return imsi;
    }

    protected void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public static SimEntity from(Sim sim) {
        return new SimEntity(sim.getId(), sim.getTitle(), sim.getWeight(), sim.getSize(), sim.getImsi());
    }
}
