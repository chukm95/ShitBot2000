package implementation;

import hardware.Motor;

public class Motors implements IComponent {
    private int pinLeft;
    private int pinRight;
    private int currentspeedleft;
    private int currenspeedRight;
    private Motor motorleft;
    private Motor moterRight;
    private final float SPEED_OFFSET = 1.55f;

    public Motors(int pinLeft, int pinRight) {
        this.pinLeft = pinLeft;
        this.pinRight = pinRight;

        motorleft = new Motor(pinLeft);
        moterRight = new Motor(pinRight);
    }

    public int getCurrentspeedleft() {
        return currentspeedleft;
    }

    public int getCurrenspeedRight() {
        return currenspeedRight;
    }

    public void setSpeed(int speed){
        motorleft.setSpeed(1500 + speed);
        currentspeedleft =  speed;
        moterRight.setSpeed(1500 - (int)(speed * SPEED_OFFSET));
        currenspeedRight = speed;
    }

    public void setSpeed(int leftspeed, int rightspeed){
        motorleft.setSpeed(1500 + leftspeed);
        currentspeedleft =  leftspeed;
        moterRight.setSpeed(1500 - (int)(rightspeed * SPEED_OFFSET));
        currenspeedRight = rightspeed;
    }

    @Override
    public void update(double deltaTime) {

    }

    @Override
    public componentType getType() {
        return componentType.MOTORS;
    }
}
