package masstack.maslogistics.application;

import masstack.maslogistics.domain.sharedKernel.DomainEventHandler;
import masstack.maslogistics.domain.events.PacketCreated;

public class PacketCreatedDomainEventHandler implements DomainEventHandler<PacketCreated> {
    @Override
    public void handleEvent(PacketCreated domainEvent) {
        System.out.println("Received PacketCreated domain event");
        System.out.println("AggregateId: " + domainEvent.getAggregateId());
        System.out.println("EventId: " + domainEvent.getEventId());
        System.out.println();

        // here we could publish the event to a event bus in the
        // case the event should be listened by another microservice
    }

    @Override
    public Class<PacketCreated> subscribedToEventType() {
        return PacketCreated.class;
    }
}
