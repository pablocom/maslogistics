package masstack.maslogistics.infrastructure.persistence;

import masstack.maslogistics.domain.packageAggregate.Packet;
import masstack.maslogistics.domain.packageAggregate.PacketRepository;
import masstack.maslogistics.infrastructure.persistence.entities.PacketEntity;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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

    @Override
    public List<Packet> all() {
        CriteriaBuilder cb = sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<PacketEntity> cq = cb.createQuery(PacketEntity.class);
        Root<PacketEntity> rootEntry = cq.from(PacketEntity.class);
        CriteriaQuery<PacketEntity> all = cq.select(rootEntry);

        TypedQuery<PacketEntity> allQuery = sessionFactory.getCurrentSession().createQuery(all);
        return allQuery.getResultList().stream().map(PacketEntity::toDomain).collect(Collectors.toList());
    }
}
