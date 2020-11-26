package masstack.maslogistics.domain.services;

import masstack.maslogistics.domain.DomainException;
import masstack.maslogistics.domain.packageAggregate.Packet;

import java.util.List;

public interface PacketManagementService {
    void createPacket(String description, String deliveryStatus);
    Packet getPacket(String id) throws DomainException;
    List<Packet> getAllPackets();
    void markAsCompleted(String id);
}
