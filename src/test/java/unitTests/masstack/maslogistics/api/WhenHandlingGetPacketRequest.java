package unitTests.masstack.maslogistics.api;

import masstack.maslogistics.api.PacketController;
import masstack.maslogistics.domain.services.PacketManagementService;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class WhenHandlingGetPacketRequest extends ControllerTestBase<PacketController> {
    private PacketManagementService service;

    @Override
    protected void additionalSetup() {
        super.additionalSetup();
        service = mock(PacketManagementService.class);
    }

    @Test
    public void serviceIsCalled() {
        var expectedId = "2ebf6d4b-2c96-4e56-9a43-ab6c7667e46b";
        controller.getPacket(expectedId);

        verify(service).getPacket("expectedId");
    }

    @Override
    protected PacketController setupController() {
        return new PacketController(service);
    }
}