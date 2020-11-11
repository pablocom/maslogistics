package unitTests.masstack.maslogistics.domain;

import masstack.maslogistics.domain.packageAggregate.Packet;
import masstack.maslogistics.domain.packageAggregate.PacketRepository;
import org.junit.jupiter.api.BeforeEach;

public class PacketDomainTestBase {
    protected PacketRepository repository;

    @BeforeEach
    protected void setup(){
        repository = new FakeInMemoryPacketRepository();
        additionalSetup();
    }

    protected void additionalSetup() {}

    protected void assumePacketInRepository(Packet packet) {
        repository.save(packet);
    }
}
