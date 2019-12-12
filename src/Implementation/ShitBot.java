package Implementation;

import Behaviors.Behavior;
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

        //initialize all componets


    }

    private void BoeBotLoop(){
        while(true){
            //update all the sensors and motors
            UpdateComponents();
            //Behavior
            currentBehavior.Update(0);
            //delay one microsecond
            BoeBot.wait(0, 1);
        }
    }

    private void UpdateComponents(){
        for(IComponent component : componentList){
            component.update(0);
        }
    }

    public void SwitchStates(Behavior behavior){
        //behavior must be something
        if(behavior == null)
            //if not return
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
