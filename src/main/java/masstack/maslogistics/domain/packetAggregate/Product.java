package masstack.maslogistics.domain.packetAggregate;

import java.util.UUID;

public abstract class Product {
    protected UUID id;
    protected String title;
    protected String weight;
    protected String size;

    protected Product(UUID id, String title, String weight, String size) {
        this.id = id;
        this.title = title;
        this.weight = weight;
        this.size = size;
    }

    public String getTitle() {
        return title;
    }

    protected void setTitle(String title) {
        this.title = title;
    }

    public String getWeight() {
        return weight;
    }

    protected void setWeight(String weight) {
        this.weight = weight;
    }

    public String getSize() {
        return size;
    }

    protected void setSize(String size) {
        this.size = size;
    }

    public UUID getId() {
        return this.id;
    }
}
