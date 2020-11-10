package masstack.maslogistics.api;

import masstack.maslogistics.domain.packageAggregate.Packet;
import masstack.maslogistics.domain.services.PacketManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("packet")
public class PacketController {
    private PacketManagementService packetManagementService;

    @Autowired
    public PacketController(PacketManagementService packetManagementService) {
        this.packetManagementService = packetManagementService;
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<PacketResponse> GetPacket(@PathVariable String id) {
        var packetResponse = PacketResponse.fromAggregate(this.packetManagementService.getPacket(id).get());
        return ResponseEntity.ok(packetResponse);
    }

    @PostMapping
    public ResponseEntity<String> CreatePacket(@RequestBody CreatePacketRequest request) {
        this.packetManagementService.createPacket(request.getDescription());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}

final class CreatePacketRequest {
    private String description;

    public void setDescription(String description) {
        this.description = description;
    }

    String getDescription() {
        return description;
    }
}
