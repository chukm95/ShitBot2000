package behaviors;

import implementation.ShitBot;

public abstract class Behavior {

    //the controlls
    private ShitBot shitBot;
    protected Behavior nextBehavior;

    //the constructor
    protected Behavior(Behavior nextBehavior){
        shitBot = ShitBot.getInstance();
        this.nextBehavior = nextBehavior;
    }

    public abstract void OnActivate();
    public abstract void Update(double deltaTime);
    public abstract void OnDeactivate();

    protected ShitBot getShitBot(){
        return shitBot;
    }
}
