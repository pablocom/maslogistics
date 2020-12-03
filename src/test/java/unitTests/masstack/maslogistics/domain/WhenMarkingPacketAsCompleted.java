package unitTests.masstack.maslogistics.domain;

import masstack.maslogistics.domain.sharedKernel.DomainException;
import masstack.maslogistics.domain.packetAggregate.PacketDeliveryStatus;
import masstack.maslogistics.domain.services.PacketManagementService;
import masstack.maslogistics.domain.services.PacketManagementServiceImplementation;
import org.junit.jupiter.api.Test;
import unitTests.masstack.maslogistics.shared.builders.PacketBuilder;

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
                .withDeliveryStatus(PacketDeliveryStatus.PENDING)
                .build());

        service.markAsCompleted(id.toString());

        var deliveredStatus = PacketDeliveryStatus.DELIVERED;
        var packet = repository.all().stream().findFirst().get();
        assertThat(packet.getId(), is(equalTo(id)));
        assertThat(packet.getDeliveryStatus(), is(equalTo(deliveredStatus)));
    }
}
