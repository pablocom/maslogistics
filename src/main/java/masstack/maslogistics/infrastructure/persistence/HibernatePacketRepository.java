package masstack.maslogistics.infrastructure.persistence;

import masstack.maslogistics.domain.Packet;
import masstack.maslogistics.domain.PacketRepository;
import masstack.maslogistics.infrastructure.persistence.entities.PacketEntity;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        for (int i = 0; i < 100000; i++) {
            var customer = new PacketEntity();
            session.save(customer);
            if (i % 20 == 0) { // 20, same as the JDBC batch size
                // flush a batch of inserts and release memory:
                session.flush();
                session.clear();
            }
        }

        tx.commit();
        session.close();
    }
}
