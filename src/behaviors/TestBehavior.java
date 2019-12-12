package behaviors;

import hardware.Ultrasoon;
import implementation.IComponent;
import implementation.Linefollowers;
import implementation.Motors;

public class TestBehavior extends Behavior{

    private Ultrasoon ultrasoon;
    private Linefollowers linefollowers;
    private Motors motors;

    public TestBehavior() {
        super();
        ultrasoon = (Ultrasoon) getShitBot().getComponent(IComponent.componentType.ULTRASOON);
        linefollowers = (Linefollowers) getShitBot().getComponent(IComponent.componentType.LINEFOLLOWERS);
        motors = (Motors) getShitBot().getComponent(IComponent.componentType.MOTORS);
    }

    @Override
    public void OnActivate() {

    }

    @Override
    public void Update(double deltaTime) {
//        System.out.println(ultrasoon.getDistance());
//        System.out.println(linefollowers.isDetectLineLeft());
//        System.out.println(linefollowers.isDetectLineMid());
//        System.out.println(linefollowers.isDetectLineRight());
//        motors.setSpeed(50,20);
//        System.out.println(motors.getCurrenspeedRight());
//        System.out.println(motors.getCurrentspeedleft());



    }

    @Override
    public void OnDeactivate() {

    }
}
