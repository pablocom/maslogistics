package masstack.maslogistics.infrastructure.persistence;

import masstack.maslogistics.domain.packageAggregate.Packet;
import masstack.maslogistics.domain.packageAggregate.PacketRepository;
import masstack.maslogistics.infrastructure.persistence.entities.PacketEntity;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class HibernatePacketRepository implements PacketRepository {
    private SessionFactory sessionFactory;

    @Autowired
    public HibernatePacketRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Packet packet) {
        var session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        var packetEntity = new PacketEntity(packet);
        session.saveOrUpdate(packetEntity);

        tx.commit();
        session.close();
    }

    @Override
    public Optional<Packet> findById(UUID id) {
        var session = sessionFactory.openSession();
        var packetEntity = Optional.ofNullable(session.byId(PacketEntity.class).load(id));
        session.close();

        if (packetEntity.isPresent())
            return Optional.of(packetEntity.get().toDomain());

        return Optional.empty();
    }
}
