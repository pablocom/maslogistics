package masstack.maslogistics.domain.services;

import masstack.maslogistics.domain.DomainException;
import masstack.maslogistics.domain.packageAggregate.Packet;
import masstack.maslogistics.domain.packageAggregate.PacketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class PacketManagementService {
    private final PacketRepository packetRepository;

    @Autowired
    public PacketManagementService(PacketRepository packetRepository) {
        this.packetRepository = packetRepository;
    }

    public void createPacket(String description) {
        var packet = new Packet(description);
        packetRepository.save(packet);
    }

    public Packet getPacket(String id) {
        var packet = packetRepository.findById(UUID.fromString(id));

        return packet.get();
    }
}
