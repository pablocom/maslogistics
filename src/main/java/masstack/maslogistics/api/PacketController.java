package masstack.maslogistics.api;

import masstack.maslogistics.domain.Packet;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("packet")
public class PacketController {
    @GetMapping
    public Packet GetPacket() {
        return new Packet();
    }
}
