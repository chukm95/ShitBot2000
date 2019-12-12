package Implementation;

import Behaviors.Behavior;
import Behaviors.IdleBehavior;
import TI.BoeBot;

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

    private ShitBot(){
        //initialize BoeBot
        Initialize();
        //boebot loop
        BoeBotLoop();
    }

    private void Initialize(){
        //init component list
        componentList = new ArrayList<>();

        //initialize delta time
        deltaTime = new DeltaTime();

        //initialize all componets

        //set starting behavior
        SwitchStates(new IdleBehavior());
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
            BoeBot.wait(0, 1);
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


}
