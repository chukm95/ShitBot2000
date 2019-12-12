package hardware;

import TI.BoeBot;
import implementation.IComponent;

public class Ultrasoon  implements IComponent {
    private int distance;
    private int triggerPin;
    private int echoPin;

    public Ultrasoon(int triggerPin, int echoPin) {
        this.triggerPin = triggerPin;
        this.echoPin = echoPin;

    }

    public int getDistance(){
        return distance;
    }

    @Override
    public void update(double deltaTime) {
        BoeBot.digitalWrite(triggerPin,true);
        BoeBot.wait(1);
        BoeBot.digitalWrite(triggerPin,false);
        this.distance = BoeBot.pulseIn(echoPin,true,5000);
    }

    @Override
    public componentType getType() {
        return componentType.ULTRASOON;
    }
}
