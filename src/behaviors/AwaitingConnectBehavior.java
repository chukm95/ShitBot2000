package behaviors;

public class AwaitingConnectBehavior extends Behavior {

    public AwaitingConnectBehavior(){

    }

    @Override
    public void OnActivate() {
        setNextBehavior(new IdleBehavior());
    }

    @Override
    public void Update(double deltaTime) {
        if(!getNetworkComponent().isConnected()){
            getNetworkComponent().listenForConnection();
        }else{
            System.out.println("connected!");
            switchToNextBehavior();
        }
    }

    @Override
    public void OnDeactivate() {

    }
}
