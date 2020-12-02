package masstack.maslogistics.application;

import masstack.maslogistics.domain.DomainEventHandler;
import masstack.maslogistics.domain.events.PacketCreated;

public class PacketCreatedDomainEventHandler extends DomainEventHandler<PacketCreated> {
    @Override
    public void handleEvent(PacketCreated domainEvent) {
        System.out.println("Received PacketCreated domain event");
        System.out.println("AggregateId: " + domainEvent.getAggregateId());
        System.out.println("EventId: " + domainEvent.getEventId());
        System.out.println();
    }

    @Override
    public Class<PacketCreated> subscribedToEventType() {
        return PacketCreated.class;
    }
}
