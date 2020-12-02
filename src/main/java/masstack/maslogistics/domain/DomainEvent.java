package masstack.maslogistics.domain;

import java.util.UUID;

public abstract class DomainEvent {
    private String eventId;
    private String aggregateId;

    public DomainEvent(String aggregateId) {
        this.aggregateId = aggregateId;
        this.eventId     = UUID.randomUUID().toString();
    }

    public String getEventId() {
        return eventId;
    }

    public String getAggregateId() {
        return aggregateId;
    }
}
