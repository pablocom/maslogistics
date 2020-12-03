package unitTests.masstack.maslogistics.api;

import masstack.maslogistics.webapi.controllers.PacketController;
import masstack.maslogistics.domain.services.PacketManagementService;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class WhenHandlingGetAllPacketsRequest extends ControllerTestBase<PacketController> {
    private PacketManagementService service;

    @Override
    protected void additionalSetup() throws Exception {
        super.additionalSetup();
        service = mock(PacketManagementService.class);
    }

    @Test
    void serviceIsCalled() {
        controller.getAllPackets();

        verify(service).getAllPackets();
    }

    @Override
    protected PacketController setupController() {
        return new PacketController(service);
    }
}
