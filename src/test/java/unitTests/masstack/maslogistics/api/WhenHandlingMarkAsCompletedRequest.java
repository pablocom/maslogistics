package unitTests.masstack.maslogistics.api;

import masstack.maslogistics.api.controllers.PacketController;
import masstack.maslogistics.domain.services.PacketManagementService;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class WhenHandlingMarkAsCompletedRequest extends ControllerTestBase<PacketController> {
    private PacketManagementService service;

    @Override
    protected void additionalSetup() throws Exception {
        super.additionalSetup();

        service = mock(PacketManagementService.class);
    }

    @Test
    void serviceIsCalled() {
        var packetId = "2ebf6d4b-2c96-4e56-9a43-ab6c7667e46b";

        var httpStatus = controller.markAsCompleted(packetId);

        verify(service).markAsCompleted(packetId);
        assertThat(httpStatus.getStatusCode(), is(equalTo(HttpStatus.CREATED)));
    }

    @Override
    protected PacketController setupController() {
        return new PacketController(service);
    }
}
