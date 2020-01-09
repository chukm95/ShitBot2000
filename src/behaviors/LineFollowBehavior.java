package behaviors;


public class LineFollowBehavior extends Behavior {
    private double time;
    private final double rolloutTime = 0.15;
    private boolean isRollingOut;


    public LineFollowBehavior() {
        isRollingOut = false;
        time = 0;
    }

    @Override
    public void OnActivate() {

    }

    @Override
    public void Update(double deltaTime) {

        int number = (getLinefollowers().isDetectLineLeft() ? 1 : 0) << 2 | (getLinefollowers().isDetectLineMid() ? 1 : 0) << 1 | (getLinefollowers().isDetectLineRight() ? 1 : 0);
        if (!isRollingOut) {
            switch (number) {
                case 0b010:
                    getMotors().setSpeed(200, 200);
                    break;
                case 0b011:
                    getMotors().setSpeed(200, 0);
                    break;
                case 0b001:
                    getMotors().setSpeed(200, 0);
                    break;
                case 0b110:
                    getMotors().setSpeed(0, 200);
                    break;
                case 0b100:
                    getMotors().setSpeed(0, 200);
                    break;
                case 0b111:
                    getMotors().setSpeed(200);
                    isRollingOut = true;
                    break;
            }
        } else {
            time += deltaTime;
            if (time >= rolloutTime) {
                switchToNextBehavior();
            }
        }
    }

    @Override
    public void OnDeactivate() {

    }
}
