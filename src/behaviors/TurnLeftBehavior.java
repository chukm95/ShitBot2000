package behaviors;

import implementation.IComponent;
import implementation.Linefollowers;
import implementation.Motors;

public class TurnLeftBehavior extends Behavior {

    private final double timeBeforeSensing = 0.2;
    private double time;
    private Motors motors;
    private Linefollowers linefollowers;
    private Behavior nextBehavior;

    public TurnLeftBehavior(){

    }

    @Override
    public void OnActivate() {
        motors = (Motors)getShitBot().getComponent(IComponent.componentType.MOTORS);
        motors.setSpeed(0,200);
        linefollowers = (Linefollowers)getShitBot().getComponent(IComponent.componentType.LINEFOLLOWERS);

        time = 0;
    }

    @Override
    public void Update(double deltaTime) {
        time += deltaTime;

        if(time >= timeBeforeSensing)
            if(linefollowers.isDetectLineMid())
                getShitBot().SwitchStates(new LineFollowBehavior());

    }

    @Override
    public void OnDeactivate() {
        //motors.setSpeed(0);
    }
}
