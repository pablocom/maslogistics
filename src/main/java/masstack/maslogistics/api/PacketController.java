package masstack.maslogistics.api;

import masstack.maslogistics.domain.Packet;
import masstack.maslogistics.domain.PacketRepository;
import masstack.maslogistics.infrastructure.persistence.entities.PacketEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("packet")
public class PacketController {
    private PacketRepository packetRepository;

    @Autowired
    public PacketController(PacketRepository packetRepository) {
        this.packetRepository = packetRepository;
    }

    @GetMapping
    public Packet GetPacket() {
        return new Packet();
    }

    @PostMapping
    public void CreatePacket() {
        this.packetRepository.save(new Packet());
    }
}
