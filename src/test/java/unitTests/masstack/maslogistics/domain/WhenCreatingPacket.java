package unitTests.masstack.maslogistics.domain;

import masstack.maslogistics.domain.packetAggregate.PacketDeliveryStatus;
import masstack.maslogistics.domain.services.PacketManagementServiceImplementation;
import org.junit.jupiter.api.Test;
import unitTests.masstack.maslogistics.shared.builders.PacketBuilder;

import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class WhenCreatingPacket extends PacketDomainTestBase {
    private PacketManagementServiceImplementation service;

    @Override
    protected void additionalSetup() {
        super.additionalSetup();
        service = new PacketManagementServiceImplementation(repository);
    }

    @Test
    void packetIsAdded() {
        var id = UUID.fromString("57ef2f82-65db-424e-b297-72c1e2363806");
        var description = "SIM";
        var deliveryStatus = PacketDeliveryStatus.DELIVERED;
        assumePacketInRepository(
                new PacketBuilder()
                        .withId(id)
                        .withDescription(description)
                        .withDeliveryStatus(deliveryStatus)
                        .build());

        service.createPacket(description);

        var packet = repository.findById(id).get();
        assertThat(packet.getId(), is(equalTo(id)));
        assertThat(packet.getDescription(), is(equalTo(description)));
        assertThat(packet.getDeliveryStatus(), is(equalTo(deliveryStatus)));
        assertThat(packet.getProducts().size(), is(equalTo(0)));
    }

    @Test
    void packetCreatedDomainEventIsRaised() {

    }
}
