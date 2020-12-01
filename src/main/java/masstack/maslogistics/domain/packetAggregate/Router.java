package masstack.maslogistics.domain.packetAggregate;

import java.util.UUID;

public class Router extends Product {
    private String brand;

    public Router(UUID id, String title, String weight, String size, String brand) {
        super(id, title, weight, size);

        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    private void setBrand(String brand) {
        this.brand = brand;
    }
}
