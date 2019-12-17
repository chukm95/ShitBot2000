package implementation;

import TI.BoeBot;
import behaviors.Behavior;
import behaviors.IdleBehavior;
import hardware.Ultrasoon;

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
    private NetworkComponent networkComponent;

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
        networkComponent = new NetworkComponent();
        //initialize all componets
        componentList.add(new Ultrasoon(1, 2));
        componentList.add(new Linefollowers(2, 0, 1));
        componentList.add(new Motors(15, 14));
        componentList.add(networkComponent);
        System.out.println("check 1");
        //set starting behavior
        SwitchStates(new IdleBehavior());
        System.out.println("check 2");
    }

    private void BoeBotLoop(){
        while(true){
            System.out.println("check 3");
            //update deltatime
            deltaTime.Update();
            System.out.println("check 4");
            //update all the sensors and motors
            UpdateComponents();
            System.out.println("check 5");
            //Behavior
            currentBehavior.Update(deltaTime.getDeltaTime());
            //send ping every update
            System.out.println("check 6");
            //delay one microsecond
            BoeBot.wait(0,1);
            System.out.println(currentBehavior);
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
