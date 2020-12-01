package integrationTests.masstack.maslogistics;

import masstack.maslogistics.api.MaslogisticsApiApplication;
import masstack.maslogistics.domain.packetAggregate.PacketDeliveryStatus;
import masstack.maslogistics.domain.packetAggregate.PacketRepository;
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
public class WhenSavingPacketEntity {
    @Autowired
    private PacketRepository repository;

    @Test
    void packetIsPersisted() {
        var id = UUID.fromString("57ef2f82-65db-424e-b297-72c1e2363806");
        var productId = UUID.fromString("47ef2f82-65db-424e-b297-72c1e2363806");
        var description = "Some description";
        var deliveryStatus = PacketDeliveryStatus.PENDING;

        this.repository.saveOrUpdate(new PacketBuilder()
                .withId(id)
                .withDeliveryStatus(deliveryStatus)
                .withDescription(description)
                .withProducts(new Sim(productId, "Title", "Weight", "Size", "IMSI"))
                .build());

        var packet = repository.findById(id).get();
        assertThat(packet.getId(), is(equalTo(id)));
        assertThat(packet.getDescription(), is(equalTo(description)));
        assertThat(packet.getDeliveryStatus(), is(equalTo(deliveryStatus)));
        assertThat(packet.getProducts().size(), is(equalTo(1)));
        assertThat(packet.getProducts().stream().findFirst().get().getId(), is(equalTo(productId)));
    }
}
