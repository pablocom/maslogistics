package unitTests.masstack.maslogistics.domain;

import masstack.maslogistics.application.PacketCreatedDomainEventHandler;
import masstack.maslogistics.domain.events.PacketCreated;
import masstack.maslogistics.domain.packetAggregate.PacketDeliveryStatus;
import masstack.maslogistics.domain.services.PacketManagementServiceImplementation;
import masstack.maslogistics.domain.sharedKernel.DomainEvents;
import org.junit.jupiter.api.Test;
import unitTests.masstack.maslogistics.shared.builders.PacketBuilder;

import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class WhenCreatingPacket extends PacketDomainTestBase {
    private PacketManagementServiceImplementation service;

    @Override
    protected void additionalSetup() {
        super.additionalSetup();
        service = new PacketManagementServiceImplementation(repository);
    }

    @Test
    void packetIsAdded() {
        var description = "SIM";

        service.createPacket(description);

        var packet = repository.all().stream().findFirst().get();
        assertThat(packet.getDescription(), is(equalTo(description)));
        assertThat(packet.getDeliveryStatus(), is(equalTo(PacketDeliveryStatus.PENDING)));
        assertThat(packet.getProducts().size(), is(equalTo(0)));
    }

    @Test
    void packetCreatedDomainEventIsRaised() {
        var description = "SIM";

        service.createPacket(description);


    }
}
