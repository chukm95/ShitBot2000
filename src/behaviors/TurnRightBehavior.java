package behaviors;

import implementation.IComponent;
import implementation.Motors;

public class TurnRightBehavior  extends Behavior {

    private final double timeToSpin = 2.1;
    private double time;
    private Motors motors;

    @Override
    public void OnActivate() {
        motors = (Motors)getShitBot().getComponent(IComponent.componentType.MOTORS);
        motors.setSpeed(20,-20);
        time = 0;
    }

    @Override
    public void Update(double deltaTime) {
        time += deltaTime;

        if(time >= timeToSpin) {
            getShitBot().SwitchStates(new LineFollowBehavior());
            System.out.println(time - timeToSpin);
        }

    }

    @Override
    public void OnDeactivate() {
        motors.setSpeed(0);
    }
}
