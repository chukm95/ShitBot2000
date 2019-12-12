package implementation.messages;

public class Msg_In_SensorDataRequest implements IMessageIn {
    @Override
    public MessageType getType() {
        return MessageType.SENSOR_DATA_REQUEST;
    }
}
