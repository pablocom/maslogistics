package unitTests.masstack.maslogistics.domain;

import masstack.maslogistics.domain.DomainException;
import masstack.maslogistics.domain.packetAggregate.PacketDeliveryStatus;
import masstack.maslogistics.domain.services.PacketManagementServiceImplementation;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import unitTests.masstack.maslogistics.shared.builders.PacketBuilder;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WhenGettingSinglePacket extends PacketDomainTestBase {
    private PacketManagementServiceImplementation service;

    @Override
    protected void additionalSetup() {
        super.additionalSetup();
        service = new PacketManagementServiceImplementation(repository);
    }

    @Test
    public void packetIsRetrieved() throws Exception {
        var id = UUID.fromString("57ef2f82-65db-424e-b297-72c1e2363806");
        var description = "some description";
        var deliveryStatus = PacketDeliveryStatus.PENDING;
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

    @Test
    void raisesDomainExceptionIfPacketIsNotFound() {
        var nonExistingPacketId = "57ef2f82-65db-424e-b297-72c1e2363806";

        Executable testDelegate = () -> service.getPacket(nonExistingPacketId);

        var exception = assertThrows(DomainException.class, testDelegate);
        var expectedMessage = "Packet with id " + nonExistingPacketId + " not found";
        assertThat(exception.getMessage(), is(equalTo(expectedMessage)));
    }
}
