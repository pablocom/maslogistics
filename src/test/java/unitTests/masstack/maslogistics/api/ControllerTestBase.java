package unitTests.masstack.maslogistics.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public abstract class ControllerTestBase<TController> {
    protected TController controller;

    @BeforeEach
    void setup(){
        additionalSetup();
        controller = setupController();
    }

    protected void additionalSetup() {}
    protected abstract TController setupController();
}
