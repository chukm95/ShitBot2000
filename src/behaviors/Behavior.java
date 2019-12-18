package behaviors;

import hardware.Ultrasoon;
import implementation.IComponent;
import implementation.Linefollowers;
import implementation.Motors;
import implementation.ShitBot;
import shittynetcode.ShittyNetServer;
import shittynetcode.messages.Message_in;

public abstract class Behavior {

    //the controlls
    private ShitBot shitBot;
    private Ultrasoon ultrasoon;
    private Linefollowers linefollowers;
    private Motors motors;
    private ShittyNetServer networkComponent;
    private Behavior nextBehavior;


    //the constructors
    protected  Behavior(Behavior nextBehavior){
        Initialize();
        this.nextBehavior = nextBehavior;
    }

    protected Behavior(){
        Initialize();
        nextBehavior = null;
    }

    private void Initialize(){
        shitBot = ShitBot.getInstance();
        ultrasoon = (Ultrasoon) shitBot.getComponent(IComponent.componentType.ULTRASOON);
        linefollowers = (Linefollowers) shitBot.getComponent(IComponent.componentType.LINEFOLLOWERS);
        motors = (Motors) shitBot.getComponent(IComponent.componentType.MOTORS);
        networkComponent = (ShittyNetServer) shitBot.getComponent(IComponent.componentType.NETWORK);
    }

    public abstract void OnActivate();
    public abstract void Update(double deltaTime);
    public abstract void OnDeactivate();

    public Behavior setNextBehavior(Behavior nextBehavior){
        this.nextBehavior = nextBehavior;
        return nextBehavior;
    }

    protected void switchToNextBehavior(){
        if (nextBehavior == null){
            nextBehavior = new IdleBehavior();
        }
        shitBot.SwitchStates(nextBehavior);

    }

    public void pollForMessages(){
        for(Message_in msg : networkComponent.getMessagesIn()){
            switch (msg.getMessageType()){
                case TIMEOUT:
                    onTimeout();
                    break;
                case SENSORDATAREQUEST:
                    onSensorDataRequest();
                    break;
                case FORWARD:
                    onForward();
                    break;
                case LEFT:
                    onLeft();
                    break;
                case RIGHT:
                    onRight();
                    break;
                case OneEighty:
                    onOneEighty();
                    break;
                case INFINETEIGHT:
                    onInfinetEight();
                    break;

            }
        }
    }

    protected void onTimeout(){}
    protected void onSensorDataRequest(){}
    protected void onForward(){}
    protected void onLeft(){}
    protected void onRight(){}
    protected void onOneEighty(){}
    protected void onInfinetEight(){}

    protected Linefollowers getLinefollowers(){
        return linefollowers;
    }

    protected Motors getMotors(){
        return motors;
    }

    protected ShittyNetServer getNetworkComponent(){
        return networkComponent;
    }

    protected Ultrasoon getUltrasoon(){
        return ultrasoon;
    }

    protected ShitBot getShitBot(){
        return shitBot;
    }
}
