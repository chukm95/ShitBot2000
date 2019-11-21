package harware;

import TI.Servo;

public class Motor {

    //The pin number the servo is on
    private final int PIN;

    //The boebot servo
    private Servo servo;

    //is servo mirrored
    private boolean isMirrored;

    //the current speed of the servo
    private int currentSpeed = 0;

    //the speed offset

    private float offset = 1f;
    //constructor

    public Motor(int pin, boolean isMirrored){
        PIN = pin;
        servo = new Servo(pin);
        servo.update(1500);
        this.isMirrored = isMirrored;
    }
    public void setSpeed(int speed){
        if(isMirrored){
            speed *= -1;
        }

        if(speed > 180){
            speed = 180;
        }

        if(speed < -180){
            speed = -180;
        }

        servo.update(1500 + (int)(speed * offset));
        currentSpeed = speed;
    }

    public void SetStop(){
        servo.update(1500);
        currentSpeed = 0;
    }

    public boolean isMirrored() {
        return isMirrored;
    }

    public int getCurrentSpeed() {
        return currentSpeed;
    }

    public void setCurrentSpeed(int currentSpeed) {
        this.currentSpeed = currentSpeed;
    }

    public float getOffset() {
        return offset;
    }

    public void setOffset(float offset) {
        this.offset = offset;
    }





}
