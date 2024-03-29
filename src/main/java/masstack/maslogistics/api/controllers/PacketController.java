package masstack.maslogistics.api.controllers;

import masstack.maslogistics.domain.sharedKernel.DomainException;
import masstack.maslogistics.domain.packetAggregate.Packet;
import masstack.maslogistics.domain.services.PacketManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("packet")
public class PacketController {
    private PacketManagementService packetManagementService;

    @Autowired
    public PacketController(PacketManagementService packetManagementService) {
        this.packetManagementService = packetManagementService;
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<PacketDto> getPacket(@PathVariable String id) throws DomainException {
        var packetResponse = PacketDto.fromAggregate(this.packetManagementService.getPacket(id));
        return ResponseEntity.ok(packetResponse);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createPacket(@RequestBody CreatePacketRequest request) {
        this.packetManagementService.createPacket(request.getDescription());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Packet>> getAllPackets() {
        return ResponseEntity.ok(this.packetManagementService.getAllPackets());
    }

    @PostMapping("complete/{id}")
    public ResponseEntity<HttpStatus> markAsCompleted(@PathVariable String id) throws DomainException {
        this.packetManagementService.markAsCompleted(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

