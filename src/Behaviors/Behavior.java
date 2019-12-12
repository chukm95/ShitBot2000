package Behaviors;

import Implementation.ShitBot;
import TI.BoeBot;

public abstract class Behavior {

    //the controlls
    private ShitBot shitBot;

    //the constructor
    protected Behavior(){
        shitBot = ShitBot.getInstance();
    }

    public abstract void OnActivate();
    public abstract void Update(double deltaTime);
    public abstract void OnDeactivate();

    protected ShitBot getShitBot(){
        return shitBot;
    }
}