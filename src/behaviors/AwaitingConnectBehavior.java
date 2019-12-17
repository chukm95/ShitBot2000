package behaviors;

import implementation.messages.IMessageIn;
import implementation.messages.Msg_In_Connect;

public class AwaitingConnectBehavior extends Behavior {

    public AwaitingConnectBehavior(){

    }

    @Override
    public void OnActivate() {
        setNextBehavior(new IdleBehavior());
    }

    @Override
    public void Update(double deltaTime) {

    }

    @Override
    protected void onConnectMessage(Msg_In_Connect msg_connect) {
        switchToNextBehavior();
        super.onConnectMessage(msg_connect);
    }

    @Override
    public void OnDeactivate() {

    }
}
