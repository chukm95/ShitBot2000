package behaviors;

import hardware.Ultrasoon;
import implementation.*;
import implementation.messages.*;

public abstract class Behavior {

    //the controlls
    private ShitBot shitBot;
    private Ultrasoon ultrasoon;
    private Linefollowers linefollowers;
    private Motors motors;
    private NetworkComponent networkComponent;
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
        networkComponent = (NetworkComponent) shitBot.getComponent(IComponent.componentType.NETWORK);
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
        for(IMessageIn msg = getNetworkComponent().pollMessage(); msg != null; msg = getNetworkComponent().pollMessage()){
            switch (msg.getType()){
                case CONNECT:
                    onConnectMessage((Msg_In_Connect) msg);
                    break;
                case DISCONNECT:
                    onDisconnectMessage((Msg_In_Disconnect) msg);
                    break;
                case TIMEOUT:
                    onTimeOut((Msg_In_TimeOut) msg);
                    break;
                case SENSOR_DATA_REQUEST:
                    onSensorDataRequest((Msg_In_SensorDataRequest) msg);
                    break;
            }
        }
    }

    protected void onConnectMessage(Msg_In_Connect msg_connect){}
    protected void onDisconnectMessage(Msg_In_Disconnect msg_in_disconnect){}
    protected void onTimeOut(Msg_In_TimeOut msg_timeout){}
    protected void onSensorDataRequest(Msg_In_SensorDataRequest msg_sensorDataRequest){}

    protected Linefollowers getLinefollowers(){
        return linefollowers;
    }

    protected Motors getMotors(){
        return motors;
    }

    protected NetworkComponent getNetworkComponent(){
        return networkComponent;
    }

    protected Ultrasoon getUltrasoon(){
        return ultrasoon;
    }

    protected ShitBot getShitBot(){
        return shitBot;
    }
}
