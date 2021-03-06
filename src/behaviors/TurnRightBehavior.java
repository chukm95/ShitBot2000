package behaviors;

public class TurnRightBehavior extends Behavior {

    private final double timeBeforeSensing = 0.2;
    private double time;

    public TurnRightBehavior(){
    }

    @Override
    public void OnActivate() {
        getMotors().setSpeed(200,-200);
        time = 0;
    }

    @Override
    public void Update(double deltaTime) {
        time += deltaTime;

        if(time >= timeBeforeSensing)
            if(getLinefollowers().isDetectLineMid())
                switchToNextBehavior();

    }

    @Override
    public void OnDeactivate() {
    }
}
