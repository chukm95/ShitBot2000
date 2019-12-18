package behaviors;

import shittynetcode.messages.Msg_Out_Complete;
import shittynetcode.messages.Msg_Out_SensorData;

public class IdleBehavior extends Behavior {

    public IdleBehavior(){
    }

    @Override
    public void OnActivate() {
        getNetworkComponent().sendMessage(new Msg_Out_Complete());
        getMotors().setSpeed(0);
    }

    @Override
    public void Update(double deltaTime) {
        pollForMessages();
    }

    @Override
    protected void onTimeout() {
        System.out.println("Client disconnected!");
        setNextBehavior(new AwaitingConnectBehavior());
        switchToNextBehavior();
    }

    @Override
    protected void onSensorDataRequest() {
        Msg_Out_SensorData msgo = new Msg_Out_SensorData(getUltrasoon().getDistance(), getLinefollowers().isDetectLineLeft(), getLinefollowers().isDetectLineMid(), getLinefollowers().isDetectLineRight(), getMotors().getCurrentspeedleft(), getMotors().getCurrenspeedRight());
        getNetworkComponent().sendMessage(msgo);
    }

    @Override
    protected void onForward() {
        setNextBehavior(new LineFollowBehavior());
        switchToNextBehavior();
    }

    @Override
    protected void onLeft() {
        setNextBehavior(new TurnLeftBehavior());
        switchToNextBehavior();
    }

    @Override
    protected void onRight() {
        setNextBehavior(new TurnRightBehavior());
        switchToNextBehavior();
    }

    @Override
    protected void onOneEighty() {
        setNextBehavior(new TurnLeftBehavior()).setNextBehavior(new TurnLeftBehavior());
        switchToNextBehavior();
    }

    @Override
    protected void onInfinetEight() {
        setNextBehavior(new InfiniteEightBehavior());
        switchToNextBehavior();
    }

    @Override
    public void OnDeactivate() {

    }
}
