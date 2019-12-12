package hardware;

import TI.BoeBot;

public class linefollower {
    private int pin;

    public linefollower(int pin) {
        this.pin = pin;
    }

    public boolean detectLine(){
        if (BoeBot.analogRead(pin) > 50) {
            return true;
        }
        return false;
    }
}
