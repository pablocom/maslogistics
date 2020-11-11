package unitTests.masstack.maslogistics.api;


import masstack.maslogistics.domain.DomainException;
import org.junit.jupiter.api.BeforeEach;

public abstract class ControllerTestBase<TController> {
    protected TController controller;

    @BeforeEach
    public void setup(){
        additionalSetup();
        controller = setupController();
    }

    protected void additionalSetup() {}
    protected abstract TController setupController();
}
