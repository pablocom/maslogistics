package unitTests.masstack.maslogistics.domain;

import masstack.maslogistics.domain.packetAggregate.Packet;
import masstack.maslogistics.domain.packetAggregate.PacketDeliveryStatus;
import masstack.maslogistics.domain.services.PacketManagementServiceImplementation;
import org.junit.jupiter.api.Test;
import unitTests.masstack.maslogistics.shared.builders.PacketBuilder;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class WhenGettingAllPackets extends PacketDomainTestBase {
    private PacketManagementServiceImplementation service;

    @Override
    protected void additionalSetup() {
        super.additionalSetup();
        service = new PacketManagementServiceImplementation(repository);
    }

    @Test
    void returnsEmptyListIfThereIsNoPackets() {
        List<Packet> packets = service.getAllPackets();

        assertThat(packets.size(), is(equalTo(0)));
    }

    @Test
    void returnsListWithOnePacket() {
        var id = UUID.fromString("57ef2f82-65db-424e-b297-72c1e2363806");
        var description = "SIM";
        var deliveryStatus = PacketDeliveryStatus.PENDING;
        assumePacketInRepository(new PacketBuilder()
                .withId(id)
                .withDescription(description)
                .withDeliveryStatus(deliveryStatus)
                .build());

        List<Packet> packets = service.getAllPackets();

        assertThat(packets.size(), is(equalTo(1)));
        var packet = packets.stream().findFirst().get();
        assertThat(packet.getId(), is(equalTo(id)));
        assertThat(packet.getDescription(), is(equalTo(description)));
        assertThat(packet.getDeliveryStatus(), is(equalTo(deliveryStatus)));
    }

    @Test
    void returnsMultiplePackets() {
        var id = "57ef2f82-65db-424e-b297-72c1e2363806";
        var description = "SIM";
        var deliveryStatus = PacketDeliveryStatus.DELIVERED;
        var anotherId = "57ef2f82-65db-424e-b297-72c1e2361234";
        var anotherDescription = "Phone + SIM";
        var anotherDeliveryStatus = PacketDeliveryStatus.DELIVERED;
        assumePacketInRepository(new PacketBuilder()
                .withId(id)
                .withDescription(description)
                .withDeliveryStatus(deliveryStatus)
                .build());
        assumePacketInRepository(new PacketBuilder()
                .withId(anotherId)
                .withDescription(anotherDescription)
                .withDeliveryStatus(anotherDeliveryStatus)
                .build());

        List<Packet> packets = service.getAllPackets();

        assertThat(packets.size(), is(equalTo(2)));
    }
}
