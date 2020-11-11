package unitTests.masstack.maslogistics.domain;

import masstack.maslogistics.domain.packageAggregate.Packet;
import masstack.maslogistics.domain.packageAggregate.PacketRepository;
import masstack.maslogistics.domain.services.PacketManagementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import unitTests.masstack.maslogistics.common.builders.PacketBuilder;

import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;


public class WhenCreatingPacket {
    private PacketManagementService service;
    private PacketRepository repository;

    @BeforeEach
    void setUp() {
        repository = new FakeInMemoryPacketRepository();
        service = new PacketManagementService(repository);
    }

    @Test
    void packetIsAdded() {
        var id = UUID.fromString("57ef2f82-65db-424e-b297-72c1e2363806");
        var description = "SIM";
        var deliveryStatus = "DELIVERED";
        assumePacketInRepository(
                new PacketBuilder()
                        .withId(id)
                        .withDescription(description)
                        .withDeliveryStatus(deliveryStatus)
                        .build());

        service.createPacket(description, deliveryStatus);

        var packetOptional = repository.findById(id);
        assertThat(packetOptional.isPresent(), is(equalTo(true)));
        var packet = packetOptional.get();
        assertThat(packet.getId(), is(equalTo(id)));
        assertThat(packet.getDescription(), is(equalTo(description)));
        assertThat(packet.getDeliveryStatus(), is(equalTo(deliveryStatus)));
    }

    private void assumePacketInRepository(Packet packet) {
        repository.save(packet);
    }
}
