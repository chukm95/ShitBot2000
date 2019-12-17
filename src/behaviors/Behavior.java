package behaviors;

import hardware.Ultrasoon;
import implementation.*;

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
