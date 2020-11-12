package masstack.maslogistics.domain.packageAggregate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PacketRepository {
    void save(Packet packet);
    Optional<Packet> findById(UUID id);
    List<Packet> all();
}
