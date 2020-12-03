package masstack.maslogistics.domain.sharedKernel;

import masstack.maslogistics.application.PacketCreatedDomainEventHandler;

import java.util.ArrayList;
import java.util.List;

public class DomainEvents {
    @SuppressWarnings("unchecked")
    private static final ThreadLocal<List> subscribers = new ThreadLocal<>();
    private static final ThreadLocal<Boolean> publishing = ThreadLocal.withInitial(() -> false);

    public static DomainEvents instance() {
        return new DomainEvents();
    }

    public DomainEvents() {
        super();
        // TODO: get all the domain event handlers resolved by currrent app context container with dependency
        this.subscribe(new PacketCreatedDomainEventHandler());
    }

    @SuppressWarnings("unchecked")
    private <T extends DomainEvent> void publish(final T domainEvent) {
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

    public static <T extends DomainEvent> void raise(final T domainEvent) {
        instance().publish(domainEvent);
    }

    public DomainEvents reset() {
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
