package unitTests.masstack.maslogistics.domain;

import masstack.maslogistics.domain.packetAggregate.Packet;
import masstack.maslogistics.domain.packetAggregate.PacketRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class FakeInMemoryPacketRepository implements PacketRepository {
    private List<Packet> packetList = new ArrayList<>();

    @Override
    public void saveOrUpdate(Packet packet) {
        var packetId = packet.getId();

        if (packetList.stream().anyMatch(x -> x.getId().equals(packetId))) {
            var packetToRemove = packetList.stream().filter(x -> x.getId().equals(packetId)).findAny().get();
            packetList.remove(packetToRemove);
        }

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
