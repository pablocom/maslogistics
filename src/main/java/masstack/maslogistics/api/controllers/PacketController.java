package masstack.maslogistics.api.controllers;

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
    public ResponseEntity<PacketDto> getPacket(@PathVariable String id) throws Exception {
        var packetResponse = PacketDto.fromAggregate(this.packetManagementService.getPacket(id));
        return ResponseEntity.ok(packetResponse);
    }

    @PostMapping
    public ResponseEntity<String> createPacket(@RequestBody CreatePacketRequest request) {
        this.packetManagementService.createPacket(request.getDescription(), request.getDeliveryStatus());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public void getAllPackets() {
        this.packetManagementService.getAllPackets();
    }
}

