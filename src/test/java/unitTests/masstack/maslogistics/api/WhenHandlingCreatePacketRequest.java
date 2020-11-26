package unitTests.masstack.maslogistics.api;

import masstack.maslogistics.api.controllers.CreatePacketRequest;
import masstack.maslogistics.api.controllers.PacketController;
import masstack.maslogistics.domain.services.PacketManagementService;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class WhenHandlingCreatePacketRequest extends ControllerTestBase<PacketController> {
    private PacketManagementService service;

    @Override
    protected void additionalSetup() throws Exception {
        super.additionalSetup();
        service = mock(PacketManagementService.class);
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