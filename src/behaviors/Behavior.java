package behaviors;

import implementation.ShitBot;

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

    //network related virtual components
    public void netOnTimeOut(){}
    public void netOnConnection(){}
    public void netOnDisconnect(){}

    protected ShitBot getShitBot(){
        return shitBot;
    }
}
