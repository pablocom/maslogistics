package unitTests.masstack.maslogistics.domain;

import masstack.maslogistics.domain.services.PacketManagementService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import unitTests.masstack.maslogistics.common.builders.PacketBuilder;

import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class WhenGettingSinglePacket extends PacketDomainTestBase {
    private PacketManagementService service;

    @Override
    protected void additionalSetup() {
        super.additionalSetup();
        service = new PacketManagementService(repository);
    }

    @Test
    public void packetIsRetrieved() {
        var id = UUID.fromString("57ef2f82-65db-424e-b297-72c1e2363806");
        var description = "some description";
        var deliveryStatus = "some delivery status";
        assumePacketInRepository(new PacketBuilder()
                .withId(id)
                .withDescription(description)
                .withDeliveryStatus(deliveryStatus)
                .build());

        var packet = service.getPacket(id.toString());

        assertThat(packet.getId(), is(Matchers.equalTo(id)));
        assertThat(packet.getDescription(), is(Matchers.equalTo(description)));
        assertThat(packet.getDeliveryStatus(), is(Matchers.equalTo(deliveryStatus)));
    }
}
