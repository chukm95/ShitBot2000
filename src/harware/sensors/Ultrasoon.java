package harware.sensors;

import TI.BoeBot;

public class Ultrasoon {
private final int PIN_TRIGGER;
private final int PIN_ECHO;
private int timeout;

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public Ultrasoon(int pin_trigger, int pin_echo, int timeout){
        PIN_TRIGGER = pin_trigger;
        PIN_ECHO = pin_echo;
        this.timeout = timeout;
    }

    private int getDistance(){
        BoeBot.digitalWrite(PIN_TRIGGER, true);
        BoeBot.wait(1);
        BoeBot.digitalWrite(PIN_TRIGGER,false);
        return BoeBot.pulseIn(PIN_ECHO,true,timeout);
    }
}
