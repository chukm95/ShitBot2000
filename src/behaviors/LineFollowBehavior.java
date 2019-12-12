package behaviors;

import implementation.IComponent;
import implementation.Linefollowers;
import implementation.Motors;

public class LineFollowBehavior extends Behavior {
    private Linefollowers linefollowers;
    private Motors motors;
    private double time;
    private final double rolloutTime = 0.05;
    private boolean isRollingOut;


    public LineFollowBehavior(Behavior nextBehavior) {
        super(nextBehavior);
        linefollowers = (Linefollowers) getShitBot().getComponent(IComponent.componentType.LINEFOLLOWERS);
        motors = (Motors) getShitBot().getComponent(IComponent.componentType.MOTORS);
        isRollingOut = false;
        time = 0;
    }

    @Override
    public void OnActivate() {

    }

    @Override
    public void Update(double deltaTime) {

        int number = (linefollowers.isDetectLineLeft() ? 1 : 0) << 2 | (linefollowers.isDetectLineMid() ? 1 : 0) << 1 | (linefollowers.isDetectLineRight() ? 1 : 0);
        if (!isRollingOut) {
            switch (number) {
                case 0b010:
                    motors.setSpeed(200, 200);
                    break;
                case 0b011:
                    motors.setSpeed(200, 0);
                    break;
                case 0b001:
                    motors.setSpeed(200, 0);
                    break;
                case 0b110:
                    motors.setSpeed(0, 200);
                    break;
                case 0b100:
                    motors.setSpeed(0, 200);
                    break;
                case 0b111:
                    motors.setSpeed(200);
                    isRollingOut = true;
                    break;
            }
        } else {
            time += deltaTime;
            if (time >= rolloutTime) {
                getShitBot().SwitchStates(nextBehavior);
            }
        }
    }

    @Override
    public void OnDeactivate() {
        //motors.setSpeed(0);
    }
}
