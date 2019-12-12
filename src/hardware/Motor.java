package hardware;

import TI.Servo;

public class Motor {
    private int pin;
    private Servo servo;

    public Motor(int pin) {
        this.pin = pin;
        servo = new Servo(pin);
    }

    public void setSpeed(int speed){
        servo.update(speed);
    }
}
