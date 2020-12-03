package integrationTests.masstack.maslogistics;

import masstack.maslogistics.webapi.MaslogisticsApiApplication;
import masstack.maslogistics.domain.packetAggregate.PacketDeliveryStatus;
import masstack.maslogistics.domain.packetAggregate.PacketRepository;
import masstack.maslogistics.domain.packetAggregate.Router;
import masstack.maslogistics.domain.packetAggregate.Sim;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import unitTests.masstack.maslogistics.shared.builders.PacketBuilder;

import javax.transaction.Transactional;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@ContextConfiguration(classes = MaslogisticsApiApplication.class)
@SpringBootTest
@Transactional
public class WhenSavingPacketEntity {
    @Autowired
    private PacketRepository repository;

    @Test
    void packetWithoutProductsIsPersisted() {
        var packetId = UUID.fromString("57ef2f82-65db-424e-b297-72c1e2363806");
        var description = "Some description";
        var deliveryStatus = PacketDeliveryStatus.PENDING;

        this.repository.saveOrUpdate(new PacketBuilder()
                .withId(packetId)
                .withDeliveryStatus(deliveryStatus)
                .withDescription(description)
                .build());

        var packet = repository.findById(packetId).get();
        assertThat(packet.getId(), is(equalTo(packetId)));
        assertThat(packet.getDescription(), is(equalTo(description)));
        assertThat(packet.getDeliveryStatus(), is(equalTo(deliveryStatus)));
        assertThat(packet.getProducts().size(), is(equalTo(0)));
    }

    @Test
    void packetIsPersisted() {
        var packetId = UUID.fromString("57ef2f82-65db-424e-b297-72c1e2363806");
        var description = "Some description";
        var deliveryStatus = PacketDeliveryStatus.PENDING;

        var simProductId = UUID.fromString("47ef2f82-65db-424e-b297-72c1e2363806");
        var simProductTitle = "Title";
        var simProductWeight = "Weight";
        var simProductSize = "Size";
        var simProductImsi = "IMSI";

        var routerProductId = UUID.fromString("37ef2f82-65db-424e-b297-72c1e2363806");
        var routerProductTitle = "OtherTitle";
        var routerProductWeight = "OtherWeight";
        var routerProductSize = "OtherSize";
        var routerProductBrand = "Samsung";

        this.repository.saveOrUpdate(new PacketBuilder()
                .withId(packetId)
                .withDeliveryStatus(deliveryStatus)
                .withDescription(description)
                .withProducts(
                        new Sim(simProductId, simProductTitle, simProductWeight, simProductSize, simProductImsi),
                        new Router(routerProductId, routerProductTitle, routerProductWeight, routerProductSize, routerProductBrand))
                .build());

        var packet = repository.findById(packetId).get();
        assertThat(packet.getId(), is(equalTo(packetId)));
        assertThat(packet.getDescription(), is(equalTo(description)));
        assertThat(packet.getDeliveryStatus(), is(equalTo(deliveryStatus)));

        assertThat(packet.getProducts().size(), is(equalTo(2)));

        var simProduct = packet.getProducts().stream()
                .filter(x -> x.getClass() == Sim.class).findFirst().get();
        assertThat(simProduct.getId(), is(equalTo(simProductId)));
        assertThat(simProduct.getTitle(), is(equalTo(simProductTitle)));
        assertThat(simProduct.getWeight(), is(equalTo(simProductWeight)));
        assertThat(simProduct.getSize(), is(equalTo(simProductSize)));
        assertThat(((Sim) simProduct).getImsi(), is(equalTo(simProductImsi)));

        var routerProduct = packet.getProducts().stream()
                .filter(x -> x.getClass() == Router.class).findFirst().get();
        assertThat(routerProduct.getId(), is(equalTo(routerProductId)));
        assertThat(routerProduct.getTitle(), is(equalTo(routerProductTitle)));
        assertThat(routerProduct.getWeight(), is(equalTo(routerProductWeight)));
        assertThat(routerProduct.getSize(), is(equalTo(routerProductSize)));
        assertThat(((Router) routerProduct).getBrand(), is(equalTo(routerProductBrand)));
    }
}
