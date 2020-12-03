package masstack.maslogistics.domain.sharedKernel;

public abstract class DomainEventHandler<T extends DomainEvent> {
    public abstract void handleEvent(T domainEvent);
    public abstract Class<T> subscribedToEventType();
}
