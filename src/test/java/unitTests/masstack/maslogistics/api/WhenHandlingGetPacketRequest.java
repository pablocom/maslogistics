package unitTests.masstack.maslogistics.api;

import masstack.maslogistics.api.controllers.PacketController;
import masstack.maslogistics.domain.services.PacketManagementService;
import org.junit.jupiter.api.Test;
import unitTests.masstack.maslogistics.shared.builders.PacketBuilder;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class WhenHandlingGetPacketRequest extends ControllerTestBase<PacketController> {
    private PacketManagementService service;

    @Override
    protected void additionalSetup() throws Exception {
        super.additionalSetup();
        service = mock(PacketManagementService.class);

        given(service.getPacket(anyString()))
                .willReturn(PacketBuilder.validPacket().build());
    }

    @Test
    void serviceIsCalled() throws Exception {
        var expectedId = "2ebf6d4b-2c96-4e56-9a43-ab6c7667e46b";
        controller.getPacket(expectedId);

        verify(service).getPacket(expectedId);
    }

    @Override
    protected PacketController setupController() {
        return new PacketController(service);
    }
}
