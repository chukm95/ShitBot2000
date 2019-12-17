package behaviors;

import implementation.messages.Msg_In_SensorDataRequest;
import implementation.messages.Msg_In_TimeOut;
import implementation.messages.Msg_Out_SensorData;

public class IdleBehavior extends Behavior {

    public IdleBehavior(){
    }

    @Override
    public void OnActivate() {

    }

    @Override
    public void Update(double deltaTime) {

    }

    @Override
    protected void onSensorDataRequest(Msg_In_SensorDataRequest msg_sensorDataRequest) {
        Msg_Out_SensorData sensorData = new Msg_Out_SensorData(
                getUltrasoon().getDistance(),
                getMotors().getCurrentspeedleft(),
                getMotors().getCurrenspeedRight(),
                getLinefollowers().isDetectLineLeft(),
                getLinefollowers().isDetectLineMid(),
                getLinefollowers().isDetectLineRight()
        );

        getNetworkComponent().sendMessage(sensorData);

        super.onSensorDataRequest(msg_sensorDataRequest);
    }

    @Override
    protected void onTimeOut(Msg_In_TimeOut msg_timeout) {
        setNextBehavior(new AwaitingConnectBehavior());
        switchToNextBehavior();
        super.onTimeOut(msg_timeout);
    }

    @Override
    public void OnDeactivate() {

    }
}
