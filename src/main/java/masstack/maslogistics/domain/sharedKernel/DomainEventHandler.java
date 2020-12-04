package masstack.maslogistics.domain.sharedKernel;

public interface DomainEventHandler<T extends DomainEvent> {
    void handleEvent(T domainEvent);
    Class<T> subscribedToEventType();
}
