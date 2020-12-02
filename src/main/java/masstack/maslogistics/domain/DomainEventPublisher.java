package masstack.maslogistics.domain;

import masstack.maslogistics.application.PacketCreatedDomainEventHandler;

import java.util.ArrayList;
import java.util.List;

public class DomainEventPublisher {
    @SuppressWarnings("unchecked")
    private static final ThreadLocal<List> subscribers = new ThreadLocal<>();
    private static final ThreadLocal<Boolean> publishing = ThreadLocal.withInitial(() -> false);

    public static DomainEventPublisher instance() {
        return new DomainEventPublisher();
    }

    public DomainEventPublisher() {
        super();
        this.subscribe(new PacketCreatedDomainEventHandler());
    }

    @SuppressWarnings("unchecked")
    public <T extends DomainEvent> void publish(final T domainEvent) {
        if (publishing.get()) {
            return;
        }
        try {
            publishing.set(true);
            List<DomainEventHandler<T>> registeredSubscribers = subscribers.get();

            if (registeredSubscribers != null) {
                Class<?> eventType = domainEvent.getClass();
                for (DomainEventHandler<T> subscriber : registeredSubscribers) {
                    Class<?> subscribedTo = subscriber.subscribedToEventType();
                    if (subscribedTo == eventType ||
                            subscribedTo == DomainEvent.class) {
                        subscriber.handleEvent(domainEvent);
                    }
                }
            }
        }
        finally {
            publishing.set(false);
        }
    }

    public DomainEventPublisher reset() {
        if (!publishing.get()) {
            subscribers.set(null);
        }
        return this;
    }

    @SuppressWarnings("unchecked")
    public <T extends DomainEvent> void subscribe(DomainEventHandler<T> aSubscriber) {
        if (publishing.get()) {
            return;
        }
        var registeredSubscribers = subscribers.get();
        if (registeredSubscribers == null) {
            registeredSubscribers = new ArrayList<>();
            subscribers.set(registeredSubscribers);
        }
        registeredSubscribers.add(aSubscriber);
    }
}
