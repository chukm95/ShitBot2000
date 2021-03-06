package implementation;

import TI.BoeBot;
import behaviors.AwaitingConnectBehavior;
import behaviors.Behavior;
import hardware.Ultrasoon;
import shittynetcode.ShittyNetServer;

import java.util.ArrayList;

public class ShitBot {

    //singleton instance
    private static ShitBot instance;

    //singleton
    public static ShitBot getInstance(){
        if(instance == null)
            instance = new ShitBot();
        return instance;
    }

    //all sensors and motors are stored here
    private ArrayList<IComponent> componentList;
    //deltaTime
    private DeltaTime deltaTime;
    //the current boebot behavior
    private Behavior currentBehavior;
    //network component


    private ShitBot(){
        //set instance to prevent stackoverflow
        instance = this;
        //initialize BoeBot
        Initialize();
        //boebot loop
        BoeBotLoop();
    }

    private void Initialize() {
        //init component list
        componentList = new ArrayList<>();
        //initialize delta time
        deltaTime = new DeltaTime();
        //initialize network component

        //initialize all componets
        componentList.add(new Ultrasoon(1, 2));
        componentList.add(new Linefollowers(2, 0, 1));
        componentList.add(new Motors(15, 14));
        componentList.add(ShittyNetServer.getInstance());
        //set starting behavior
        SwitchStates(new AwaitingConnectBehavior());
    }

    private void BoeBotLoop(){
        while(true){
            //update deltatime
            deltaTime.Update();
            //update all the sensors and motors
            UpdateComponents();
            //Behavior
            currentBehavior.Update(deltaTime.getDeltaTime());
            //delay one microsecond
            BoeBot.wait(0,1);
        }
    }

    private void UpdateComponents(){
        for(IComponent component : componentList){
            component.update(deltaTime.getDeltaTime());
        }
    }

    public void SwitchStates(Behavior behavior){
        //behavior cannot be null
        if(behavior == null)
            //if it is
            return;

        //check if current behavior exists
        if(currentBehavior != null)
            //if so set it to null
            currentBehavior.OnDeactivate();
        //set the current behavior
        currentBehavior = behavior;
        //activate current behavior
        currentBehavior.OnActivate();
    }

    public IComponent getComponent(IComponent.componentType type){
        //gets all components from componentlist
        for (IComponent component : componentList){
            //check if component equals the type
            if (component.getType() == type){
                // returns the component that equals the type
                return component;
            }
        }
        return null;
    }

}
