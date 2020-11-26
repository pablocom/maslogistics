package masstack.maslogistics.domain.services;

import masstack.maslogistics.domain.DomainException;
import masstack.maslogistics.domain.packageAggregate.Packet;
import masstack.maslogistics.domain.packageAggregate.PacketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PacketManagementServiceImplementation implements PacketManagementService {
    private final PacketRepository packetRepository;

    @Autowired
    public PacketManagementServiceImplementation(PacketRepository packetRepository) {
        this.packetRepository = packetRepository;
    }

    public void createPacket(String description) {
        var packet = new Packet(UUID.randomUUID(), description);
        packetRepository.saveOrUpdate(packet);
    }

    public Packet getPacket(String id) throws DomainException {
        var packet = packetRepository.findById(UUID.fromString(id));

        if (packet.isEmpty())
            throw new DomainException("Packet with id " + id + " not found");

        return packet.get();
    }

    public List<Packet> getAllPackets() {
        return packetRepository.all();
    }


    public void markAsCompleted(String id) throws DomainException {
        var packetOptional = this.packetRepository.findById(UUID.fromString(id));

        if (packetOptional.isEmpty())
            throw new DomainException("Packet with id " + id + " not found");

        var packet = packetOptional.get();
        packet.markAsCompleted();
        packetRepository.saveOrUpdate(packet);
    }
}
