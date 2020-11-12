package masstack.maslogistics.domain.services;

import masstack.maslogistics.domain.DomainException;
import masstack.maslogistics.domain.packageAggregate.Packet;
import masstack.maslogistics.domain.packageAggregate.PacketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PacketManagementService {
    private final PacketRepository packetRepository;

    @Autowired
    public PacketManagementService(PacketRepository packetRepository) {
        this.packetRepository = packetRepository;
    }

    public void createPacket(String description, String deliveryStatus) {
        var packet = new Packet(UUID.randomUUID(), description, deliveryStatus);
        packetRepository.save(packet);
    }

    public Packet getPacket(String id) throws DomainException {
        var packet = packetRepository.findById(UUID.fromString(id));

        if (packet.isEmpty())
            throw new DomainException("Packet with id " + id + " not found");

        return packet.get();
    }
}
