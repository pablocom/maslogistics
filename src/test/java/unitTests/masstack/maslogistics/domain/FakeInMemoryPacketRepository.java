package unitTests.masstack.maslogistics.domain;

import masstack.maslogistics.domain.packageAggregate.Packet;
import masstack.maslogistics.domain.packageAggregate.PacketRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class FakeInMemoryPacketRepository implements PacketRepository {
    private List<Packet> packetList = new ArrayList<>();

    @Override
    public void save(Packet packet) {
        packetList.add(packet);
    }

    @Override
    public Optional<Packet> findById(UUID id) {
        return packetList.stream().filter(x -> x.getId().equals(id)).findFirst();
    }

    @Override
    public List<Packet> all() {
        return packetList;
    }
}
