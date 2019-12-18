package shittynetcode.messages;

public class Msg_In_SensorDataRequest extends Message_in {
    public Msg_In_SensorDataRequest() {
        messageType = MessageTypes.SENSORDATAREQUEST;
    }
}
