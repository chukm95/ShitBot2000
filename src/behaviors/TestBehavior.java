package behaviors;

import hardware.Ultrasoon;
import implementation.IComponent;

public class TestBehavior extends Behavior{

    private Ultrasoon ultrasoon;

    public TestBehavior() {
        super();
        ultrasoon = (Ultrasoon) getShitBot().getComponent(IComponent.componentType.ULTRASOON);
    }

    @Override
    public void OnActivate() {

    }

    @Override
    public void Update(double deltaTime) {
        System.out.println(ultrasoon.getDistance());

    }

    @Override
    public void OnDeactivate() {

    }
}
