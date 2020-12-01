package masstack.maslogistics.domain.packetAggregate;

import java.util.UUID;

public class Sim extends Product {
    private String imsi;

    public Sim(UUID id, String title, String weight, String size, String imsi) {
        super(id, title, weight, size);

        this.imsi = imsi;
    }

    public String getImsi() {
        return imsi;
    }

    private void setImsi(String imsi) {
        this.imsi = imsi;
    }
}
