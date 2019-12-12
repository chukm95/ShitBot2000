package behaviors;

import implementation.IComponent;
import implementation.Linefollowers;
import implementation.Motors;

public class TurnBehavior extends Behavior {

    private final double timeBeforeSensing = 0.2;
    private double time;
    private Motors motors;
    private Linefollowers linefollowers;
    private boolean turnLeft;

    public TurnBehavior(boolean turnLeft, Behavior nextBehavior){
        super(nextBehavior);
        this.turnLeft = turnLeft;
    }

    @Override
    public void OnActivate() {
        motors = (Motors)getShitBot().getComponent(IComponent.componentType.MOTORS);
        if(turnLeft)
            motors.setSpeed(0,200);
        else
            motors.setSpeed(200,0);
        linefollowers = (Linefollowers)getShitBot().getComponent(IComponent.componentType.LINEFOLLOWERS);

        time = 0;
    }

    @Override
    public void Update(double deltaTime) {
        time += deltaTime;

        if(time >= timeBeforeSensing)
            if(linefollowers.isDetectLineMid())
                getShitBot().SwitchStates(nextBehavior);

    }

    @Override
    public void OnDeactivate() {
    }
}
