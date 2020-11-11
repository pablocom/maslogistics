package unitTests.masstack.maslogistics.api;

import masstack.maslogistics.api.CreatePacketRequest;
import masstack.maslogistics.api.PacketController;
import masstack.maslogistics.domain.packageAggregate.Packet;
import masstack.maslogistics.domain.services.PacketManagementService;
import org.junit.jupiter.api.Test;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class WhenHandlingCreatePacketRequest extends ControllerTestBase<PacketController> {
    private PacketManagementService service;

    @Override
    protected void additionalSetup() {
        super.additionalSetup();
        service = mock(PacketManagementService.class);
        given(service.getPacket("")).willReturn(new Packet(""));
    }

    @Test
    public void serviceIsCalled() {
        var expectedDescription = "SIM plus router description";
        var request = new CreatePacketRequest();
        request.setDescription(expectedDescription);

        controller.createPacket(request);

        verify(service).createPacket(expectedDescription);
    }

    @Override
    protected PacketController setupController() {
        return new PacketController(service);
    }
}