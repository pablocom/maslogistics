package unitTests.masstack.maslogistics.domain;

import masstack.maslogistics.domain.DomainException;
import masstack.maslogistics.domain.services.PacketManagementService;
import masstack.maslogistics.domain.services.PacketManagementServiceImplementation;
import org.junit.jupiter.api.Test;
import unitTests.masstack.maslogistics.common.builders.PacketBuilder;

import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class WhenMarkingPacketAsCompleted extends PacketDomainTestBase {
    private PacketManagementService service;

    @Override
    protected void additionalSetup() {
        super.additionalSetup();
        service = new PacketManagementServiceImplementation(repository);
    }

    @Test
    void packetIsMarkedAsDelivered() throws DomainException {
        var id = UUID.fromString("57ef2f82-65db-424e-b297-72c1e2363806");
        assumePacketInRepository(new PacketBuilder()
                .withId(id)
                .withDeliveryStatus("PENDING")
                .build());

        service.markAsCompleted(id.toString());

        var deliveredStatus = "DELIVERED";
        var packet = repository.all().stream().findFirst().get();
        assertThat(packet.getId(), is(equalTo(id)));
        assertThat(packet.getDeliveryStatus(), is(equalTo(deliveredStatus)));
    }
}
