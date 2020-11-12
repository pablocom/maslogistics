package masstack.maslogistics.infrastructure.persistence;

import masstack.maslogistics.domain.packageAggregate.Packet;
import masstack.maslogistics.domain.packageAggregate.PacketRepository;
import masstack.maslogistics.infrastructure.persistence.entities.PacketEntity;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class HibernatePacketRepository implements PacketRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public HibernatePacketRepository(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Packet packet) {
        sessionFactory.getCurrentSession().saveOrUpdate(new PacketEntity(packet));
        sessionFactory.getCurrentSession().flush();
        sessionFactory.getCurrentSession().clear();
    }

    @Override
    public Optional<Packet> findById(UUID id) {
        var packetEntity = Optional
                .ofNullable(sessionFactory.getCurrentSession().byId(PacketEntity.class).load(id));
        return packetEntity.map(PacketEntity::toDomain);
    }
}
